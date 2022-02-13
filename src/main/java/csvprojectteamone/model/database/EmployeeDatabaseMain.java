package csvprojectteamone.model.database;

import csvprojectteamone.controller.ThreadedFileReader;
import csvprojectteamone.model.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class EmployeeDatabaseMain {
    public static void main(String[] args) throws SQLException, IOException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        boolean exit = false;

        HashMap<Integer, Employee> ourNames = new HashMap<Integer,Employee>();
        while(!exit){
            System.out.println("\nPlease Enter number to select an option:\n" +
                    "|---------1. Insert Employees csv to Database---------|\n" +
                    "|---------2. Select Employee by ID----------|" +
                    "\n|---------3. Select All Employees from database---|" +
                    "\n|---------4. Exit---------------|\n");
            int desiredOption = scanner.nextInt();
            switch (desiredOption){
                case 1 -> {
                    //Create or drop table first
                    employeeDAO.createEmployeesTable();
                    //create map with employee details
//                    readCSV("csvInputs/EmployeeRecords.csv",ourNames);
                    //Insert employees data into database
                    ThreadedFileReader t = new ThreadedFileReader();
                    t.readCSV("csvInputs/EmployeeRecordsLarge.csv",ourNames);
//                    employeeDAO.insertMultipleEmployees(ourNames);
                }
                case 2 -> {
                    System.out.println("Enter employee id");
                    int employeeId = scanner2.nextInt();
                    employeeDAO.selectEmployee(employeeId);
                }
                case 3 -> {
                    employeeDAO.selectAllEmployees();
                }
                case 4 -> {
                    System.out.println("Goodbye");
                    DatabaseConnectionFactory.closeConnection();
                    exit = true;
                }
            }
        }
        System.exit(0);
    }
}
