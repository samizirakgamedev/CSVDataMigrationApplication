package csvprojectteamone.view;

import java.util.Scanner;

public class DisplayManager {
    // For Output.
    StringBuilder builder = new StringBuilder();
    // For Input.
    Scanner scanner = new Scanner(System.in);
    // String for separating the console outputs.
    String separateConsoleOutput = "==================================";

    public void outputReadAndWriteStatus(){
        // Output that the reading of the file was successful.
        // Output that the writing to the database was successful.
    }
    public void outputFileStats(){
        System.out.println("");
        // Output: How many duplicates and corrupt data (getDuplicates)
        // Output: How many records were added to the database (getRecordCount)
    }
    public void outputAvailableOptions(){
        System.out.println("");
        // Option for getting employee via ID from the database.
        // Option to view all duplicate and corrupt data from the database.
    }
    public String getStringFromUser(String message, String messageTwo){
        System.out.println(message);
        if (messageTwo != null)
            System.out.println(messageTwo);
        String inputString = scanner.next();
        System.out.println(separateConsoleOutput);
        return inputString;
    }
    public int getIntFromUser(String message, String messageTwo){
        System.out.println(message);
        if (messageTwo != null)
            System.out.println(messageTwo);
        int inputInt = scanner.nextInt();
        System.out.println(separateConsoleOutput);
        return inputInt;
    }
}
