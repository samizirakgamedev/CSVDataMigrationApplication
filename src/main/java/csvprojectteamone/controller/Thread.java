package csvprojectteamone.controller;

import csvprojectteamone.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class Thread implements Runnable {
    private static Logger logger = LogManager.getLogger("CSV-DM Logger:");
    // String for naming the thread.
    private String threadName;
    // HashMap that we will pass into the thread object - this will contain employee records.
    private HashMap<Integer, Employee> map;
    // For reading the file line by line.
    private BufferedReader inFile;

    //Constructor for creating a new thread.
    public Thread(String threadName, HashMap<Integer, Employee> map, BufferedReader inFile){
        this.threadName = threadName;
        this.map = map;
        this.inFile = inFile;
    }
    // Overridden method from the implemented 'Runnable' interface. Called for processing the file in its separately executed thread object.
    @Override
    public void run() {
        logger.info("Thread method for implementing the 'Runnable' interface has been called");
        //processFile();
    }
}
