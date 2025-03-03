package org.cakwei;

import java.util.Scanner;

public class Main {
    public static String getInput(String questionText) {
        Scanner input = new Scanner(System.in);
        System.out.print(questionText);
        return input.nextLine().strip();
    }
    public static void main(String[] args) {
        System.out.println("[!] Starting up application...");
        Application.main(args);
    }
}