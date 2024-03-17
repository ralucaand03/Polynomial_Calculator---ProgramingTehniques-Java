package model;

import javax.swing.*;
import java.util.*;
public class Polynomial {
    private TreeMap<Integer, Monomial> polynomialMap;

    public Polynomial() {
        this.polynomialMap = new TreeMap<>();
    }

    public void addMonomial(double coefficient, int degree) {
        Monomial monomial = new Monomial(coefficient, degree);
        polynomialMap.put(degree, monomial);
    }
    public TreeMap<Integer, Monomial> getPolynomialMap() {
        return polynomialMap;
    }
    public void setPolynomialMap(TreeMap<Integer, Monomial> polynomialMap) {
        this.polynomialMap = polynomialMap;
    }
    public static Polynomial addition(Polynomial first, Polynomial second) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : first.getPolynomialMap().entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            result.addMonomial(monomial.getCoefficient(), degree);
        }
        for (Map.Entry<Integer, Monomial> entry : second.getPolynomialMap().entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            double newCoefficient = result.getPolynomialMap().containsKey(degree) ? result.getPolynomialMap().get(degree).getCoefficient() + monomial.getCoefficient() : monomial.getCoefficient();
            result.addMonomial(newCoefficient, degree);
        }
        result=orderMonomialsDescending(result);
        return result;
    }
    public static Polynomial subtraction(Polynomial first, Polynomial second) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : first.getPolynomialMap().entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            result.addMonomial(monomial.getCoefficient(), degree);
        }
        for (Map.Entry<Integer, Monomial> entry : second.getPolynomialMap().entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            double newCoefficient = result.getPolynomialMap().containsKey(degree) ? result.getPolynomialMap().get(degree).getCoefficient() - monomial.getCoefficient() : -monomial.getCoefficient();
            result.addMonomial(newCoefficient, degree);
        }
        result=orderMonomialsDescending(result);
        return result;
    }
    public static Polynomial multiply(Polynomial first, Polynomial second) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry1 : first.getPolynomialMap().entrySet()) {
            for (Map.Entry<Integer, Monomial> entry2 : second.getPolynomialMap().entrySet()) {
                int degree1 = entry1.getKey();
                Monomial monomial1 = entry1.getValue();
                int degree2 = entry2.getKey();
                Monomial monomial2 = entry2.getValue();
                double newCoefficient = monomial1.getCoefficient() * monomial2.getCoefficient();
                int newDegree = degree1 + degree2;
                if (result.getPolynomialMap().containsKey(newDegree)) {
                    newCoefficient += result.getPolynomialMap().get(newDegree).getCoefficient();
                }
                result.addMonomial(newCoefficient, newDegree);
            }
        }
        result=orderMonomialsDescending(result);
        return result;
    }
    public static List<Polynomial> divide(Polynomial first, Polynomial second) {
        if (second.getPolynomialMap().isEmpty() || second.getPolynomialMap().firstEntry().getValue().getCoefficient() == 0) {
            JOptionPane.showMessageDialog(null, "Division by zero", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Division by zero");

        }
        Polynomial firstOrdered = orderMonomialsDescending(first);
        Polynomial secondOrdered = orderMonomialsDescending(second);
        boolean firstHasHigherDegree = firstOrdered.getPolynomialMap().firstKey() >= secondOrdered.getPolynomialMap().firstKey();
        Polynomial quotient = new Polynomial();
        Polynomial remainder = orderMonomialsDescending(first);
        if (firstHasHigherDegree) {
            while (!remainder.getPolynomialMap().isEmpty() && remainder.getPolynomialMap().firstKey() >= secondOrdered.getPolynomialMap().firstKey()) {
                Monomial leadR = remainder.getPolynomialMap().firstEntry().getValue();
                Monomial leadD = secondOrdered.getPolynomialMap().firstEntry().getValue();
                Polynomial t1 = new Polynomial();
                t1.addMonomial(leadR.getCoefficient() / leadD.getCoefficient(), leadR.getDegree() - leadD.getDegree());
                quotient.addMonomial(leadR.getCoefficient() / leadD.getCoefficient(), leadR.getDegree() - leadD.getDegree());
                Polynomial product = multiply(secondOrdered, t1);
                remainder = subtraction(remainder, product);
                remainder.removeZeros();
                remainder = orderMonomialsDescending(remainder);
            }
        } else { quotient.addMonomial(0, 0);       }
        quotient=orderMonomialsDescending(quotient);
        List<Polynomial> result = new ArrayList<>();
        result.add(quotient);
        result.add(remainder);
        return result;
    }
    public void removeZeros() {
        TreeMap<Integer, Monomial> copyMap = new TreeMap<>(polynomialMap);
        for (Map.Entry<Integer, Monomial> entry : copyMap.entrySet()) {
            if (entry.getValue().getCoefficient() == 0) {
                polynomialMap.remove(entry.getKey()); // Remove monomial with zero coefficient
            }
        }
    }
    public static Polynomial derivate(Polynomial polynomial) {
        Polynomial derivative = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : polynomial.getPolynomialMap().entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            if (degree > 0) {
                double coefficient = monomial.getCoefficient() * degree;
                int newDegree = degree - 1;
                derivative.addMonomial(coefficient, newDegree);
            }
        }
        derivative=orderMonomialsDescending(derivative);
        return derivative;
    }
    public static Polynomial integrate(Polynomial polynomial) {
        Polynomial integral = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : polynomial.getPolynomialMap().entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            double coefficient = monomial.getCoefficient();
            int newDegree = degree + 1;
            if (coefficient != 0) {
                double integratedCoefficient = coefficient / newDegree;
                integral.addMonomial(integratedCoefficient, newDegree);
            }
        }
        integral=orderMonomialsDescending(integral);
        return integral;
    }
    public static Polynomial orderMonomialsDescending(Polynomial polynomial) {
        TreeMap<Integer, Monomial> polynomialMap = polynomial.getPolynomialMap();
        TreeMap<Integer, Monomial> sortedMap = new TreeMap<>(Collections.reverseOrder());
        sortedMap.putAll(polynomialMap);
        Polynomial orderedPolynomial = new Polynomial();
        orderedPolynomial.setPolynomialMap(sortedMap);
        return orderedPolynomial;
    }
}
