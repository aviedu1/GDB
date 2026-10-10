package com.gdb;

import com.gdb.domain.IAccount;
import com.gdb.logging.FileLogDestination;
import com.gdb.logging.LogDestination;
import com.gdb.logging.TransactionLogger;
import com.gdb.service.AccountService;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Global Digital Bank — Service Demo");
        System.out.println("=".repeat(60));

        // STEP 20: Wire Up Dependencies
        LogDestination dest = new FileLogDestination();
        TransactionLogger logger = new TransactionLogger(dest);
        AccountService service = new AccountService(logger);

        // STEP 21: Run A Demo Workflow
        IAccount acc1 = service.openAccount("SAVINGS", "Alice", 28, 20000);
        acc1.setPin(1111);
        IAccount acc2 = service.openAccount("CURRENT", "Bob", 35, 30000);
        acc2.setPin(2222);

        service.deposit(acc1.getAccountNumber(), 5000);
        service.withdraw(acc1.getAccountNumber(), 2000, 1111);
        service.transfer(acc1.getAccountNumber(), acc2.getAccountNumber(), 3000, 1111);

        System.out.println("All demo operations completed successfully.");
    }
}
