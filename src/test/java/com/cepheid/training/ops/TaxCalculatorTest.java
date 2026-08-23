package com.cepheid.training.ops;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculatorTest {

    @Test
    void calculatesEastRegionTax() {
        TaxCalculator calculator = new TaxCalculator();
        assertEquals(7.0, calculator.calculateTax(100.0, "US-EAST"), 0.001);
    }
}
