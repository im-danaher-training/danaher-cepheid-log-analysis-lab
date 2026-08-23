package com.cepheid.training.ops;

// Scenario 3: Incorrect business calculation. Tax rate lookup accidentally
// uses the wrong key for the "US-WEST" region (copy/paste defect).
public class TaxCalculator {

    public double calculateTax(double amount, String region) {
        double rate;
        switch (region) {
            case "US-EAST":
                rate = 0.07;
                break;
            case "US-WEST":
                rate = 0.07; // BUG: should be 0.0925 for US-WEST
                break;
            default:
                rate = 0.05;
        }
        return amount * rate;
    }
}
