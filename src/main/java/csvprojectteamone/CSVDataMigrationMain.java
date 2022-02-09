package csvprojectteamone;

import java.util.ArrayList;
import java.util.HashMap;

import static csvprojectteamone.ReadFile.readCSV;

public class CSVDataMigrationMain {
    public static void main(String[] args) {
        HashMap<Integer, Employee> ourNames = new HashMap<Integer,Employee>();
        readCSV("EmployeeRecords.csv",ourNames);
    }
}
