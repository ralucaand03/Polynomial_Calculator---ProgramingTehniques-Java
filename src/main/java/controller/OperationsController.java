package controller;

import model.Monomial;
import model.Polynomial;
import view.CalculatorView;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperationsController {
    public CalculatorView view;
    public Polynomial polynomial;
    public OperationsController() {
        this.polynomial = new Polynomial();
    }
    public void addButtonClicked() {
        Polynomial first = view.getFirstPolynomial();
        Polynomial second = view.getSecondPolynomial();
        if (first == null || second == null || second.getPolynomialMap().isEmpty() || first.getPolynomialMap().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter valid polynomials. :D", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Polynomial result = polynomial.addition(first, second);
        String resultString = toStringPolynomial(result);
        if (view != null) {
            view.setResultTextFieldString(resultString);
        }
    }
    public void subtractButtonClicked() {
        Polynomial first = view.getFirstPolynomial();
        Polynomial second = view.getSecondPolynomial();
        if (first == null || second == null || second.getPolynomialMap().isEmpty() || first.getPolynomialMap().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter valid polynomials. XD", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Polynomial result = polynomial.subtraction(first, second);
        String resultString = toStringPolynomial(result);
        if (view != null) {
            view.setResultTextFieldString(resultString);
        }
    }
    public void multiplyButtonClicked() {
        Polynomial first = view.getFirstPolynomial();
        Polynomial second = view.getSecondPolynomial();
        if (first == null || second == null || second.getPolynomialMap().isEmpty() || first.getPolynomialMap().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter valid polynomials. :*", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Polynomial result = polynomial.multiply(first, second);
        String resultString = toStringPolynomial(result);
        if (view != null) {
            view.setResultTextFieldString(resultString);
        }
    }
    public void divideButtonClicked() {
        Polynomial first = view.getFirstPolynomial();
        Polynomial second = view.getSecondPolynomial();
        if (first == null || second == null || second.getPolynomialMap().isEmpty() || first.getPolynomialMap().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter valid polynomials. :P", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (second.getPolynomialMap().firstEntry().getValue().getCoefficient() == 0) {
            JOptionPane.showMessageDialog(view, "Division by zero is not allowed. :(", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Polynomial> result = polynomial.divide(first, second);
        Polynomial Q = result.get(0);
        Polynomial R = result.get(1);
        String resultQ = toStringPolynomial(Q);
        String resultR = toStringPolynomial(R);

        if (resultR == null || resultR.isEmpty()) {
            resultR = "0";
        }
        String resultString = "Q= " + resultQ + " |R= " + resultR;
        if (view != null) {
            view.setResultTextFieldString(resultString);
        }
    }

    public void derivateButtonClicked() {
        Polynomial first = view.getFirstPolynomial();
        if (first == null || first.getPolynomialMap().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter valid polynomials. :O", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Polynomial result = polynomial.derivate(first);
        String resultString = toStringPolynomial(result);
        if (view != null) {
            view.setResultTextFieldString(resultString);
        }
    }
    public void integrateButtonClicked() {
        Polynomial first = view.getSecondPolynomial();
        if (first == null || first.getPolynomialMap().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter valid polynomials. ;)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Polynomial result = polynomial.integrate(first);
        String resultString = toStringPolynomial(result);
        if (view != null) {
            view.setResultTextFieldString(resultString);
        }
    }
    public static String toStringPolynomial(Polynomial polynomial) {
        String stringPolynomial = "";
        for (Map.Entry<Integer, Monomial> entry : polynomial.getPolynomialMap().entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            double coeff = monomial.getCoefficient();
            if (coeff > 0 && !stringPolynomial.isEmpty()) {
                stringPolynomial += "+";
            }
            if(coeff==-1) {
                stringPolynomial += "-";
            }else {
                if (coeff != 0 && coeff != 1) {
                    if (coeff == (int) coeff) {
                        stringPolynomial += (int) coeff;
                    } else {
                        stringPolynomial += String.format("%.2f", coeff);
                    }
                }
            }
            if(degree == 0 && (coeff ==1 || coeff==-1)) stringPolynomial += "1";
            if (degree > 0 && coeff != 0) {
                stringPolynomial += "X";
                if (degree != 1) {
                    stringPolynomial += "^" + degree;
                }
            }
        }
        if (stringPolynomial.isEmpty()) {
            stringPolynomial = "0";
        }
        return stringPolynomial;
    }
    //-----------------------------------------------------------------
    public static Polynomial getPolynomial(String input) throws IllegalArgumentException{
        Polynomial polynomial = new Polynomial();
        if (!verifyInput(input)) {
            return null;
        }
        String regex = "(?<S1>[-+]?)?(?<C1>\\d*)(X(\\^(?<degree>\\d+))?)|(?<S2>[-+]?)?(?<C2>\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String sign1 = matcher.group("S1");
            String coefficient1 = matcher.group("C1");
            String degree = matcher.group("degree");
            String sign2 = matcher.group("S2");
            String coefficient2 = matcher.group("C2");
            int[] parsedValues = parseValues(sign1, coefficient1, degree, sign2, coefficient2);
            double coef = (double) parsedValues[0];
            int deg = parsedValues[1];
            if (polynomial.getPolynomialMap().containsKey(deg)) {
                // Polynomial with degree 'deg' already exists
                Monomial existingMonomial = polynomial.getPolynomialMap().get(deg);
                coef += existingMonomial.getCoefficient();
            }
            polynomial.addMonomial(coef,deg);
        }
        return polynomial;
    }
    public static boolean verifyInput(String input) {
        // Check for invalid patterns
        if (input.contains("XX") || input.contains("^X") || input.contains("^+") || input.contains("^-") ||
                input.contains("--") || input.contains("++") || input.contains("-+") || input.contains("+-") ||
                input.contains("0^") || input.contains("1^") || input.contains("2^") || input.contains("3^") ||
                input.contains("4^") || input.contains("5^") || input.contains("6^") || input.contains("7^") ||
                input.contains("8^") || input.contains("9^") || input.contains("-^") || input.contains("+^") ||
                input.contains("X0") || input.contains("X1") || input.contains("X2") || input.contains("X3") ||
                input.contains("X4") || input.contains("X5") || input.contains("X6") || input.contains("X7") ||
                input.contains("X8") || input.contains("X9") || input.startsWith("^") ||
                input.endsWith("-") || input.endsWith("+") || input.endsWith("^")) {
            return false;
        }
        return true;
    }
    private static int[] parseValues(String sign1, String coefficient1, String degree, String sign2, String coefficient2) {
        int coefficient;
        int deg;
        // case 1: sign1*coefficient1*x^degree
        if (coefficient1 != null && !coefficient1.isEmpty()) {
            coefficient = parseCoefficient(sign1, coefficient1);
            deg = (degree != null && !degree.isEmpty()) ? Integer.parseInt(degree) : 1;
        }
        // case 2: sign2*coefficient2
        else if (sign1 != null && !sign1.isEmpty()) {
            coefficient = parseCoefficient(sign1, coefficient1);
            deg = (degree != null && !degree.isEmpty()) ? Integer.parseInt(degree) : 1;
        } else if (sign2 == null) {
            sign1 = "+";
            coefficient = parseCoefficient(sign1, coefficient1);
            deg = (degree != null && !degree.isEmpty()) ? Integer.parseInt(degree) : 1;
        } else {
            coefficient = parseCoefficient(sign2, coefficient2);
            deg = 0;
        }
        return new int[]{coefficient, deg};
    }
    private static int parseCoefficient(String sign, String coefficient) {
        if (coefficient == null || coefficient.isEmpty()) {
            coefficient = "1";
        }
        int coeff = Integer.parseInt(coefficient);
        return (sign != null && sign.equals("-")) ? -coeff : coeff;
    }
    //-----------------------------------------------------------------
    public CalculatorView getView() {
        return view;
    }
    public void setView(CalculatorView view) {
        this.view = view;
    }
    public Polynomial getPolynomial() {
        return polynomial;
    }
}