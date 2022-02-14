package csvprojectteamone.model.database;

import csvprojectteamone.model.Employee;
import csvprojectteamone.model.LogClass;
import csvprojectteamone.view.DisplayManager;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class EmployeeDAOImpl implements EmployeeDAOInterface {

//Declaration of the constant variables used for Querying into the database
    private static final String INSERT_EMPLOYEE = "INSERT INTO employees" +
                " (employeeId, title , firstName, middleInitial, lastName, gender, emailAddress, dateOfBirth, dateOfJoining, salary)"
                + " VALUES" + " (?,?,?,?,?,?,?,?,?,?);";
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE employeeId = ?; ";
    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM employees WHERE employeeId !=0;";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees";
    private static final String CREATE_TABLE = "CREATE TABLE employees " +
                "(employeeId INTEGER, " + "title VARCHAR(20), "+ "firstName VARCHAR(20), " + "middleInitial CHAR, "
                + "lastName VARCHAR(20), " + "gender CHAR, " + "emailAddress VARCHAR(255)," + "dateOfBirth DATE, "
                + "dateOfJoining DATE, " + "SALARY INTEGER NOT NULL,"+ "PRIMARY KEY (employeeId))";


    //Method to drop employee table if already exists to make sure it´s cleaned before using and then creates if it does not exist
    @Override
    public void createEmployeesTable() {
        try {
            //creates connection to the database
            Connection connection = DatabaseConnectionFactory.getConnection();
            Statement dropTable = connection.createStatement();
            dropTable.executeUpdate("DROP TABLE IF EXISTS employees");
            Statement createTable = connection.createStatement();
            createTable.executeUpdate(CREATE_TABLE);
            System.out.println("TABLE CREATED");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    //Method to inserts one single employee into the employees database using the employee´s object
        @Override
    public void insertEmployee(Employee employee){
        PreparedStatement preparedStatement = null;
        try{
            //creates connection to the database
            Connection connection = DatabaseConnectionFactory.getConnection();
            //Statement to insert all column values into the table using employee´s object info.
            preparedStatement = connection.prepareStatement(
                    INSERT_EMPLOYEE);
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2,employee.getTitle());
            preparedStatement.setString(3,employee.getFirstName());
            preparedStatement.setString(4,String.valueOf(employee.getMiddleInitial()));
            preparedStatement.setString(5,employee.getLastName());
            preparedStatement.setString(6,String.valueOf(employee.getGender()));
            preparedStatement.setString(7,employee.getEmailAddress());
            preparedStatement.setDate(8, Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setDate(9,Date.valueOf(employee.getDateOfJoining()));
            preparedStatement.setDouble(10,employee.getSalary());

            //Executes the query to insert an employee
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }

    }

    //Method to insert multiple employees into the table
    @Override
    public void insertMultipleEmployees(HashMap<Integer, Employee> employeesList) {
        //Iterates through the map of employees objects and calls insert employee method for each employee
        for(Employee employee : employeesList.values()){
            insertEmployee(employee);
        }
    }

    //Method to select employee from the employees table based on the employee id
    @Override
    public boolean selectEmployee(int employeeId) {
        //Initialises the result query
        ResultSet rs = null;
        try{
            //creates connection to the database
            Connection connection = DatabaseConnectionFactory.getConnection();
            //Statement to select employees based on the employee id
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1,employeeId);
            rs = preparedStatement.executeQuery();

            //If employee is found, prints its info to the console and returns true
            if(rs.next()){
                System.out.println("Employee: " + rs.getString(2) + " "  + rs.getString(3) + " " +  rs.getString(4) + " " + rs.getString(5)
                                + " Gender: " + rs.getString(6) + " Email: " + rs.getString(7) + " Birthdate: " + rs.getString(8) + " Joining date: "
                                + rs.getString(9));
                return true;}
            //Returns false if employee has not been found
            else return false;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    //Method to select all employees from the employees table
    @Override
    public void selectAllEmployees() {
        ResultSet rs = null;
        try{
            //creates connection to the database
            Connection connection = DatabaseConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            //Statement to select all employees in the table.
            rs = statement.executeQuery(SELECT_ALL_EMPLOYEES);
            //iterates through the result and prints out employees info
            while(rs.next()) {
                System.out.println("Employee: " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5)
                        + " Gender: " + rs.getString(6) + " Email: " + rs.getString(7) + " Birthdate: " + rs.getString(8) + " Joining date: "
                        + rs.getString(9));
            }
            rs.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    //Method to count number of employees in the database
    public int countRows() {
        long startTime = System.nanoTime();
        //initialises count variable
        int count = 0;
        try{
            //creates connection to the database
            Connection connection = DatabaseConnectionFactory.getConnection();
            // statement to count the number of rows (employees) in the table
            Statement statement = connection.createStatement();
            statement.executeQuery(COUNT_ROWS);
            ResultSet rs = statement.getResultSet();
            rs.next();
            count = Integer.parseInt(rs.getString(1));
        } catch (SQLException e){
            //Prints out error message if could not count employees
            LogClass.logError("Rows couldn't be counted: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            double totalTime = (System.nanoTime() - startTime)/1_000_000_000.0;
            DisplayManager.displayMessage("Operation performed in " + totalTime + " seconds");
            DisplayManager.displayMessage("Total Number Of Employees :: " + count);
        }
        return count;
    }
}
