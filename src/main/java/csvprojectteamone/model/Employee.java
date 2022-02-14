package csvprojectteamone.model;

import java.time.LocalDate;
// Employee object
public class Employee {
    private int employeeId;
    private String title;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private char gender;
    private String emailAddress;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private double salary;

    public Employee(int employeeId, String title, String firstName, char middleInitial, String lastName, char gender, String emailAddress, LocalDate dateOfBirth, LocalDate dateOfJoining, double salary) {
        this.employeeId = employeeId;
        this.title = title;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "[Employee #" + employeeId +
                ": "+ title + " " + firstName +
                " " + middleInitial + ". " + lastName +
                " - Gender: " + gender + ", Email: "
                + emailAddress + ", D.o.B: "
                + dateOfBirth + ", Date Joined: "
                + dateOfJoining + ", Salary: " + salary + "]";
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public char getMiddleInitial() {
        return middleInitial;
    }
    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }
    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
}