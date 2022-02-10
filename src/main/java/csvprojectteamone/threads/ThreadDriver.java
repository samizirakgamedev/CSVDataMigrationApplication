package csvprojectteamone.threads;





import csvprojectteamone.Employee;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ThreadDriver {
    public static void main(String[] args) {
        HashMap<Integer, Employee> map = new HashMap<>();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("EmployeeRecords.csv"));
            br.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }

        MyThread t1 = new MyThread("t1", map, br);
        Thread th1 = new Thread(t1);
        MyThread t2 = new MyThread("t2", map, br);
        Thread th2 = new Thread(t2);
        th1.start();
        th2.start();
        try { //Temporarily breaks the thread and clears the interruption by throwing this exception
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(Employee em: map.values()){
            System.out.println(em.toString()); //converts the employees to a string.
        }
        System.out.println(map.size());//gets the map size

    }
}
