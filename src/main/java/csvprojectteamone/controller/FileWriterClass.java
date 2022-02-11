package csvprojectteamone.controller;

import csvprojectteamone.model.Employee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterClass {
    public static void writeToCSVFile(String filePath, Employee employee, String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true))){
            bw.write(employee.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
