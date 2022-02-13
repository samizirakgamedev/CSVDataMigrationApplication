package csvprojectteamone.controller;

import csvprojectteamone.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static csvprojectteamone.controller.FileReader.outputRecords;
import static csvprojectteamone.controller.ThreadedFileReader.readCSV;
import static org.junit.jupiter.api.Assertions.*;

class ThreadedFileReaderTest {

    @Test
    @DisplayName("Given an invalid filePath and a valid HashMap, the readCSV method will not throw a \"IOException\" for ThreadedFileReader")
    void readCSVDoesNotThrowIOExceptionForThreadedFileReader() {
        HashMap<Integer, Employee> testHash = new HashMap<>();
        Assertions.assertDoesNotThrow(() -> readCSV("invalidFile.csv",testHash));
    }
    @Test
    @DisplayName("Given a filePath and HashMap that is null, the readCSV method will not throw a \"NullPointerException\" for ThreadedFileReader")
    public void readCSVDoesNotThrowNullPointerExceptionForThreadedFileReader(){
        Assertions.assertDoesNotThrow(() -> readCSV("csvInput/EmployeeRecordsLarge.csv",null));
    }
    @Test
    @DisplayName("Given an invalid filePath and HashMap that is null, the readCSV method will not throw a \"NullPointerException\" or \"IOException\" for ThreadedFileReader")
    public void readCSVDoesNotThrowExceptionsForThreadedFileReader(){
        Assertions.assertDoesNotThrow(() -> readCSV("invalidFile.csv", null));
    }

    @Test
    @DisplayName("Given a filePath and HashMap the readCSV method will read the employeeId and set it as the HashMaps keyset for ThreadedFileReader")
    public void readCSVSetsEmployeeIDAsKeySetForThreadedFileReader() {
        HashMap<Integer, Employee> eHashActual = new HashMap<>();
        readCSV("csvInputs/TestRecords.csv",eHashActual);
        HashMap<Integer, Employee> eHashExpected = generateTestHashMap();

        Assertions.assertEquals(eHashExpected.keySet(), eHashActual.keySet());
    }



    @Test
    @DisplayName("Given a filePath and HashMap the readCSV method will read the file and remove duplicates for ThreadedFileReader")
    public void readCSVRemovesDuplicatesForThreadedFileReader(){
        HashMap<Integer, Employee> eHashActual = new HashMap<>();
        readCSV("csvInputs/TestDuplicateRecords.csv",eHashActual);

        HashMap<Integer, Employee> eHashExpected = generateTestHashMapDuplicates();

        Assertions.assertEquals(eHashExpected.keySet(), eHashActual.keySet());
    }

    private HashMap<Integer,Employee> generateTestHashMap(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        HashMap<Integer, Employee> eHashExpected = new HashMap<>();
        eHashExpected.put(198429,new Employee(198429,"Mrs.","Serafina",'I',"Bumgarner",'F',"serafina.bumgarner@exxonmobil.com", LocalDate.parse("9/21/1982",formatter),LocalDate.parse("02/01/2008",formatter),69294));
        eHashExpected.put(178566,new Employee(178566,"Mrs.","Juliette",'M',"Rojo",'F',"juliette.rojo@yahoo.co.uk", LocalDate.parse("05/08/1967",formatter),LocalDate.parse("06/04/2011",formatter),193912));
        eHashExpected.put(647173,new Employee(647173,"Mr.","Milan",'F',"Krawczyk",'M',"milan.krawczyk@hotmail.com", LocalDate.parse("04/04/1980",formatter),LocalDate.parse("1/19/2012",formatter),123681));
        eHashExpected.put(847634,new Employee(847634,"Mr.","Elmer",'R',"Jason",'M',"elmer.jason@yahoo.com", LocalDate.parse("04/09/1996",formatter),LocalDate.parse("5/28/2017",formatter),93504));
        return  eHashExpected;
    }
    private HashMap<Integer,Employee> generateTestHashMapDuplicates(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        HashMap<Integer, Employee> eHashExpected = new HashMap<>();
        eHashExpected.put(198429,new Employee(198429,"Mrs.","Serafina",'I',"Bumgarner",'F',"serafina.bumgarner@exxonmobil.com", LocalDate.parse("9/21/1982",formatter),LocalDate.parse("02/01/2008",formatter),69294));
        eHashExpected.put(647173,new Employee(647173,"Mr.","Milan",'F',"Krawczyk",'M',"milan.krawczyk@hotmail.com", LocalDate.parse("04/04/1980",formatter),LocalDate.parse("1/19/2012",formatter),123681));
        return  eHashExpected;
    }
}