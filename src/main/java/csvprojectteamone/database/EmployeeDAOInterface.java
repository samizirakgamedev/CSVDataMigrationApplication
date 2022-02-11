package csvprojectteamone.database;

import csvprojectteamone.Employee;

import java.util.HashMap;

public interface EmployeeDAOInterface {

    //Create table
    void createEmployeesTable();

    //Create or insert a new Employee
    void insertEmployee(Employee employee);

    //Insert multiple employees
    void insertMultipleEmployees(HashMap<Integer,Employee> employeesList);

    //Select Employee by id
    void selectEmployee(int employeeId);

    //Select all Employees
    void selectAllEmployees();

    }
