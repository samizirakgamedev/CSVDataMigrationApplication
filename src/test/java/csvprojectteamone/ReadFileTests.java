package csvprojectteamone;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static csvprojectteamone.ReadFile.readCSV;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadFileTests {

//    @Test
//    @DisplayName("Given a CSV file, the method reads the file")
//    public void givenCSVReadsTheFile(){
//
//    }
//
//    @Test
//    @DisplayName("Given a CSV file, the method outputs the file to the console")
//    public void givenCSVOutputToConsole(){
//
//    }

    @ParameterizedTest
    @CsvSource(value = {"Gideon, Sami, Suyash, Donovan | Gideon, Sami, Suyash, Donovan"},
            delimiter = '|')
    @DisplayName("Given a CSV file return the values")
    public void givenCSVReturnTheValues(String file, String expected){

        assertEquals(expected, readCSV("names.csv"));
    }

}
