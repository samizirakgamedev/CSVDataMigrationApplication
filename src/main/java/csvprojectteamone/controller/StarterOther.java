package csvprojectteamone.controller;


public class StarterOther {
    public static void start(){
        EmployeeManagerOther employeeManager = new EmployeeManagerOther();
        employeeManager.load();
        SelectionManagerOther.startSelectionProcess();
    }
}
