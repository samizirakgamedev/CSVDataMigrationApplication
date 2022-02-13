package csvprojectteamone.controller;

import csvprojectteamone.model.DataVerification;
import csvprojectteamone.model.Employee;
import csvprojectteamone.model.LogClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class FileReader {
    private static int duplicateCount = 0;
    private static int corruptCount = 0;
    // Method for reading in a CSV file from a directory.
    // This adds each record to a new 'Employee'' object  and then add those objects to the employeeHashmap collection.
    public static void readCSV(String filePath, HashMap<Integer, Employee> employeeHashMap) {
        LogClass.logInfo("readCSV called from FileReader");
        long start = System.nanoTime(); // Timing method start
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));) {
            LogClass.logInfo("The CSV file at path \"" + filePath + "\" has been found for FileReader");
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
            }
            outputRecords(employeeHashMap);
            LogClass.logInfo("The CSV file has been read. There were " + duplicateCount + " duplicates and " + corruptCount + " corrupted files found");
            LogClass.logInfo("These can be found in the csvOutputs folder.");
        }catch (IOException | NullPointerException e) {
            e.printStackTrace(); // we can remove this line
            LogClass.logError("FileReader has thrown an " + e + " exception type"); //in place of e, we can call the e.getMessage()
        }
        long end = System.nanoTime(); // timing method ends
        LogClass.logInfo("It took " + (TimeUnit.MICROSECONDS.convert(end-start, TimeUnit.NANOSECONDS)) + " Microseconds to run the non-threaded FileReader 'readCSV' method.");//converts to microseconds
    }
    // Method for outputting all records that are stored within the employee HashMap.
    public static void outputRecords(HashMap<Integer,Employee> hash){
        LogClass.logInfo("outputRecords method called from FileReader");
        int count = 0;
        for (Integer key : hash.keySet()) {
            System.out.println(key + ":" + hash.get(key));
            count++;
        }
        System.out.println(count);
        LogClass.logInfo("Clean records have been counted and stored for FileReader");
    }
    // Method for checking that an employee's record is not corrupt or a duplicate of an already existing record within the HashMap.
    public static void dataFiltration(Employee e, Integer id,HashMap<Integer,Employee> map){
        LogClass.logInfo("Validation method checking for corrupted/duplicate records called in FileReader");
        if(DataVerification.isEmployeeDataValid(e)) {
            if(!map.containsKey(id))
              synchronized (map){
                  map.put(id, e);
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
}
