package main;

import controller.NumericValuesController;
import controller.OperationsController;
import view.CalculatorView;
public class Main {
    public static void main(String[] args) {
        NumericValuesController numericValuesController = new NumericValuesController();
        OperationsController operationsController = new OperationsController();
        CalculatorView calculatorView = new CalculatorView(numericValuesController, operationsController);
        operationsController.setView(calculatorView);
        numericValuesController.setView(calculatorView);
    }
}

