package com.example.model;

import java.util.Stack;

public class CalculatorModel {
    public double evaluate(String expression) throws Exception {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                number.append(ch);
            } else {
                if (number.length() > 0) {
                    numbers.push(Double.parseDouble(number.toString()));
                    number = new StringBuilder();
                }
                if (ch == ' ') continue;

                while (!operations.isEmpty() && precedence(ch) <= precedence(operations.peek())) {
                    double a = numbers.pop();
                    double b = numbers.pop();
                    char op = operations.pop();
                    numbers.push(applyOp(op, b, a));
                }
                operations.push(ch);
            }
        }

        if (number.length() > 0) {
            numbers.push(Double.parseDouble(number.toString()));
        }

        while (!operations.isEmpty()) {
            double a = numbers.pop();
            double b = numbers.pop();
            char op = operations.pop();
            numbers.push(applyOp(op, b, a));
        }

        return numbers.pop();
    }

    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            case '^': return Math.pow(a, b);
            case '%': return a % b;
        }
        return 0;
    }

    private int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
}
