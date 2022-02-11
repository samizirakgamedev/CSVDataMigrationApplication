package csvprojectteamone.controller;

import csvprojectteamone.model.DataVerification;
import csvprojectteamone.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class FileReaderClass {
    public static void readCSV(String filePath, HashMap<Integer, Employee> employeeHashMap) {
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
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
                if(DataVerification.isEmployeeDataValid(employee))
                {
                    if(!employeeHashMap.containsKey(key))
                        employeeHashMap.put(key, employee);
                    else
                        FileWriterClass.writeToCSVFile("DuplicateRecords.csv", employee, "Duplicate-data");
                } else
                    FileWriterClass.writeToCSVFile("CorruptRecords.csv", employee, "Corrupt-data");
            }
            outputRecords(employeeHashMap);
        }catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    public static void outputRecords(HashMap<Integer,Employee> hash){
        int count = 0;
        for (Integer key : hash.keySet()) {
            System.out.println(key + ":" + hash.get(key));
            count++;
        }
        System.out.println(count);
    }
}
