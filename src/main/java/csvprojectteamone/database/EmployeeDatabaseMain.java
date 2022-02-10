package csvprojectteamone.database;

import csvprojectteamone.Employee;

import java.time.LocalDate;
import java.util.HashMap;

import static csvprojectteamone.ReadFile.readCSV;

public class EmployeeDatabaseMain {
    public static void main(String[] args) {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        employeeDAO.createEmployeesTable();
        Employee testEmployee = new Employee(1,"Mr." , "Donovan", 'R' ,"Cupueran",
                'M', "test@test.com", LocalDate.of(1997,10,24),LocalDate.of(2021,10,22),2000);
        employeeDAO.insertEmployee(testEmployee);

        HashMap<Integer, Employee> ourNames = new HashMap<Integer,Employee>();
        readCSV("EmployeeRecords.csv",ourNames);
        employeeDAO.insertMultipleEmployees(ourNames);
    }
}
