package com.example.view;

import java.util.Scanner;

public class CalculatorView {

    public void displayResult(double result) {
        System.out.println("Result: " + result);
    }

    public String getInputExpression() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter expression: ");
        return scanner.nextLine();
    }

    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}

