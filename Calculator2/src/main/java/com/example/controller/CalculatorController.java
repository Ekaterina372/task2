package com.example.controller;

import com.example.model.CalculatorModel;
import com.example.view.CalculatorView;


public class CalculatorController {

    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void processUserInput() {
        try {
            String expression = view.getInputExpression();
            double result = model.evaluate(expression);
            view.displayResult(result);
        } catch (IllegalArgumentException | ArithmeticException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorController controller = new CalculatorController(model, view);

        // Run the application
        controller.processUserInput();
    }
}
