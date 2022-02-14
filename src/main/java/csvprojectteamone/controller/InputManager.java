package csvprojectteamone.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputManager {
    // Initiates the 'Scanner'.
    static Scanner scanner = new Scanner(System.in);

    // Getter for getting a String from the user.
    public static String getString() {
        return scanner.nextLine();
    }

    // Getter for getting an Integer from the user.
    public static int getInteger()
    {
        return scanner.nextInt();
    }
}
