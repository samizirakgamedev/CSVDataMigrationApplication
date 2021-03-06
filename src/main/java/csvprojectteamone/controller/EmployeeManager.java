package csvprojectteamone.controller;

import csvprojectteamone.model.Employee;
import csvprojectteamone.model.LogClass;
import csvprojectteamone.model.database.EmployeeDAOImpl;
import csvprojectteamone.view.DisplayManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.InputMismatchException;

public class EmployeeManager {
    // Initiating the File Paths.
    private static final String DUPLICATES_FILE_PATH = "csvOutputs/duplicateValues.csv";
    private static final String CORRUPT_FILE_PATH = "csvOutputs/corruptValues.csv";
    private static final String SMALL_FILE_PATH = "csvInputs/EmployeeRecords.csv";
    private static final String LARGE_FILE_PATH = "csvInputs/EmployeeRecordsLarge.csv";

    //Creating the HashMap.
    HashMap<Integer, Employee> employeeHashMap = new HashMap<>();

    //Creating the 'load' method to use the 'resetTheDatabaseAndFiles' and 'reademployeesLoad' methods.
    public void load() {
        try {
            resetTheDatabaseAndFiles();
            long start = System.nanoTime();
            readEmployeesLoad();
            long end = System.nanoTime();
            DisplayManager.displayMessage("\n=============================================================================\n\n"
                                                + " The data has been read from a CSV file and has been imported into a database. \n" +
                                                    " This process took " + (end - start)/1000000000.0 + " seconds");
        } catch(Exception e){ //Catches a general exception.
            LogClass.logError("Loading the data from a CSV to a database failed: " + e.getMessage() );
        }
    }

    // Create the resetTheDatabaseAndFiles method to reset the database when the programme is run.
    public void resetTheDatabaseAndFiles(){
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        try {
            employeeDAO.createEmployeesTable();
            Files.deleteIfExists(Paths.get(DUPLICATES_FILE_PATH));
            Files.deleteIfExists(Paths.get(CORRUPT_FILE_PATH));
        } catch (IOException e) { //Catches an IOException.
            LogClass.logError("The database and other files could not be reset: " + e.getMessage());
        }
    }

    // Loads and read the large CSV file for the 'ThreadedFileReader'.
    public void readEmployeesLoad() {
        ThreadedFileReader.readCSV(LARGE_FILE_PATH, employeeHashMap);
    }

    // Takes in a 'filePath' and reads the CSV file for the non-thread 'FileReader'.
    public void readEmployeesNonThreaded(String filePath) {
        FileReader.readCSV(filePath, employeeHashMap);
    }

    // Takes in a 'filePath' and reads the CSV file for the 'ThreadedFileReader'.
    public void readEmployeesThreaded(String filePath) {
        ThreadedFileReader.readCSV(filePath, employeeHashMap);
    }

    // Creates a method to select a single employee from the database.
    public void selectEmployee() throws InputMismatchException, NullPointerException
    {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        employeeDAO.selectEmployee(InputManager.getInteger());
    }

    // Creates a method that will select all employees from the database.
    public void selectAllEmployees() throws InputMismatchException, NullPointerException
    {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        employeeDAO.selectAllEmployees();
    }

    // Creates a method to count the number of employee records in the database.
    public void countEmployees() {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        int rows = employeeDAO.countRows();
        //employeeDAO.closeConnection();
    }


}
