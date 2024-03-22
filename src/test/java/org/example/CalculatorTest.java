package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void testSum() {
        Calculator calculator = new Calculator();
        int actual = calculator.sum(2, 5);
        Assertions.assertEquals(7, actual);
    }

}