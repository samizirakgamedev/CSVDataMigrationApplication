package csvprojectteamone.controller;

import csvprojectteamone.model.Employee;
import csvprojectteamone.model.LogClass;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FileWriter {
    // Method for writing to a CSV file in a directory of choice.
    public static void writeToCSVFile(String filePath, Employee employee, String message) {
        long start = System.nanoTime(); //This timing method runs in a loop
        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(filePath,true))){
            synchronized (bw){
                bw.write(employee.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            LogClass.logError("FileWriter has thrown an " + e + " exception type");
        }
        long end = System.nanoTime(); // ends the timing implementation
        LogClass.logInfo("It took " + (TimeUnit.MICROSECONDS.convert(end-start, TimeUnit.NANOSECONDS)) + " Microseconds to run the threaded FileWriter 'writeToCSVFile' method."); // converts to microseconds
    }
}
