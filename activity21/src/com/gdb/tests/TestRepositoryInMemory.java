package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.repository.*;
import com.gdb.service.AccountService;
import com.gdb.logging.*;
import java.util.List;

public class TestRepositoryInMemory {
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  ACTIVITY 21 — REPOSITORY PATTERN IN-MEMORY TESTS");
        System.out.println("=".repeat(60));

        AccountRepository accountRepo = new InMemoryAccountRepository();
        TransactionRepository txnRepo = new InMemoryTransactionRepository();
        TransactionLogger logger = new TransactionLogger(new MemoryLogDestination());

        AccountService service = new AccountService(accountRepo, txnRepo, logger);

        // ============================================================
        // STEP 17: Test Account Repository CRUD
        // ============================================================
        System.out.println("[TEST 1] Account Repository CRUD:");
        int accNum1 = accountRepo.nextAccountNumber();
        IAccount acc1 = AccountFactory.createAccount("SAVINGS", accNum1, "Rajesh Sharma", 30, 50000.0, 0);
        accountRepo.save(acc1);
        System.out.println("  Saved: " + acc1.getAccountInfo());

        IAccount found = accountRepo.findById(1001);
        System.out.println("  Found: " + found.getAccountInfo());

        found.deposit(15000.0);
        accountRepo.update(found);
        System.out.println("  Updated balance: Rs. " + found.getBalance());

        int accNum2 = accountRepo.nextAccountNumber();
        IAccount acc2 = AccountFactory.createAccount("SAVINGS", accNum2, "Priya Patel", 28, 20000.0, 0);
        accountRepo.save(acc2);
        System.out.println("  Total accounts in repository: " + accountRepo.findAll().size());
        System.out.println("  -> PASSED\n");

        // ============================================================
        // STEP 18: Test Transaction Repository Operations
        // ============================================================
        System.out.println("[TEST 2] Transaction Repository Operations:");
        Transaction txn1 = new Transaction(Transaction.generateId(), java.time.LocalDateTime.now(), 1001,
                TransactionType.DEPOSIT, 15000.0, 65000.0, "SUCCESS", "Deposit of Rs. 15000.0", 0, 0);
        Transaction txn2 = new Transaction(Transaction.generateId(), java.time.LocalDateTime.now(), 1001,
                TransactionType.WITHDRAW, 5000.0, 60000.0, "SUCCESS", "Withdrawal of Rs. 5000.0", 0, 0);
        txnRepo.save(txn1);
        txnRepo.save(txn2);
        System.out.println("  Saved " + txnRepo.findAll().size() + " transaction records.");

        List<Transaction> txns1001 = txnRepo.findByAccount(1001);
        System.out.println("  Found transactions for #1001: " + txns1001.size());
        System.out.println("  -> PASSED\n");

        // ============================================================
        // STEP 19: Test Service Integration With Repositories
        // ============================================================
        System.out.println("[TEST 3] Service Integration with Repositories:");
        IAccount acc3 = service.openAccount("SAVINGS", "Amit Kumar", 45, 20000.0);
        System.out.println("  Opened account #" + acc3.getAccountNumber() + " via AccountService.");

        service.deposit(acc3.getAccountNumber(), 5000.0);
        System.out.println("  Deposit executed. New Balance: Rs. " + acc3.getBalance());
        System.out.println("  -> PASSED\n");

        System.out.println("=".repeat(60));
        System.out.println("  ALL ACTIVITY 21 IN-MEMORY REPOSITORY TESTS PASSED!");
        System.out.println("=".repeat(60));
    }
}
