package csvprojectteamone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;

public class FileReaderClass {
    //need DAO Class to complete this class, so that we can store duplicate and corrupt records in a separate file
    //Under construction
    //private static final HashMap<String, Employee> records = new HashMap<>();

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
                //employeeHashMap.put(key, employee);
//
                if(DataVerification.isEmployeeDataValid(employee)) //check!!
                {
                    if(employeeHashMap.containsKey(key))
                    {
                        employeeHashMap.put(key, employee);
                    }
                    else
                    {
                        FileWriterClass.writeToCSVFile("DuplicateRecords.csv", employee, "Duplicate-data");
                    }
                } else

                {
                    FileWriterClass.writeToCSVFile("CorruptRecords.csv", employee, "Corrupt-data");
                }
            }



//                if (parts.length == 10) {
//                    int key = Integer.parseInt(parts[0]);
//                    Employee e = new Employee(
//                            key,
//                            parts[1],
//                            parts[2],
//                            parts[3].charAt(0),
//                            parts[4],
//                            parts[5].charAt(0),
//                            parts[6],
//                            LocalDate.parse(parts[7],formatter),
//                            LocalDate.parse(parts[8],formatter),
//                            Double.parseDouble(parts[9]));
//                    employeeHashMap.put(key, e);
//                } else {
//                    System.out.println("ignoring line: " + line);
//                }
//            }


            //give the command to insert the filtered data to a database

            int count = 0;
            for (Integer key : employeeHashMap.keySet()) {
                System.out.println(key + ":" + employeeHashMap.get(key));
                count++;
            }
            System.out.println(count);
        }catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}
