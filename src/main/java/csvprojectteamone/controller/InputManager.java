package csvprojectteamone.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputManager {
    static Scanner scanner = new Scanner(System.in);

    public static String getString() {
        return scanner.nextLine();
    }

    public static int getInteger()
    {
        return scanner.nextInt();
    }
}
