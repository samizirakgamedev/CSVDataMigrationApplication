package csvprojectteamone.threads;

import csvprojectteamone.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MyThread implements Runnable {

    private String threadName;
    private HashMap<Integer, Employee> map;
    private BufferedReader inFile;



    public MyThread(String threadName, HashMap<Integer, Employee> map, BufferedReader inFile){
        this.threadName = threadName;
        this.map = map;
        this.inFile = inFile;
    }

    @Override
    public void run() {
        processFile();

    }

    // Put the processFile in Suyash's FileReader and FileWriter class.

    public void processFile(){
        String line;
        try {
                while ((line = inFile.readLine()) != null) {
                    String[] values = line.split(",");
                    synchronized (map){
                        map.put(new Employee(values[1], values[2]));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
