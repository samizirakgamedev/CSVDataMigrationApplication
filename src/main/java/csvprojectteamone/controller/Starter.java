package csvprojectteamone.controller;

public class Starter {
    public static void start(){
        EmployeeManager employeeManager = new EmployeeManager();
        employeeManager.load();
        SelectionManager.startSelectionProcess();
    }
}
