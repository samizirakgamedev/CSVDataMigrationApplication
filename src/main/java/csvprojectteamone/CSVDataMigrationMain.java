package csvprojectteamone;

import csvprojectteamone.model.Employee;
import csvprojectteamone.controller.Thread;

import java.util.HashMap;

import static csvprojectteamone.controller.FileReader.readCSV;

public class CSVDataMigrationMain {
    public static void main(String[] args) {
        HashMap<Integer, Employee> ourNames = new HashMap<Integer,Employee>();
        readCSV("EmployeeRecords.csv",ourNames);





//
//        DisplayManager displayManager = new DisplayManager();
//        displayManager.getStringFromUser(
//                "Please enter the filepath of the CSV file you want to read and press \'Enter\':",
//                "| E.g.\"EmployeeRecords.csv\" |");













//        HashMap<Integer, Employee> map = new HashMap<>();
//        BufferedReader br = null;
//        try{
//            br = new BufferedReader(new FileReader("EmployeeRecords.csv"));
//            br.readLine();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//        Thread t1 = new Thread("t1", map, br);
//        java.lang.Thread th1 = new java.lang.Thread(t1);
//        Thread t2 = new Thread("t2", map, br);
//        java.lang.Thread th2 = new java.lang.Thread(t2);
//        th1.start();
//        th2.start();
//        try { //Temporarily breaks the thread and clears the interruption by throwing this exception
//            th1.join();
//            th2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for(Employee em: map.values()){
//            System.out.println(em.toString()); //converts the employees to a string.
//        }
//        System.out.println(map.size());//gets the map size

    }
}
