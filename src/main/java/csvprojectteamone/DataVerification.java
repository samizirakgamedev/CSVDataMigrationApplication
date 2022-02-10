package csvprojectteamone;

import java.time.format.DateTimeFormatter;

public class DataVerification { //try catch blocks have been added throughout (with possible exceptions) for better performance.

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    private static boolean isEmpIdValid(String empID){ //CHECK THE TYPES
        try {
            Integer.parseInt(String.valueOf(empID));
        } catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    private static boolean isNamePrefix(String namePrefix){
        String[] namePrefixes = new String[] {"Mr.", "Ms.", "Mrs.", "Dr.", "Drs.", "Hon.", "Prof."};
        for (String nPrefix : namePrefixes)
        {
            if(nPrefix.equals(namePrefix))
                return true;
        }return false;
    }
    private static boolean isFirstNameValid(String firstName){
        return (firstName.matches("^[a-zA-Z]*$"));
    }
    private static boolean isMiddleInitialValid(String middleName){

        return ((middleName.matches("^[A-Z]") || middleName.length() != 1));
    }
    private static boolean isLastNameValid(String lastName){
        return (lastName.matches("^[A-Z]+(['-][A-Z][a-z[A-Z]]+)*$")); //This regex also accepts last names containing ' or - character on their index 1 (i.e. O'Neil or O-Neil )
    }
    private static boolean isGenderValid(String g){
        return g.equals("M") || g.equalsIgnoreCase("F"); //Can also use gender.matches(regex) to make it case-sensitive (if needed).
    }
    private static boolean isEmailValid(String email){
        return email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"); //Regular Expression by RFC 5322 for Email Validation. Can also use a basic one ^(.+)@(.+)$ whose main focus is just the @ sign.
    }
    private static boolean isDateOfBirthValid(String dateOfBirth){
        try{
            formatter.parse(dateOfBirth);
        } catch(Exception e){
            return false;
        }
        return true;
    }
    private static boolean isDateOfJoiningValid(String dateOfJoining){
        try{
            formatter.parse(dateOfJoining);
        } catch(Exception e){
            return false;
        }
        return true;
    }
    private static boolean isSalaryValid(String salary){
        try {
            Integer.parseInt(salary);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    //call this method to verify an employee record.
    public static boolean isEmployeeDataValid(Employee employee)
    {
        return (isEmpIdValid(employee.getEmployeeId())
                && isNamePrefix(employee)
                && isFirstNameValid(employee[2])
                && isMiddleInitialValid(employee[3])
                && isLastNameValid(employee[4])
                && isGenderValid(employee[5])
                && isEmailValid(employee[6])
                && isDateOfBirthValid(employee[7])
                && isDateOfJoiningValid(employee[8])
                && isSalaryValid(employee[9]));
    } //can store the length of the data column in the enum class or a separate constant class
}
