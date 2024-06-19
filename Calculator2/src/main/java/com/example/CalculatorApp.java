package com.example;

import com.example.controller.CalculatorController;
import com.example.model.CalculatorModel;
import com.example.view.CalculatorView;

public class CalculatorApp {
    public static void main(String[] args) {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorController controller = new CalculatorController(model, view);
        controller.processUserInput();
    }
}
