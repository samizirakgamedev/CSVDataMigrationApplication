package csvprojectteamone.controller;

import csvprojectteamone.model.Employee;
import csvprojectteamone.model.LogClass;
import csvprojectteamone.model.database.EmployeeDAOImpl;
import csvprojectteamone.view.DisplayManagerOther;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.InputMismatchException;

public class EmployeeManagerOther {

    private static final String DUPLICATES_FILE_PATH = "csvOutputs/duplicateValues.csv";
    private static final String CORRUPT_FILE_PATH = "csvOutputs/corruptValues.csv";
    private static final String SMALL_FILE_PATH = "csvInputs/EmployeeRecords.csv";
    private static final String LARGE_FILE_PATH = "csvInputs/EmployeeRecordsLarge.csv";

    HashMap<Integer, Employee> employeeHashMap = new HashMap<>();

    public void load() {
        try {
            resetTheDatabaseAndFiles();
            long start = System.nanoTime();
            readEmployeesLoad();
            long end = System.nanoTime();
            DisplayManagerOther.displayMessage("\n=============================================================================\n\n"
                                                + " The data has been read from a CSV file and has been imported into a database. \n" +
                                                    " This process took " + (end - start)/1000000000.0 + " seconds");
        } catch(Exception e){
            LogClass.logError("Loading the data from a CSV to a database failed: " + e.getMessage() );
        }
    }

    public void resetTheDatabaseAndFiles(){
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        try {
            employeeDAO.createEmployeesTable();
            Files.deleteIfExists(Paths.get(DUPLICATES_FILE_PATH));
            Files.deleteIfExists(Paths.get(CORRUPT_FILE_PATH));
        } catch (IOException e) {
            LogClass.logError("The database and other files could not be reset: " + e.getMessage());
        }
    }

    public void readEmployeesLoad() {
        ThreadedFileReader.readCSV(LARGE_FILE_PATH, employeeHashMap);
    }

    public void readEmployeesNonThreaded(String filePath) {
        FileReader.readCSV(filePath, employeeHashMap);
    }

    public void readEmployeesThreaded(String filePath) {
        ThreadedFileReader.readCSV(filePath, employeeHashMap);
    }

    public void selectEmployee() throws InputMismatchException, NullPointerException
    {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        employeeDAO.selectEmployee(InputManagerOther.getInteger());
    }

    public void selectAllEmployees() throws InputMismatchException, NullPointerException
    {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        employeeDAO.selectAllEmployees();
    }


//    public void countEmployees() {
//        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
//        int rows = employeeDAO.countRows();
//        employeeDAO.closeConnection();
//    }


}
