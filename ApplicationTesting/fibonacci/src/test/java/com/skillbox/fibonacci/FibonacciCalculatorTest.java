package com.skillbox.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FibonacciCalculatorTest {

    private FibonacciCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new FibonacciCalculator();
    }

    @Test
    @DisplayName("Test Get Fibonacci number")
    public void testGetFibonacciNumber() {
        int index = 7;
        assertEquals(13, calculator.getFibonacciNumber(index).intValue());
        index = 10;
        assertEquals(55, calculator.getFibonacciNumber(index).intValue());
        index = 30;
        assertEquals(832040, calculator.getFibonacciNumber(index).intValue());
    }

    @Test
    @DisplayName("Test Get Fibonacci number when index is below 1")
    public void testGetFibonacciNumberIfIndexBelowOne() {
        int index = -1;
        assertThrows(IllegalArgumentException.class, () -> calculator.getFibonacciNumber(index));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("Test Get Fibonacci number when index is 1 or 2")
    public void testGetFibonacciNumberIfIndexOneOrTwo (int index) {
        assertEquals(1, calculator.getFibonacciNumber(index).intValue());
    }

}
