package csvprojectteamone.controller;

import csvprojectteamone.model.Employee;
import csvprojectteamone.model.LogClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {

    //private static Logger logger = LogManager.getLogger("CSV-DM Logger:"); -> Get rid of this

    // Method for writing to a CSV file in a directory of choice.
    public static void writeToCSVFile(String filePath, Employee employee, String message) {
        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(filePath,true))){
            synchronized (bw){
                bw.write(employee.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            LogClass.logError("FileWriter has thrown an " + e + " exception type");
        }
    }
}
