package ro.tuc.tp;

import controller.OperationsController;
import model.Polynomial;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PolynomialTest {

    @Test
    public void testAddition() {
        Polynomial first = OperationsController.getPolynomial("X^2-3X");
        Polynomial second = OperationsController.getPolynomial("X^2+X");
        Polynomial result =  Polynomial.addition(first, second);
        String resultString = OperationsController.toStringPolynomial(result);
        assertEquals("2X^2-2X",resultString);
    }
    @Test
    public void testSubtraction() {
        Polynomial first = OperationsController.getPolynomial("X^2-3X");
        Polynomial second = OperationsController.getPolynomial("X^2+X");
        Polynomial result = Polynomial.subtraction(first, second);
        String resultString = OperationsController.toStringPolynomial(result);
        assertEquals("-4X",resultString);
    }
    @Test
    public void testMultiply() {
        Polynomial first = OperationsController.getPolynomial("X^2-3X");
        Polynomial second = OperationsController.getPolynomial("X^3");
        Polynomial result = Polynomial.multiply(first, second);
        String resultString = OperationsController.toStringPolynomial(result);
        assertEquals("X^5-3X^4",resultString);
    }
    @Test
    public void testDivide() {
        Polynomial first = OperationsController.getPolynomial("X^3-2X^2+6X-5");
        Polynomial second = OperationsController.getPolynomial("X^2+1");
        List<Polynomial> result = Polynomial.divide(first, second);
        Polynomial Q = result.get(0);
        Polynomial R = result.get(1);
        String resultQ = OperationsController.toStringPolynomial(Q);
        String resultR = OperationsController.toStringPolynomial(R);
        assertEquals("X-2",resultQ);
        assertEquals("5X-3",resultR);
    }
    @Test
    public void testDerivate() {
        Polynomial first = OperationsController.getPolynomial("X^5-3X^2-4");
        Polynomial result =  Polynomial.derivate(first);
        String resultString = OperationsController.toStringPolynomial(result);
        assertEquals("5X^4-6X",resultString);
    }
    @Test
    public void testIntegrate() {
        Polynomial second = OperationsController.getPolynomial("3X^2+4X");
        Polynomial result =  Polynomial.integrate(second);
        String resultString = OperationsController.toStringPolynomial(result);
        assertEquals("X^3+2X^2",resultString);
    }
}
