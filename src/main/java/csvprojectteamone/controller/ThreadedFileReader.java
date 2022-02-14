package csvprojectteamone.controller;

import csvprojectteamone.model.DataVerification;
import csvprojectteamone.model.Employee;
import csvprojectteamone.model.LogClass;
import csvprojectteamone.model.database.EmployeeDAOImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class ThreadedFileReader {
    public static String line;
    private static final int TOTAL_THREADS = 21;
    static ArrayList<Thread> threads = new ArrayList<>();
    private static HashMap<Integer, Employee> threadedRecords = new HashMap<>();
    private static int duplicateCount = 0;
    private static int corruptCount = 0;
    private static int deleteCount = 0;
    private static int countCount = 0;

    // Method for reading in a CSV file from a directory.
    // This adds each record to a new 'Employee'' object  and then add those objects to the employeeHashmap collection.
    public static void readCSV(String filePath, HashMap<Integer, Employee> employeeHashMap) {
        LogClass.logInfo("readCSV called from ThreadedFileReader.");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));) {
            LogClass.logInfo("The CSV file at path \"" + filePath + "\" has been found for ThreadedFileReader.");
            long entryCount = Files.lines(Paths.get(filePath)).count();
            String headerLine = reader.readLine();
            while ((line = reader.readLine()) != null) {
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
                        LocalDate.parse(parts[7], formatter),
                        LocalDate.parse(parts[8], formatter),
                        Double.parseDouble(parts[9]));
                dataFiltration(employee, key, employeeHashMap);
                if (threadedRecords.size() > entryCount / TOTAL_THREADS) {
                    createNewThreadAndAddToDatabase(threadedRecords);
                    threadedRecords = new HashMap<>();
                }
            }
            createNewThreadAndAddToDatabase(threadedRecords);
            deleteThreads();
            LogClass.logInfo("The CSV file has been read.");
            LogClass.logWarn("There were " + duplicateCount + " duplicates and " + corruptCount + " corrupted records found.");
            LogClass.logInfo("Duplicate and corrupt records can be found in the csvOutputs folder.");
            System.out.println(countCount);
            System.out.println(deleteCount);
            duplicateCount = 0;
            corruptCount = 0;
            //outputRecords(employeeHashMap);
        } catch (IOException | NullPointerException | InterruptedException e) {
            LogClass.logError("ThreadedFileReader has thrown an " + e + " exception type.");
        }
    }
    // Method for outputting all records that are stored within the employee HashMap.
    public static void outputRecords(HashMap<Integer, Employee> hash) {
        LogClass.logInfo("outputRecords method called from ThreadedFileReader.");
        int count = 0;
        for (Integer key : hash.keySet()) {
            System.out.println(key + ":" + hash.get(key));
            count++;
        }
        System.out.println(count);
        LogClass.logInfo("Clean record have been counted and stored for ThreadedFileReader.");
    }
    // Method for checking that an employee's record is not corrupt or a duplicate of an already existing record within the HashMap.
    public static void dataFiltration(Employee e, Integer id, HashMap<Integer, Employee> map) {

        String[] partArray = line.split(",");
        if (DataVerification.isEmployeeDataValid(e)) {
            if (!map.containsKey(Integer.parseInt(partArray[0]))) {
                map.put(Integer.parseInt(partArray[0]), e);
                threadedRecords.put(Integer.parseInt(partArray[0]), e);
            } else {
                FileWriterClass.writeToCSVFile("csvOutputs/DuplicateRecords.csv", e, "Duplicate-data");
                duplicateCount++;
            }
        } else {
            FileWriterClass.writeToCSVFile("csvOutputs/CorruptRecords.csv", e, "Corrupt-data");
            corruptCount++;
        }
    }
    // Creates a method for creating new threads (set amount) and adding these threads to the database.
    private static void createNewThreadAndAddToDatabase(HashMap<Integer, Employee> employeeHashMap) throws InterruptedException {
        Object lock = new HashMap<Integer, Employee>();
        Runnable runnable = () -> {
            EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
            synchronized (lock) {
                employeeDAO.insertMultipleEmployees(employeeHashMap);
            }
        };
        // Initiates the threads.
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Thread add, is after this step.");
        threads.add(thread);
        LogClass.logInfo("New Threads have been created for ThreadedFileReader.");
    }
    //Creates a method for deleting the threads created in the above method.
    private static void deleteThreads() {
        countCount = threads.size();
        while (threads.size() > 0 && threads.size() <=21 && threadedRecords != null) {
            threads.removeIf((thread -> !thread.isAlive()));
                deleteCount++;
        }
        LogClass.logInfo("Threads have been deleted. END");
    }
}
