package csvprojectteamone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ReadFile {
    public static void readCSV(String filePath, HashMap<Integer, Employee> employeeHashMap) {
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
            String headerLine = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 10);
                if (parts.length == 10) {
                    int key = Integer.parseInt(parts[0]);
                    Employee e = new Employee(
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
                    employeeHashMap.put(key, e);
                } else {
//                    System.out.println("ignoring line: " + line);
                }
            }
            int count = 0;
            for (Integer key : employeeHashMap.keySet()) {
//                System.out.println(key + ":" + employeeHashMap.get(key));
                count++;
            }
//            System.out.println(count);
        }catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
