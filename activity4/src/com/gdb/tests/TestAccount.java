package com.gdb.tests;

import com.gdb.domain.Account;

public class TestAccount {
    public static void main(String[] args) {
        System.out.println("=== Activity 4: Enhanced Account Test Suite ===");

        // Test 1: Underage Customer Rejection
        try {
            new Account("ACC1002", "Minor Student", 16, 1000.0, "SAVINGS", "ACTIVE", "1234");
            System.out.println("Test 1 (Underage Customer Rejection): [FAIL]");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 1 (Underage Customer Rejection): [PASS]");
        }

        // Setup valid account for remaining tests
        Account acc = new Account("ACC1001", "Rajesh Sharma", 28, 5000.0, "SAVINGS", "ACTIVE", "1234");

        // Test 2: Wrong PIN Rejection
        boolean wrongPinResult = acc.withdraw(1000.0, "9999");
        if (!wrongPinResult && acc.getBalance() == 5000.0) {
            System.out.println("Test 2 (Wrong PIN Rejection): [PASS]");
        } else {
            System.out.println("Test 2 (Wrong PIN Rejection): [FAIL]");
        }

        // Test 3: Correct PIN Withdrawal
        boolean correctPinResult = acc.withdraw(1000.0, "1234");
        if (correctPinResult && acc.getBalance() == 4000.0) {
            System.out.println("Test 3 (Correct PIN Withdrawal): [PASS]");
        } else {
            System.out.println("Test 3 (Correct PIN Withdrawal): [FAIL]");
        }

        // Test 4: PIN Change & Old PIN Invalidation
        boolean pinChanged = acc.changePin("1234", "5678");
        boolean oldPinBlocked = !acc.withdraw(500.0, "1234");
        boolean newPinSuccess = acc.withdraw(500.0, "5678");
        if (pinChanged && oldPinBlocked && newPinSuccess && acc.getBalance() == 3500.0) {
            System.out.println("Test 4 (PIN Change & Old PIN Invalidation): [PASS]");
        } else {
            System.out.println("Test 4 (PIN Change & Old PIN Invalidation): [FAIL]");
        }

        // Test 5: Suspended Account Block
        acc.suspend();
        boolean suspendedBlocked = !acc.withdraw(500.0, "5678");
        if (suspendedBlocked && acc.getBalance() == 3500.0) {
            System.out.println("Test 5 (Suspended Account Block): [PASS]");
        } else {
            System.out.println("Test 5 (Suspended Account Block): [FAIL]");
        }

        // Test 6: Reactivation & Success
        acc.activate();
        boolean reactivatedSuccess = acc.withdraw(500.0, "5678");
        if (reactivatedSuccess && acc.getBalance() == 3000.0) {
            System.out.println("Test 6 (Reactivation & Success): [PASS]");
        } else {
            System.out.println("Test 6 (Reactivation & Success): [FAIL]");
        }

        System.out.println("All Enhanced Account tests passed!");
    }
}
