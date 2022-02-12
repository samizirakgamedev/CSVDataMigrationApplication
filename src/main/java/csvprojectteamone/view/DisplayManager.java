package csvprojectteamone.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DisplayManager {
    // For Output.
    StringBuilder builder = new StringBuilder();
    // For Input.
    Scanner scanner = new Scanner(System.in);
    // String for separating the console outputs.
    String separateConsoleOutput = "==================================";

    public void outputReadStatus() {
        // Output that the writing to the database was successful.
    }
    public void outputWriteStatus() {
        // Output that the writing to the database was successful.
    }
    public void outputFileStats() {
        System.out.println("");
        // Output: How many duplicates and corrupt data (getDuplicates)
        // Output: How many records were added to the database (getRecordCount)
    }

    public void outputAvailableOptions() {
        System.out.println("");
        // Option for getting employee via ID from the database.
        // Option to view all duplicate and corrupt data from the database.
    }

    public void outputException(Exception exception) {
        if (exception instanceof IOException)
            System.out.println("File path invalid, please make sure the file path provided points to a .csv file");
        else if (exception instanceof NullPointerException)
            System.out.println("The provided collection is either invalid or has not been instantiated. Please provide a valid collection");
        else
            System.out.println("The program has thrown a " + exception + ".");
        System.out.println(separateConsoleOutput);
    }

    public String getStringFromUser(String message, String messageTwo) {
        System.out.println(message);
        if (messageTwo != null)
            System.out.println(messageTwo);
        String inputString = scanner.next();
        System.out.println(separateConsoleOutput);
        return inputString;
    }

    public int getIntFromUser(String message, String messageTwo) {
        System.out.println(message);
        if (messageTwo != null)
            System.out.println(messageTwo);
        int inputInt = scanner.nextInt();
        System.out.println(separateConsoleOutput);
        return inputInt;
    }

    public int getChoiceFromUser(String message, String[] options){
        System.out.println(message);
        for (int i = 0; i < options.length; i++){
            System.out.println("Option " + i+1 + ": " + options[i]);
        }
        System.out.println("Please enter the number for your option of choice and press \'Enter\'");
        int inputChoice = scanner.nextInt();
        if(options[inputChoice] != null)
            return inputChoice;
        else{
            System.out.println("Please enter the number for a valid choice");
            getChoiceFromUser(message, options);
            return 0;
        }
    }
}
