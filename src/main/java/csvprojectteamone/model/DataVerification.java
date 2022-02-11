package csvprojectteamone.model;

import csvprojectteamone.model.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataVerification {
    // Try catch blocks have been added throughout (with possible exceptions) for better performance.
    // Formatter for comparing date format of employees data.
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    // Checks to make sure the employees ID is valid by making sure it is an integer.
    private static boolean isEmpIdValid(Integer empID){
        try{
            Integer.valueOf(empID);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    // Checks to make sure the employees name prefix is valid by looping through an array of prefixes.
    private static boolean isNamePrefix(String namePrefix){
        String[] namePrefixes = new String[] {"Mr.", "Ms.", "Mrs.", "Dr.", "Drs.", "Hon.", "Prof."};
        for (String nPrefix : namePrefixes) {
            if(nPrefix.equals(namePrefix))
                return true;
        }
        return false;
    }
    // Checks to make sure the employees first name is valid using regex.
    private static boolean isFirstNameValid(String firstName){
        return (firstName.matches("^[a-zA-Z]*$"));
    }
    // Checks to make sure the employees middle initial is a valid char.
    private static boolean isMiddleInitialValid(Character middleName){
        return ((middleName >= 'A' && middleName <= 'Z'));
    }
    // Checks to make sure the employees last name is valid using regex.
    private static boolean isLastNameValid(String lastName){
        return (lastName.matches("^[a-zA-Z]*$")); //This regex also accepts last names containing ' or - character on their index 1 (i.e. O'Neil or O-Neil )
    }
    // Checks to make sure the employees gender char is valid by checking if it equals 'M' of 'F'.
    private static boolean isGenderValid(Character g){
        return g.equals('M') || g.equals('F');
    }
    // Checks to make sure the employees email is valid using regex for email addresses.
    private static boolean isEmailValid(String email){
        return email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }
    // Checks to make sure the employees date of birth is valid checking if the formatter can format the passed in date.
    private static boolean isDateOfBirthValid(LocalDate dateOfBirth){
        try{
            formatter.format(dateOfBirth);
        } catch(Exception e){
            return false;
        }
        return true;
    }
    // Checks to make sure the employees date of joining is valid checking if the formatter can format the passed in date.
    private static boolean isDateOfJoiningValid(LocalDate dateOfJoining){
        try{
            formatter.format(dateOfJoining);
        } catch(Exception e){
            return false;
        }
        return true;
    }
    // Checks to make sure the employee's salary is valid by making sure it is a double.
    private static boolean isSalaryValid(Double salary){
        try {
            Double.valueOf(salary);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    // Method to call to verify if the 'Employee' object as a whole is valid.
    public static boolean isEmployeeDataValid(Employee employee){
        return (isEmpIdValid(employee.getEmployeeId())
                && isNamePrefix(employee.getTitle())
                && isFirstNameValid(employee.getFirstName())
                && isMiddleInitialValid(employee.getMiddleInitial())
                && isLastNameValid(employee.getLastName())
                && isGenderValid(employee.getGender())
                && isEmailValid(employee.getEmailAddress())
                && isDateOfBirthValid(employee.getDateOfBirth())
                && isDateOfJoiningValid(employee.getDateOfJoining())
                && isSalaryValid(employee.getSalary()));
    }
}
