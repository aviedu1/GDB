public class AccountEnhanced{
    private int pin = -1;
    private int accountNumber;
    private int age;
    private String name;
    private String accountType;
    private String status;
    private double balance;
    private double savingMinBalance = 500.0, currentMinBalance = 1000.0;

    public AccountEnhanced(int accountNumber, String name, int age, double initialBalance, String accountType){
        this.accountNumber = accountNumber;
        this.name = name;
        if (age < 18){
            this.age = 18;
        }else{
            this.age = age;
        }
        switch(accountType){
            case "Savings" -> {
                this.accountType = accountType;
                if(initialBalance < savingMinBalance){
                    this.balance = savingMinBalance;
                }else{
                    this.balance = initialBalance;
                }
                break;
            }
            case "Current" -> {
                this.accountType = accountType;
                if(initialBalance < currentMinBalance){
                    this.balance = currentMinBalance;
                }else{
                    this.balance = initialBalance;
                }
                break;
            }
            default -> {
                this.accountType = "Savings"; 
                if(initialBalance < savingMinBalance){
                    this.balance = savingMinBalance;
                }else{
                    this.balance = initialBalance;
                }
                break;
            }
        }
        status = "Active";
    }
    public boolean deposit(double amount){
        if(this.status.equals("Active")){
            if(amount <= 0){
                return false;
            }
            this.balance += amount ;
            return true;
        }else{
            return false;
        }
    }
    public boolean withdraw(double amount, int pin){
        if (!this.status.equals("Active")){return false;}
        if(!verifyPin(pin)){return false;}
        if(amount <=0 || amount > this.balance){
            return false;
        }
        double resultingBalance = this.balance - amount;
        if(accountType.equals("Savings") && resultingBalance < savingMinBalance){
            return false;
        }
        if(accountType.equals("Current") && resultingBalance < currentMinBalance){
            return false;
        }
        this.balance -= amount;
        return true;
    }

    //Getters
    public int getAccountNumber(){
        return this.accountNumber;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public double getBalance(){
        return this.balance;
    }
    public String getAccountType(){
        return this.accountType;
    }
    public String getStatus(){
        return this.status;
    }
    public boolean verifyPin(int pin){
        if(!hasPin()){
            return false;
        }
        return this.pin == pin;
    }
    public boolean hasPin(){
        return this.pin != -1;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public boolean closeAccount(){
        if(this.status.equals("Inactive")){
            return false;
        }
        this.status = "Inactive";
        return true;
    }
    public boolean reopenAccount(){
        if(this.status.equals("Active")){
            return false;
        }
        this.status = "Active";
        return true;
    }
    public boolean setPin(int pin){
        if(pin >=1000 && pin <= 9999){
           this.pin = pin;
           return true; 
        }else{return false;}
    }
}