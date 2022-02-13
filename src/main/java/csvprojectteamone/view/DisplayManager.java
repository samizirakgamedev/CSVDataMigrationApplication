package csvprojectteamone.view;

import java.io.IOException;

public class DisplayManager {
    public static void displayMessage(String message){
        System.out.println(message);
    }

    public static void displaySelection(){
        System.out.println( "\n======================================================================================\n"+
                "\n   *************************|| Welcome to CSV Migration ||*************************  \n" +
                "\n======================================================================================\n"+
                "-------------------------> Please select your desired option <-------------------------\n"+
                "\n        ***--> Please choose from one of the following three options <--***\n" +
                "\n    1 for- || Insert Employees csv to Database ||\n" +
                "\n    2 for- || Select Employee by ID  ||\n" +
                "\n    3 for- || Select All Employees from database  ||\n "+
                "\n======================================================================================\n"+
                "\n              Choose 4 for- || Exiting the Sort Manager Application ||               \n"+
                "\n======================================================================================\n"+
                "\n Enter your choice here: ");


    }

    public void outputException(Exception exception) {
        if (exception instanceof IOException)
            System.out.println("File path invalid, please make sure the file path provided points to a .csv file");
        else if (exception instanceof NullPointerException)
            System.out.println("The provided collection is either invalid or has not been instantiated. Please provide a valid collection");
        else
            System.out.println("The program has thrown a " + exception + ".");
    }

}
