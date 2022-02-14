package csvprojectteamone.model.database;

import csvprojectteamone.controller.ThreadedFileReader;
import csvprojectteamone.model.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class EmployeeDatabaseMain {
    public static void main(String[] args) throws SQLException, IOException {
        //Initialises the employeeDAO implementation
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        //Creates two Scanners to get the users input and select and option
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        boolean exit = false;

        HashMap<Integer, Employee> ourNames = new HashMap<Integer,Employee>();
        while(!exit){
            //Prints out the possible options the user has to interact with the database
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
                    //Insert employees data into database
                    ThreadedFileReader t = new ThreadedFileReader();
                    t.readCSV("csvInputs/EmployeeRecordsLarge.csv",ourNames);
//                    employeeDAO.insertMultipleEmployees(ourNames);
                }
                case 2 -> {
                    //Select employee id and prints out info to the console
                    System.out.println("Enter employee id");
                    int employeeId = scanner2.nextInt();
                    employeeDAO.selectEmployee(employeeId);
                }
                case 3 -> {
                    //Selects all employees and prints out info to the console
                    employeeDAO.selectAllEmployees();
                }
                case 4 -> {
                    //Exists the menu and the application
                    System.out.println("Goodbye");
                    DatabaseConnectionFactory.closeConnection();
                    exit = true;
                }
            }
        }
        System.exit(0);
    }
}
