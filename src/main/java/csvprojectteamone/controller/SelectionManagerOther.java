package csvprojectteamone.controller;

import csvprojectteamone.model.LogClass;
import csvprojectteamone.view.DisplayManagerOther;

import java.util.InputMismatchException;

public class SelectionManagerOther {
    public static void startSelectionProcess() {
        boolean done = false;
        while(!done){
            boolean choice = false;
            DisplayManagerOther.displaySelection();
            int userChoice = 0;
            do {
                try {
                    userChoice = InputManagerOther.getInteger();
                    if(userChoice >= 1 && userChoice <= 4){
                        choice = true;
                    } else{
                        DisplayManagerOther.displayMessage("Please select from one of the options listed above");
                    }
                } catch (InputMismatchException e){
                    LogClass.logError(e.getMessage());
                    DisplayManagerOther.displayMessage("Please chose a number (option) between 1 and 4");
                    InputManagerOther.getString();
                } catch (Exception e){
                    LogClass.logError(e.getMessage());
                    InputManagerOther.getString();
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
                    //countEmployees();
                    break;
                case 4:
                    done = true;
                    break;
            }
        }
    }

    private static void selectEmployee(){
        EmployeeManagerOther employeeManager = new EmployeeManagerOther();
        boolean done = false;
        do {
            try {
                DisplayManagerOther.displayMessage("Please enter an employee ID: ");
                employeeManager.selectEmployee();
                done = true;
            } catch (InputMismatchException | NullPointerException e){
                LogClass.logError(e.getMessage());
            }
        } while(!done);
    }


//We can add this method if we have a count function in the EmployeeDAOImpl class
//    private static void countEmployees(){
//        EmployeeManagerOther employeeManager = new EmployeeManagerOther();
//        boolean done = false;
//        do {
//            employeeManager.countEmployees();
//            done = true;
//        } while(!done);
//    }

private static void selectAllEmployees(){
    EmployeeManagerOther employeeManager = new EmployeeManagerOther();
    boolean done = false;
    do {
        try {
            DisplayManagerOther.displayMessage("Now selecting all employees");
            employeeManager.selectAllEmployees();
            done = true;
        } catch (InputMismatchException | NullPointerException e){
            LogClass.logError(e.getMessage());
        }
    } while(!done);
}

}
