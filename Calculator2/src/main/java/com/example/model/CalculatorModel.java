package com.example.model;

import java.util.Stack;

public class CalculatorModel {

    public double evaluate(String expression) throws IllegalArgumentException {
        if (!isValidExpression(expression)) {
            throw new IllegalArgumentException("Invalid expression: incorrect brackets");
        }

        expression = preprocessExpression(expression);
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int i = 0;

        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                numbers.push(Double.parseDouble(sb.toString()));
                continue;
            }

            if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.pop(); // Убираем '(' из стека операторов
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(c);
            }
            i++;
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private String preprocessExpression(String expression) {
        // Обработка дополнительных математических символов
        expression = expression.replaceAll("log", "l");
        expression = expression.replaceAll("\\*\\*", "^");
        expression = expression.replaceAll("exp", "e");

        return expression;
    }

    private boolean isValidExpression(String expression) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false; // Больше закрывающих, чем открывающих
                }
            }
        }

        return stack.isEmpty(); // Проверяем, что все открывающие скобки имеют пару
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '!' || c == '^' || c == 'l' || c == 'e';
    }

    private boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '!' || op1 == '^' || op1 == 'l' || op1 == 'e') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            case '!':
                return factorial(a);
            case '^':
                return Math.pow(a, b);
            case 'l':
                return Math.log(a) / Math.log(2); // логарифм по основанию 2
            case 'e':
                return Math.exp(a);
        }
        return 0;
    }

    private double factorial(double n) {
        if (n == 0 || n == 1) return 1;
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
