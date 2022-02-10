package csvprojectteamone;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterClass {
    public static void writeToCSVFile(String filePath, Employee employee, String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            bw.write(employee.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
