package csvprojectteamone.controller;

import csvprojectteamone.model.Employee;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {
    // Method for writing to a CSV file in a directory of choice.
    public static void writeToCSVFile(String filePath, Employee employee, String message) {
        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(filePath,true))){
            synchronized (bw){
                bw.write(employee.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
