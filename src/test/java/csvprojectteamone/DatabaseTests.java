package csvprojectteamone;

import csvprojectteamone.model.database.DatabaseConnectionFactory;
import csvprojectteamone.model.database.EmployeeDAOImpl;
import csvprojectteamone.model.Employee;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

import static csvprojectteamone.controller.FileReaderClass.readCSV;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DatabaseTests {
    private static EmployeeDAOImpl employeeDAO;

    @BeforeAll
    static void setUp(){
        employeeDAO = new EmployeeDAOImpl();
    }

    @AfterAll
    static void cleanUp(){
        try {
            DatabaseConnectionFactory.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("GivenADatabaseConnectionCreatesEmployeesTable")
    public void createTableCheckIfExists(){
        employeeDAO.createEmployeesTable();
        try {
            Connection connection = DatabaseConnectionFactory.getConnection();
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "employees", new String[] {"TABLE"} );
            boolean exists = (resultSet.next());
            assertEquals(true,exists);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("GivenADatabaseConnectionInsertEmployeeIntoEmployeesTable")
    public void insertEmployeeCheckIfExists(){
        employeeDAO.createEmployeesTable();
        Employee testEmployee = new Employee(458749,"Mr." , "Donovan", 'R' ,"Cupueran",
                'M', "test@test.com", LocalDate.of(1997,10,24), LocalDate.of(2021,10,22),2000);
        employeeDAO.insertEmployee(testEmployee);
        Statement statement = null;
        ResultSet rs = null;
        try {
            Connection connection = DatabaseConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM employees WHERE employeeId = 458749");
            boolean exists = (rs.next());
            assertEquals(true,exists);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("GivenADatabaseConnectionInsertMultipleEmployeeIntoEmployeesTable")
    public void insertMultipleEmployeeCheckIfExist(){
        employeeDAO.createEmployeesTable();
        HashMap<Integer, Employee> ourNames = new HashMap<Integer,Employee>();
        //Create or drop table first
        employeeDAO.createEmployeesTable();
        //create map with employee details
        readCSV("EmployeeRecords.csv",ourNames);
        employeeDAO.insertMultipleEmployees(ourNames);
        Statement statement = null;
        ResultSet rs = null;
        try {
            Connection connection = DatabaseConnectionFactory.getConnection();
            statement = connection.createStatement();
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
