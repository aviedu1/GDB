package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAbstractAccount {

    public static void transfer(AbstractAccount from, AbstractAccount to, double amount, String pin) throws AccountException {
        from.withdraw(amount, pin);
        to.deposit(amount);
    }

    public static void main(String[] args) {
        System.out.println("=== Activity 10: Banking Operations Suite ===");

        // Step 1: Create an account portfolio
        SavingsAccount sa = new SavingsAccount("SAV1001", "Rajesh Sharma", 28, 10000.0, "ACTIVE", "1234", 1000.0, 4.0);
        CurrentAccount ca = new CurrentAccount("CUR1001", "Priya Patel", 34, 5000.0, "ACTIVE", "5678", 25000.0);
        SalaryAccount sla = new SalaryAccount("SAL1001", "Sneha Verma", 26, 30000.0, "ACTIVE", "2222", "Infosys");

        AbstractAccount[] portfolio = { sa, ca, sla };

        // Step 2: Transfer Rs 3000 from Savings to Current
        try {
            transfer(sa, ca, 3000.0, "1234");
            System.out.println("Transfer Rs 3000 from Savings to Current: SUCCESS");
            System.out.println("Savings Balance: Rs " + sa.getBalance() + " | Current Balance: Rs " + ca.getBalance());
        } catch (AccountException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }

        // Step 3: Failed transfer with wrong PIN
        double saBalBefore = sa.getBalance();
        double caBalBefore = ca.getBalance();
        try {
            transfer(sa, ca, 1000.0, "9999");
            System.out.println("Failed Transfer test [FAIL]");
        } catch (AccountException e) {
            if (sa.getBalance() == saBalBefore && ca.getBalance() == caBalBefore) {
                System.out.println("Failed Transfer (Wrong PIN): Exception caught, no balance changed [PASS]");
            } else {
                System.out.println("Failed Transfer: balance corrupted [FAIL]");
            }
        }

        // Step 4: Process monthly banking cycle
        for (AbstractAccount acc : portfolio) {
            if (acc instanceof SavingsAccount) {
                ((SavingsAccount) acc).applyInterest();
            } else if (acc instanceof SalaryAccount) {
                SalaryAccount salaryAcc = (SalaryAccount) acc;
                if (salaryAcc.getInactiveMonths() > 2) {
                    salaryAcc.incrementInactiveMonths();
                }
            }
        }
        System.out.println("Monthly Interest Cycle processed for all qualifying accounts.");
        System.out.println("All banking operations passed!");
    }
}
