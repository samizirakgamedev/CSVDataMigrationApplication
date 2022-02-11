package csvprojectteamone.controller;

import csvprojectteamone.model.DataVerification;
import csvprojectteamone.model.Employee;
import csvprojectteamone.model.database.EmployeeDAOImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadedFileReader {
    // Initialising the logger.
    private static Logger logger = LogManager.getLogger("CSV-DM Logger:");
    ////        logger.warn("");
    ////        logger.info("");
    ////        logger.error("");

    private static final int TOTAL_THREADS = 10;
    static ArrayList<java.lang.Thread> threads = new ArrayList();
    private static HashMap<Integer, Employee> threadedRecords = new HashMap<>();
    private static int duplicateCount = 0;
    private static int corruptCount = 0;

    // Method for reading in a CSV file from a directory.
    // This adds each record to a new 'Employee'' object  and then add those objects to the employeeHashmap collection.
    public static void readCSV(String filePath, HashMap<Integer, Employee> employeeHashMap) {
        logger.info("readCSV called from ThreadedFileReader.");
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));) {
            logger.info("The CSV file at path \"" + filePath + "\" has been found for ThreadedFileReader.");
            long entryCount = Files.lines(Paths.get(filePath)).count();

            String headerLine = reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");

                int key = Integer.parseInt(parts[0]);

                Employee employee = new Employee(
                        key,
                        parts[1],
                        parts[2],
                        parts[3].charAt(0),
                        parts[4],
                        parts[5].charAt(0),
                        parts[6],
                        LocalDate.parse(parts[7],formatter),
                        LocalDate.parse(parts[8],formatter),
                        Double.parseDouble(parts[9]));
                dataFiltration(employee,key,employeeHashMap);

                if(threadedRecords.size() > entryCount / TOTAL_THREADS) {

                    threadedRecords = new HashMap<>();
                }
            }
            createNewThreadAndAddToDatabase(threadedRecords);
            deleteThreads();
            logger.info("The CSV file has been read.");
            logger.warn("There were " + duplicateCount + " duplicates and " + corruptCount + " corrupted records found.");
            logger.info("Duplicate and corrupt records can be found in the csvOutputs folder.");
            duplicateCount = 0;
            corruptCount = 0;

            //outputRecords(employeeHashMap);
        }catch (IOException | NullPointerException e) {
            e.printStackTrace();
            logger.error("ThreadedFileReader has thrown an " + e + " exception type.");
        }
    }
    // Method for outputting all records that are stored within the employee HashMap.
    public static void outputRecords(HashMap<Integer,Employee> hash){
        logger.info("outputRecords method called from ThreadedFileReader.");
        int count = 0;
        for (Integer key : hash.keySet()) {
            System.out.println(key + ":" + hash.get(key));
            count++;
        }
        System.out.println(count);
        logger.info("Clean record have been counted and stored for ThreadedFileReader.");
    }
    // Method for checking that an employee's record is not corrupt or a duplicate of an already existing record within the HashMap.
    public static void dataFiltration(Employee e, Integer id,HashMap<Integer,Employee> map){
        if(DataVerification.isEmployeeDataValid(e))
        {
            if(!map.containsKey(id)){
//                synchronized (map){
                    map.put(id, e);

                    threadedRecords.put(id, e);

                }

            else {
                FileWriter.writeToCSVFile("csvOutputs/DuplicateRecords.csv", e, "Duplicate-data");
                duplicateCount++;
            }
        }
        else {
            FileWriter.writeToCSVFile("csvOutputs/CorruptRecords.csv", e, "Corrupt-data");
            corruptCount++;
        }
    }

    private static void deleteThreads(){
        while (threads.size() > 0) {
            threads.removeIf((thread -> !thread.isAlive()));
        }
        logger.info("Threads have been deleted.");
    }

    private static void createNewThreadAndAddToDatabase(HashMap<Integer, Employee> employeeHashMap){
        Object lock = new Object();

        Runnable runnable = () -> {
            EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
            synchronized (lock) {employeeDAO.insertMultipleEmployees(employeeHashMap);}};
        java.lang.Thread thread = new java.lang.Thread(runnable);
        thread.start();
        threads.add(thread);
        logger.info("New Threads have been created for ThreadedFileReader.");
    }
}
