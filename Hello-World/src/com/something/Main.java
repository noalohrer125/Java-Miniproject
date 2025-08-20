package com.something;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte gib den Eurobetrag an: ");
        double money = scanner.nextDouble();

        convertToDollars(money);
    }

    public static void convertToDollars(double eur) {
        System.out.println("Betrag ist: " + eur * 1.20);
    }
}
