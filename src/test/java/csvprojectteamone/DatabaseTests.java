package csvprojectteamone;

import csvprojectteamone.controller.ThreadedFileReader;
import csvprojectteamone.model.database.DatabaseConnectionFactory;
import csvprojectteamone.model.database.EmployeeDAOImpl;
import csvprojectteamone.model.Employee;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

import static csvprojectteamone.controller.FileReader.readCSV;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DatabaseTests {
    //Declares the employeeDAO implementation
    private static EmployeeDAOImpl employeeDAO;

    //Initialises the employeeDAO implementation before tests run
    @BeforeAll
    static void setUp(){
        employeeDAO = new EmployeeDAOImpl();
    }

    //Closes the connection after the tests are executed
    @AfterAll
    static void cleanUp(){
        try {
            DatabaseConnectionFactory.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Test to check whether a table has been created or not, if it exists will return true, false otherwise.
    @Test
    @DisplayName("GivenADatabaseConnectionCreatesEmployeesTable")
    public void createTableCheckIfExists(){
        //calls the create employees table method
        employeeDAO.createEmployeesTable();
        try {
            //gets connection to the database
            Connection connection = DatabaseConnectionFactory.getConnection();
            DatabaseMetaData meta = connection.getMetaData();
            //query to check if table does exist
            ResultSet resultSet = meta.getTables(null, null, "employees", new String[] {"TABLE"} );
            boolean exists = (resultSet.next());
            //Checks if table exists
            assertEquals(true,exists);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Test that calls insert single employee and checks if it exists in the database
    @Test
    @DisplayName("GivenADatabaseConnectionInsertEmployeeIntoEmployeesTable")
    public void insertEmployeeCheckIfExists(){
        //calls the create employees table method
        employeeDAO.createEmployeesTable();
        Employee testEmployee = new Employee(458749,"Mr." , "Donovan", 'R' ,"Cupueran",
                'M', "test@test.com", LocalDate.of(1997,10,24), LocalDate.of(2021,10,22),2000);
        employeeDAO.insertEmployee(testEmployee);
        Statement statement = null;
        ResultSet rs = null;
        try {
            //gets connection to the database
            Connection connection = DatabaseConnectionFactory.getConnection();
            statement = connection.createStatement();
            //query to check if employee exists
            rs = statement.executeQuery("SELECT * FROM employees WHERE employeeId = 458749");
            boolean exists = (rs.next());
            assertEquals(true,exists);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Test that checks ifa non-existent employee is in the database
    @Test
    @DisplayName("GivenADatabaseConnectionSelectNonexistentEmployeeReturnsFalse")
    public void selectNonexistentUserReturnFalse(){
        //Calls the create employees table method
        employeeDAO.createEmployeesTable();
        boolean expected = false;
        //Compares the boolean expected value(false) with the returned by the select employee method
        assertEquals(expected,employeeDAO.selectEmployee(12345));
    }

    //Test to check that existent employee is in the databse
    @Test
    @DisplayName("GivenADatabaseConnectionSelectExistentEmployeeReturnsTrue")
    public void selectExistentUserReturnTrue(){
        //calls the create employees table method
        employeeDAO.createEmployeesTable();
        //Creates sample employee and adds it to the database
        Employee testEmployee = new Employee(458749,"Mr." , "Donovan", 'R' ,"Cupueran",
                'M', "test@test.com", LocalDate.of(1997,10,24), LocalDate.of(2021,10,22),2000);
        employeeDAO.insertEmployee(testEmployee);
        //Check if inserted employee exists using insert and select employee methods
        boolean expected = true;
        assertEquals(expected,employeeDAO.selectEmployee(458749));
    }

    //Test to check if a list of employees is inserted into the database using a single thread
    @Test
    @DisplayName("GivenADatabaseConnectionInsertMultipleEmployeeIntoEmployeesTableSingleThread")
    public void insertMultipleEmployeeCheckIfExistSingleThread(){
        //calls the create employees table method
        employeeDAO.createEmployeesTable();
        HashMap<Integer, Employee> ourNames = new HashMap<Integer,Employee>();
        //Create or drop table first
        employeeDAO.createEmployeesTable();
        //create map with employee details
        readCSV("csvInputs/EmployeeRecordsLarge.csv",ourNames);
        employeeDAO.insertMultipleEmployees(ourNames);
        Statement statement = null;
        ResultSet rs = null;
        try {
            //gets connection to the database
            Connection connection = DatabaseConnectionFactory.getConnection();
            statement = connection.createStatement();
            //query to check if employees exist in the table
            rs = statement.executeQuery("SELECT * FROM employees");
            boolean exists = (rs.next());
            assertEquals(true,exists);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Test to check if a list of employees is inserted into the database using multiple threads
    @Test
    @DisplayName("GivenADatabaseConnectionInsertMultipleEmployeeIntoEmployeesTableMultipleThreads")
    public void insertMultipleEmployeeCheckIfExistMultipleThreads(){
        //calls the create employees table method
        employeeDAO.createEmployeesTable();
        HashMap<Integer, Employee> ourNames = new HashMap<Integer,Employee>();
        //Create or drop table first
        employeeDAO.createEmployeesTable();
        //create map with employee details
        ThreadedFileReader t = new ThreadedFileReader();
        t.readCSV("csvInputs/EmployeeRecordsLarge.csv",ourNames);
        Statement statement = null;
        ResultSet rs = null;
        try {
            //gets connection to the database
            Connection connection = DatabaseConnectionFactory.getConnection();
            statement = connection.createStatement();
            //query to check if employees exist in the table
            rs = statement.executeQuery("SELECT * FROM employees");
            boolean exists = (rs.next());
            assertEquals(true,exists);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
