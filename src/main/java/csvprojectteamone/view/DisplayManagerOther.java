package csvprojectteamone.view;

public class DisplayManagerOther {
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

}
