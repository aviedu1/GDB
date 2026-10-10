package com.gdb.tests;

import com.gdb.domain.Account;

public class TestAccount {
    public static void main(String[] args) {
        System.out.println("=== Activity 2: Test Account Suite ===");

        Account acc = new Account("ACC1001", "Rajesh Sharma", 28, 5000.0, "SAVINGS", "ACTIVE");

        // Step 1: Test Initial Balance
        if (acc.getBalance() == 5000.0) {
            System.out.println("Test 1 (Initial Balance 5000.0): [PASS]");
        } else {
            System.out.println("Test 1 (Initial Balance 5000.0): [FAIL]");
        }

        // Step 2: Test Valid Deposit
        boolean depOk = acc.deposit(2000.0);
        if (depOk && acc.getBalance() == 7000.0) {
            System.out.println("Test 2 (Deposit 2000.0 -> Balance 7000.0): [PASS]");
        } else {
            System.out.println("Test 2 (Deposit 2000.0 -> Balance 7000.0): [FAIL]");
        }

        // Step 3: Test Negative Deposit
        boolean negDep = acc.deposit(-500.0);
        if (!negDep && acc.getBalance() == 7000.0) {
            System.out.println("Test 3 (Negative Deposit -> Rejected): [PASS]");
        } else {
            System.out.println("Test 3 (Negative Deposit -> Rejected): [FAIL]");
        }

        // Step 4: Test Valid Withdrawal
        boolean withOk = acc.withdraw(3000.0);
        if (withOk && acc.getBalance() == 4000.0) {
            System.out.println("Test 4 (Withdraw 3000.0 -> Balance 4000.0): [PASS]");
        } else {
            System.out.println("Test 4 (Withdraw 3000.0 -> Balance 4000.0): [FAIL]");
        }

        // Step 5: Test Exceeding Withdrawal
        boolean exceedWith = acc.withdraw(10000.0);
        if (!exceedWith && acc.getBalance() == 4000.0) {
            System.out.println("Test 5 (Exceeding Withdrawal -> Rejected): [PASS]");
        } else {
            System.out.println("Test 5 (Exceeding Withdrawal -> Rejected): [FAIL]");
        }

        // Step 6: Test Negative Withdrawal
        boolean negWith = acc.withdraw(-100.0);
        if (!negWith && acc.getBalance() == 4000.0) {
            System.out.println("Test 6 (Negative Withdrawal -> Rejected): [PASS]");
        } else {
            System.out.println("Test 6 (Negative Withdrawal -> Rejected): [FAIL]");
        }

        System.out.println("All Account tests completed successfully!");
    }
}
