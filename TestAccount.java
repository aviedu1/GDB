public class TestAccount {
    public static void main(String[] args){
        System.out.println("=".repeat(30));
        System.out.println("GLOBAL DIGITAL BANK - ACCOUNT TEST");
        System.out.println("=".repeat(30));

        System.out.println(">>> 1. Creating Account");

        AccountEnhanced a1 = new AccountEnhanced(1001, "John Doe", 25, 1000.0, "Savings");
        System.out.println("Account #"+a1.getAccountNumber()+" | "+a1.getName()+"("+a1.getAge()+" yrs) | "+a1.getAccountType()+" | ₹"+a1.getBalance()+" | "+a1.getStatus());

        System.out.println("Account Created!");

        System.out.println(">>> 2. Deposit Money");
        if (a1.deposit(500.0)){
            System.out.println("Depositing ₹500.0: SUCCESS");
            System.out.println("New balance: ₹"+a1.getBalance());
        }
        else{
            System.out.println("Depositing ₹500.0: FAILED");
        }
        if (a1.deposit(-100.0)){
            System.out.println("Depositing ₹-100.0: SUCCESS");
        }
        else{
            System.out.println("Depositing ₹-100.0: FAILED (Invalid amount)");
        }

        System.out.println(">>> 3. Withdraw Money");

        if(a1.withdraw(200.0)){
            System.out.println("Withdrawing ₹200.0: SUCCESS");
            System.out.println("New balance: ₹"+a1.getBalance());

        }else{
            System.out.println("Withdrawing ₹200.0: FAILED");
        }
        if(a1.withdraw(2000.0)){
            System.out.println("Withdrawing ₹2000.0: SUCCESS");
            System.out.println("New balance: ₹"+a1.getBalance());

        }else{
            System.out.println("Withdrawing ₹2000.0: FAILED (Insufficient balance)");
            System.out.println("Current balance: ₹"+a1.getBalance());
        }

        System.out.println(">>> 4. Creating Another Account");

        AccountEnhanced a2 = new AccountEnhanced(1002, "Jane Smith", 30, 2000.0, "Current");

        System.out.println("Account #"+a2.getAccountNumber()+" | "+a2.getName()+"("+a2.getAge()+" yrs) | "+a2.getAccountType()+" | ₹"+a2.getBalance()+" | "+a2.getStatus());

        System.out.println(">>> 5. All Account");

        System.out.println("Account #"+a1.getAccountNumber()+" | "+a1.getName()+"("+a1.getAge()+" yrs) | "+a1.getAccountType()+" | ₹"+a1.getBalance()+" | "+a1.getStatus());
        System.out.println("Account #"+a2.getAccountNumber()+" | "+a2.getName()+"("+a2.getAge()+" yrs) | "+a2.getAccountType()+" | ₹"+a2.getBalance()+" | "+a2.getStatus());

        System.out.println("=".repeat(30));
        System.out.println("TEST COMPLETED!");
        System.out.println("=".repeat(30));




    }
}
