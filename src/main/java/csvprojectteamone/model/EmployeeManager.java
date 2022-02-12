package csvprojectteamone.model;

import csvprojectteamone.model.database.EmployeeDAOImpl;

import java.util.HashMap;

import static csvprojectteamone.controller.FileReader.readCSV;

public class EmployeeManager {
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    public void insertCSV_ToDatabase(HashMap<Integer, Employee> employeeRecords){

        employeeDAO.createEmployeesTable();
        employeeDAO.insertMultipleEmployees(employeeRecords);

    }
}
