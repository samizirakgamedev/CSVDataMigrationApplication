package csvprojectteamone;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ReadFile {
    public static void readCSV(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));){
            String line;
            while((line = br.readLine()) != null){
                String[] csvData = line.split(",");
                System.out.println(Arrays.toString(csvData));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
