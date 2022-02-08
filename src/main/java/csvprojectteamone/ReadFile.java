package csvprojectteamone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadFile {

    public static String readCSV(String filePath){
        ArrayList<String[]> employeeList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));){
            String line;
            while((line = br.readLine()) != null){
                employeeList.add(line.split(","));
            }
            for(String[] s:employeeList){
                System.out.println(Arrays.toString(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
