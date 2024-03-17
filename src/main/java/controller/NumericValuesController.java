package controller;

import view.CalculatorView;
import javax.swing.*;
public class NumericValuesController {
    public CalculatorView view;


    public NumericValuesController() {}

    public void appendDigit(String digit, JTextField textField) {
        String currentText = textField.getText();
        textField.setText(currentText + digit);
    }

    public void deleteLastCharacter(JTextField textField) {
        String currentText = textField.getText();
        if (currentText != null && !currentText.isEmpty()) {
            textField.setText(currentText.substring(0, currentText.length() - 1));
        }
    }
    public CalculatorView getView() {
        return view;
    }

    public void setView(CalculatorView view) {
        this.view = view;
    }


}
