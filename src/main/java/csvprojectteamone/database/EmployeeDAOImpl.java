package csvprojectteamone.database;

import csvprojectteamone.DatabaseConnectionFactory;
import csvprojectteamone.Employee;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class EmployeeDAOImpl implements EmployeeDAO {

    private static final String INSERT_EMPLOYEE = "INSERT INTO employees" +
                " (employeeId, title , firstName, middleInitial, lastName, gender, emailAddress, dateOfBirth, dateOfJoining, salary)"
                + " VALUES" + " (?,?,?,?,?,?,?,?,?,?);";
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE employeeId = ?; ";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE employeeId = ?;";
    private static final String UPDATE_EMPLOYEE = "UPDATE employee set name = ? WHERE employeeId = ? ;";
    private static final String CREATE_TABLE = "CREATE TABLE employees " +
                "(employeeId INTEGER NOT NULL, " + "title VARCHAR(20), "+ "firstName VARCHAR(20), " + "middleInitial CHAR, "
                + "lastName VARCHAR(20), " + "gender CHAR, " + "emailAddress VARCHAR(255)," + "dateOfBirth DATE, "
                + "dateOfJoining DATE, " + "SALARY INTEGER NOT NULL)";

    @Override
    public void createEmployeesTable() {
        try {
            Connection connection = DatabaseConnectionFactory.getConnection();
            Statement dropTable = connection.createStatement();
            dropTable.executeUpdate("DROP TABLE IF EXISTS employees");
            Statement createTable = connection.createStatement();
            createTable.executeUpdate(CREATE_TABLE);
            System.out.println("TABLE CREATED");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                DatabaseConnectionFactory.closeConnection();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
        @Override
    public void insertEmployee(Employee employee){
        PreparedStatement preparedStatement = null;
        try{
            Connection connection = DatabaseConnectionFactory.getConnection();
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

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
//        finally {
//            try{
//                DatabaseConnectionFactory.closeConnection();
//            } catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void insertMultipleEmployees(HashMap<Integer, Employee> employeesList) {
        for(Employee employee : employeesList.values()){
            insertEmployee(employee);
        }
//        selectEmployee(1);
        try {
            DatabaseConnectionFactory.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateEmployee(int employeeId){

        return false;
    }

    @Override
    public void selectEmployee(int employeeId) {
        ResultSet rs = null;
        try(Connection connection = DatabaseConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM employees WHERE employeeId = ?")
            ;) {
            preparedStatement.setInt(1,1);
            rs = preparedStatement.executeQuery();
            System.out.println(rs);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnectionFactory.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void selectAllEmployees() {

    }

    @Override
    public boolean deleteEmployee(int employeeId){

        return false;
    }

}
