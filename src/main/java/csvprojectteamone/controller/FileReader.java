package csvprojectteamone.controller;

import csvprojectteamone.model.DataVerification;
import csvprojectteamone.model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class FileReader {
    // Method for reading in a CSV file from a directory.
    // This adds each record to a new 'Employee'' object  and then add those objects to the employeeHashmap collection.
    public static void readCSV(String filePath, HashMap<Integer, Employee> employeeHashMap) {
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));) {
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
        }catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    // Method for outputting all records that are stored within the employee HashMap.
    public static void outputRecords(HashMap<Integer,Employee> hash){
        int count = 0;
        for (Integer key : hash.keySet()) {
            System.out.println(key + ":" + hash.get(key));
            count++;
        }
        System.out.println(count);
    }
    // Method for checking that an employee's record is not corrupt or a duplicate of an already existing record within the HashMap.
    public static void dataFiltration(Employee e, Integer id,HashMap<Integer,Employee> map){
        if(DataVerification.isEmployeeDataValid(e))
        {
            if(!map.containsKey(id))
              synchronized (map){
                  map.put(id, e);
              }
            else
                FileWriter.writeToCSVFile("DuplicateRecords.csv", e, "Duplicate-data");
        } else
            FileWriter.writeToCSVFile("CorruptRecords.csv", e, "Corrupt-data");
    }
}
