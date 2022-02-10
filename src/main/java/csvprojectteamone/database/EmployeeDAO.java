package csvprojectteamone.database;

import csvprojectteamone.Employee;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface EmployeeDAO {

    //Create table
    void createEmployeesTable();

    //Create or insert a new Employee
    void insertEmployee(Employee employee);

    //Insert multiple employees
    void insertMultipleEmployees(HashMap<Integer,Employee> employeesList);

    //Update Employee
    boolean updateEmployee(int employeeId);


    //Select Employee by id
    void selectEmployee(int employeeId);

    //Select all Employees
    void selectAllEmployees();

    //Delete Employee
    boolean deleteEmployee(int employeeId);

    }
