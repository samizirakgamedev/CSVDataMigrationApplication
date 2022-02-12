package csvprojectteamone;

import csvprojectteamone.model.Employee;
import csvprojectteamone.view.DisplayManager;
import java.util.HashMap;
import static csvprojectteamone.controller.ThreadedFileReader.readCSV;

public class CSVDataMigrationMain {
    public static void main(String[] args) {
        HashMap<Integer, Employee> ourNames = new HashMap<Integer,Employee>();
        readCSV("csvInputs/EmployeeRecords.csv",ourNames);

        DisplayManager displayManager = new DisplayManager();

        String[] availableOptions = {"Insert Employees csv to Database", "Select Employee by ID", "Select Employee by ID", "Exit"};

        displayManager.getStringFromUser(
                "Please enter the filepath of the CSV file you want to read and press \'Enter\':",
                "| E.g.\"EmployeeRecords.csv\" |");
        displayManager.outputReadStatus();
        displayManager.outputWriteStatus();
        displayManager.outputFileStats();

        int choice = displayManager.getChoiceFromUser("Please select how you wish to interact with the records that have been read", availableOptions);
    }
}

//            System.out.println("\nPlease Enter number to select an option:\n" +
//                    "|---------1. Insert Employees csv to Database---------|\n" +
//                    "|---------2. Select Employee by ID----------|" +
//                    "\n|---------3. Select All Employees from database---|" +
//                    "\n|---------4. Exit---------------|\n");