package application.services;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoudTest {


    @Test
    public void roudBalance() {
        Double test = 90.00515616516515;

        Double valid = Roud.roudBalance(test);

        assertEquals(Double.valueOf(90.01), valid);
    }
}