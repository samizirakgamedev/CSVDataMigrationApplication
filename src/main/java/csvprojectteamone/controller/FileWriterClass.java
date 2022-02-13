package csvprojectteamone.controller;

import csvprojectteamone.model.Employee;
import csvprojectteamone.model.LogClass;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterClass {
    // Method for writing to a CSV file in a directory of choice.
    public static void writeToCSVFile(String filePath, Employee employee, String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true))){
            synchronized (bw){
                bw.write(employee.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            LogClass.logError("FileWriter has thrown an " + e + " exception type");
        }
    }
}
