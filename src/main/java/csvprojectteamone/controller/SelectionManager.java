package csvprojectteamone.controller;

import csvprojectteamone.model.LogClass;
import csvprojectteamone.view.DisplayManager;

import java.util.InputMismatchException;

public class SelectionManager {
    public static void startSelectionProcess() {
        boolean done = false;
        while(!done){
            boolean choice = false;
            DisplayManager.displaySelection();
            int userChoice = 0;
            do {
                try {
                    userChoice = InputManager.getInteger();
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
            } while(!choice);

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


//We can add this method if we have a count function in the EmployeeDAOImpl class
    private static void countEmployees(){
        EmployeeManager employeeManager = new EmployeeManager();
        boolean done = false;
        do {
            employeeManager.countEmployees();
            done = true;
        } while(!done);
    }

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
