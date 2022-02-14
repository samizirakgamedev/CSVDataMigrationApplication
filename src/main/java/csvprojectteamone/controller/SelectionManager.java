package csvprojectteamone.controller;

import csvprojectteamone.model.LogClass;
import csvprojectteamone.view.DisplayManager;

import java.util.InputMismatchException;

public class SelectionManager {
    public static void startSelectionProcess() {
        boolean done = false; //Initiates the boolean value 'done'.
        while(!done){ // While loop to run the programme until the functions selected in the user have been completed.
            boolean choice = false;
            DisplayManager.displaySelection();
            int userChoice = 0;
            do {
                try {
                    userChoice = InputManager.getInteger(); // Try statement to display the task the user must carry out to run the programme.
                    if(userChoice >= 1 && userChoice <= 4){
                        choice = true;
                    } else{
                        DisplayManager.displayMessage("Please select from one of the options listed above");
                    }
                } catch (InputMismatchException e){
                    LogClass.logError(e.getMessage());
                    DisplayManager.displayMessage("Please chose a number (option) between 1 and 4");
                    InputManager.getString();
                } catch (Exception e){
                    LogClass.logError(e.getMessage());
                    InputManager.getString();
                }
            } while(!choice); //Re-runs the programme after the user task has been completed until 'Exit' is picked where 'done' is then true.

            switch (userChoice){
                case 1:
                    selectEmployee();
                    break;
                case 2:
                    selectAllEmployees();
                    break;
                case 3:
                    countEmployees();
                    break;
                case 4:
                    done = true;
                    break;
            }
        }
    }
    // Calls the 'SelectEmployee' method by choosing the Employee ID.
    private static void selectEmployee(){
        EmployeeManager employeeManager = new EmployeeManager();
        boolean done = false;
        do {
            try {
                DisplayManager.displayMessage("Please enter an employee ID: ");
                employeeManager.selectEmployee();
                done = true;
            } catch (InputMismatchException | NullPointerException e){
                LogClass.logError(e.getMessage());
            }
        } while(!done);
    }


// Calls the 'countEmployees' method.
    private static void countEmployees(){
        EmployeeManager employeeManager = new EmployeeManager();
        boolean done = false;
        do {
            employeeManager.countEmployees();
            done = true;
        } while(!done);
    }
    // Calls the 'selectAllEmployees' method.
private static void selectAllEmployees(){
    EmployeeManager employeeManager = new EmployeeManager();
    boolean done = false;
    do {
        try {
            DisplayManager.displayMessage("Now selecting all employees");
            employeeManager.selectAllEmployees();
            done = true;
        } catch (InputMismatchException | NullPointerException e){
            LogClass.logError(e.getMessage());
        }
    } while(!done);
}

}
