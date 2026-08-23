package com.cepheid.training.ops;

// Scenario 2: Database/connection timeout failure. Simulates a downstream
// inventory system that occasionally times out under load.
public class InventoryClient {

    public int checkAvailableStock(String productId, boolean simulateTimeout) {
        if (simulateTimeout) {
            throw new RuntimeException("Timeout while connecting to inventory-db after 3000ms");
        }
        return 42;
    }
}
