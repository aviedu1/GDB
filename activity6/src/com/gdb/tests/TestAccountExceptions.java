package com.gdb.tests;

import com.gdb.domain.Account;
import com.gdb.exceptions.*;

public class TestAccountExceptions {
    public static void main(String[] args) {
        System.out.println("=== Activity 6: Exception Handling Suite ===");

        Account acc = new Account("ACC1001", "Rajesh Sharma", 28, 5000.0, "SAVINGS", "ACTIVE", "1234");

        // Step 1: Handle Invalid PIN Exception
        try {
            acc.withdraw(1000.0, "9999");
            System.out.println("[Test 1] Invalid PIN test [FAIL]");
        } catch (InvalidPinException e) {
            System.out.println("[Test 1] Caught Invalid PIN: " + e.getMessage() + " [PASS]");
        } catch (AccountException e) {
            System.out.println("[Test 1] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }

        // Step 2: Handle Inactive Account Exception (Suspended)
        acc.suspend();
        try {
            acc.withdraw(1000.0, "1234");
            System.out.println("[Test 2] Inactive account test [FAIL]");
        } catch (InactiveAccountException e) {
            System.out.println("[Test 2] Caught Inactive Account: " + e.getMessage() + " [PASS]");
        } catch (AccountException e) {
            System.out.println("[Test 2] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }

        // Reactivate for subsequent tests
        acc.activate();

        // Step 3: Handle Invalid Amount Exception
        try {
            acc.deposit(-500.0);
            System.out.println("[Test 3] Invalid amount test [FAIL]");
        } catch (InvalidAmountException e) {
            System.out.println("[Test 3] Caught Invalid Amount: " + e.getMessage() + " [PASS]");
        } catch (Exception e) {
            System.out.println("[Test 3] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }

        // Step 4: Handle Insufficient Balance Exception
        try {
            acc.withdraw(10000.0, "1234");
            System.out.println("[Test 4] Insufficient funds test [FAIL]");
        } catch (InsufficientBalanceException e) {
            System.out.println("[Test 4] Caught Insufficient Funds: " + e.getMessage() + " [PASS]");
        } catch (AccountException e) {
            System.out.println("[Test 4] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }

        // Step 5: Polymorphic Catch with Base Exception (Closed account)
        acc.close();
        try {
            acc.withdraw(1000.0, "1234");
            System.out.println("[Test 5] Polymorphic handler test [FAIL]");
        } catch (AccountException e) {
            System.out.println("[Test 5] Polymorphic Handler caught: " + e.getMessage() + " [PASS]");
        }

        System.out.println("All exception handling tests completed successfully!");
    }
}
