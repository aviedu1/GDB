package com.gdb.repository;

import java.io.*;
import java.util.Properties;

/**
 * Factory creating and supplying repository instances based on persistence.properties configuration.
 */
public class RepositoryFactory {

    private static AccountRepository accountRepositoryInstance;
    private static TransactionRepository transactionRepositoryInstance;

    public static String getPersistenceMode() {
        Properties props = new Properties();
        InputStream is = null;
        try {
            is = RepositoryFactory.class.getClassLoader().getResourceAsStream("config/persistence.properties");
            if (is == null) {
                File file = new File("src/main/resources/config/persistence.properties");
                if (file.exists()) {
                    is = new FileInputStream(file);
                }
            }
            if (is != null) {
                props.load(is);
            }
        } catch (Exception ignored) {
        } finally {
            if (is != null) {
                try { is.close(); } catch (Exception ignored) {}
            }
        }
        return props.getProperty("persistence.mode", "memory");
    }

    public static synchronized AccountRepository getAccountRepository() {
        if (accountRepositoryInstance == null) {
            String mode = getPersistenceMode();
            if ("memory".equalsIgnoreCase(mode)) {
                accountRepositoryInstance = new InMemoryAccountRepository();
            } else if ("jdbc".equalsIgnoreCase(mode)) {
                throw new UnsupportedOperationException("JDBC repository not implemented yet - coming in Activity 22");
            } else if ("file".equalsIgnoreCase(mode)) {
                throw new UnsupportedOperationException("File repository not implemented yet");
            } else {
                accountRepositoryInstance = new InMemoryAccountRepository();
            }
        }
        return accountRepositoryInstance;
    }

    public static synchronized TransactionRepository getTransactionRepository() {
        if (transactionRepositoryInstance == null) {
            String mode = getPersistenceMode();
            if ("memory".equalsIgnoreCase(mode)) {
                transactionRepositoryInstance = new InMemoryTransactionRepository();
            } else if ("jdbc".equalsIgnoreCase(mode)) {
                throw new UnsupportedOperationException("JDBC repository not implemented yet - coming in Activity 22");
            } else if ("file".equalsIgnoreCase(mode)) {
                throw new UnsupportedOperationException("File repository not implemented yet");
            } else {
                transactionRepositoryInstance = new InMemoryTransactionRepository();
            }
        }
        return transactionRepositoryInstance;
    }
}
