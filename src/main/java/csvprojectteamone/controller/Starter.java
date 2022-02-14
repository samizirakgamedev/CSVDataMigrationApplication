package csvprojectteamone.controller;

public class Starter {
    // Defines the start method in CSVDataMigrationMain.
    public static void start(){
        EmployeeManager employeeManager = new EmployeeManager();
        employeeManager.load();
        // Outputs the options for the user to chose from when running the application.
        SelectionManager.startSelectionProcess();
    }
}
