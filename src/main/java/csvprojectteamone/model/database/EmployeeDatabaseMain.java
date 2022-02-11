package csvprojectteamone.model.database;

import csvprojectteamone.model.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import static csvprojectteamone.controller.FileReader.readCSV;

public class EmployeeDatabaseMain {
    public static void main(String[] args) throws SQLException, IOException {
//
//        TEST INSERT EMPLOYEE
//
//        Employee testEmployee = new Employee(458749,"Mr." , "Donovannnn", 'R' ,"Cupueran",
//                'M', "test@test.com", LocalDate.of(1997,10,24),LocalDate.of(2021,10,22),2000);
//        employeeDAO.insertEmployee(testEmployee);
//
//
        //Create table if not exists
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
                    readCSV("EmployeeRecords.csv",ourNames);
                    //Insert employees data into database
                    employeeDAO.insertMultipleEmployees(ourNames);
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
