package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAccountSubclasses {
    public static void main(String[] args) {
        System.out.println("=== Activity 8: Polymorphism Test ===");

        // Step 1: SavingsAccount minimum balance breach
        Account sa = new SavingsAccount("SAV1001", "Rajesh Sharma", 28, 10000.0, "ACTIVE", "1234", 1000.0, 4.0);
        try {
            sa.withdraw(9500.0, "1234");
            System.out.println("[Savings] Withdraw 9500 (breaches min balance 1000): [FAIL]");
        } catch (MinimumBalanceViolationException e) {
            System.out.println("[Savings] Withdraw 9500 (breaches min balance 1000): Caught MinimumBalanceViolationException [PASS]");
        } catch (Exception e) {
            System.out.println("[Savings] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }

        // Step 2: CurrentAccount valid withdrawal utilizing overdraft facility
        Account ca = new CurrentAccount("CUR1001", "Priya Patel", 34, 5000.0, "ACTIVE", "5678", 25000.0);
        try {
            ca.withdraw(10000.0, "5678");
            if (ca.getBalance() == -5000.0) {
                System.out.println("[Current] Withdraw with Overdraft (Balance goes to -5000): SUCCESS [PASS]");
            } else {
                System.out.println("[Current] Withdraw with Overdraft: Unexpected balance " + ca.getBalance() + " [FAIL]");
            }
        } catch (Exception e) {
            System.out.println("[Current] Withdraw with Overdraft failed: " + e.getMessage() + " [FAIL]");
        }

        // Step 3: CurrentAccount exceeding overdraft limit
        try {
            ca.withdraw(30000.0, "5678");
            System.out.println("[Current] Withdraw exceeding Overdraft: [FAIL]");
        } catch (InsufficientBalanceException e) {
            System.out.println("[Current] Withdraw exceeding Overdraft (exceeds -25000): Caught InsufficientBalanceException [PASS]");
        } catch (Exception e) {
            System.out.println("[Current] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }

        // Step 4: FixedDepositAccount premature withdrawal block
        Account fda = new FixedDepositAccount("FD1001", "Amit Kumar", 45, 50000.0, "ACTIVE", "1111", 12, 6.5);
        try {
            fda.withdraw(5000.0, "1111");
            System.out.println("[FixedDeposit] Withdraw attempt: [FAIL]");
        } catch (AccountException e) {
            System.out.println("[FixedDeposit] Withdraw attempt: Caught AccountException [PASS]");
        } catch (Exception e) {
            System.out.println("[FixedDeposit] Unexpected exception: " + e.getMessage() + " [FAIL]");
        }

        System.out.println("All polymorphic behaviors verified!");
    }
}
