package com.skillbox.fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FibonacciServiceTest {
    private final FibonacciRepository repository = Mockito.mock(FibonacciRepository.class);
    private final FibonacciCalculator calculator = Mockito.mock(FibonacciCalculator.class);
    private final FibonacciService service = new FibonacciService(repository, calculator);

    @Test
    @DisplayName("Test method Fibonacci number if it exists in repository")
    public void testFibonacciNumberIfExistInRepository() {
        int index = 7;
        int value = 13;
        FibonacciNumber fibonacciNumber = new FibonacciNumber(index, value);

        when(repository.findByIndex(index)).thenReturn(Optional.of(fibonacciNumber));
        assertEquals(fibonacciNumber, service.fibonacciNumber(index));
        verify(repository, times(1)).findByIndex(index);
    }

    @Test
    @DisplayName("Test method Fibonacci number if it does not exist in repository")
    public void testFibonacciNumberIfNotExistInRepository() {
        int index = 7;
        FibonacciNumber fibonacciNumber = new FibonacciNumber(7, 13);
        verify(repository, times(0)).findByIndex(index);
        when(repository.findByIndex(index)).thenReturn(Optional.of(new FibonacciNumber(7, 13)));
        assertEquals(fibonacciNumber.getValue(), service.fibonacciNumber(index).getValue());

    }

    @Test
    @DisplayName("Test method Fibonacci number if the index is lower than 1")
    public void testFibonacciNumberIfIndexLowerThanOne() {
        int index = -1;
        assertThrows(IllegalArgumentException.class, () -> service.fibonacciNumber(index));
        verify(repository, times(0)).findByIndex(index);
    }
}
