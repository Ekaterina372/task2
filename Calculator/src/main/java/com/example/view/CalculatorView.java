package com.example.view;

import java.util.Scanner;

public class CalculatorView {
    private Scanner scanner = new Scanner(System.in);

    public String getExpression() {
        System.out.print("Expression: ");
        return scanner.nextLine();
    }

    public void displayResult(double result) {
        System.out.println("Result: " + result);
    }

    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}
