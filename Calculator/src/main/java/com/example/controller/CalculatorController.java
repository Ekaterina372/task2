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

    public void calculate() {
        try {
            String expression = view.getExpression();
            double result = model.evaluate(expression);
            view.displayResult(result);
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }
}
