package csvprojectteamone;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static csvprojectteamone.ReadFile.readCSV;

public class ReadFileTests {
    @BeforeAll
    static void setUp(){

    }

    @Test
    @DisplayName("Given an invalid filePath and a valid HashMap, the readCSV method will not throw a \"IOException\"")
    public void readCSVDoesNotThrowIOException(){
       HashMap<Integer, Employee> testHash = new HashMap<>();
       Assertions.assertDoesNotThrow(() -> readCSV("invalidFile.csv",testHash));
     }
    @Test
    @DisplayName("Given a filePath and HashMap that is null, the readCSV method will not throw a \"NullPointerException\"")
    public void readCSVDoesNotThrowNullPointerException(){
        Assertions.assertDoesNotThrow(() -> readCSV("EmployeeRecords.csv",null));
    }
    @Test
    @DisplayName("Given an invalid filePath and HashMap that is null, the readCSV method will not throw a \"NullPointerException\" or \"IOException\"")
    public void readCSVDoesNotThrowExceptions(){
        Assertions.assertDoesNotThrow(() -> readCSV("invalidFile.csv", null));
    }
    // IN DEVELOPMENT
//    @Test
//    @DisplayName("Given a filePath and HashMap the readCSV method will read the file")
//    public void readCSVReads(){
//        HashMap<Integer, Employee> eHashActual = new HashMap<Integer,Employee>();
//        readCSV("TestRecords.csv",eHashActual);
//        HashMap<Integer, Employee> eHashExpected = generateTestHashMap();
//
//        //Map<Integer, Boolean> result = areEqualKeyValues(generateTestHashMap(),eHashActual);
//
//    }
    @Test
    @DisplayName("Given a filePath and HashMap the readCSV method will read the employeeId and set it as the HashMaps keyset")
    public void readCSVSetsEmployeeIDAsKeySet(){
        HashMap<Integer, Employee> eHashActual = new HashMap<>();
        readCSV("TestRecords.csv",eHashActual);
        HashMap<Integer, Employee> eHashExpected = generateTestHashMap();

        Assertions.assertEquals(eHashExpected.keySet(), eHashActual.keySet());
    }
    @Test
    @DisplayName("Given a filePath and HashMap the readCSV method will read the file and remove duplicates")
    public void readCSVRemovesDuplicates(){
        HashMap<Integer, Employee> eHashActual = new HashMap<>();
        readCSV("TestDuplicateRecords.csv",eHashActual);

        HashMap<Integer, Employee> eHashExpected = generateTestHashMapDuplicates();

        Assertions.assertEquals(eHashExpected.keySet(), eHashActual.keySet());
    }
    private Map<Integer, Boolean> areEqualKeyValues(Map<Integer, Employee> first, Map<Integer, Employee> second) {
        return first.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> e.getValue().equals(second.get(e.getKey()))));
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
