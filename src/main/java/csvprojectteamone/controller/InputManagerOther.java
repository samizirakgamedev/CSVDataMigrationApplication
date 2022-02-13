package csvprojectteamone.controller;

import java.util.Scanner;

public class InputManagerOther {
    static Scanner scanner = new Scanner(System.in);

    public static String getString() {
        return scanner.nextLine();
    }
    public static int getInteger() {
        return scanner.nextInt();
    }

}
