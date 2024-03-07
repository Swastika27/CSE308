abstract public class Account {
    String name;
    double depositAmount;
    double loanAmount;
    DepositBehaviour depositBehaviour;
    LoanBehaviour loanBehaviour;
    ServiceChargeBehaviour serviceChargeBehaviour;
    static double LOAN_INTEREST_RATE = 0.1;
    DepositInterestRateBehaviour depositInterestRateBehaviour;

    Account(String customerName, double initialDeposit) {
        name = customerName;
        depositAmount = initialDeposit;
        loanAmount = 0;
    }
    public double getDepositAmount() {
        return depositAmount;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
    public DepositInterestRateBehaviour getDepositInterestRateBehaviour() {
        return depositInterestRateBehaviour;
    }
    public ServiceChargeBehaviour getServiceChargeBehaviour() {
        return serviceChargeBehaviour;
    }

    public void deposit(double amount) throws Exception {
        if(depositBehaviour.isValidAmount(amount)) {
            depositAmount += amount;
            System.out.println(amount + "$ deposited; current balance " + depositAmount + "$");
        }
        else {
            throw new Exception("Deposit amount not valid");
        }
    }
    public boolean isValidLoanRequest(double amount) {
        return loanBehaviour.isValidLoanRequest(amount);
    }
    public boolean withdraw(double amount) {
        if(amount > 0 && amount <= depositAmount) {
            depositAmount -= amount;
            return true;
        }
        else {
            System.out.println("Invalid transaction; Current balance: " + depositAmount + "$");
            return false;
        }
    };
    public void incrementYear() {
        depositAmount = depositAmount *(1 + depositInterestRateBehaviour.getInterestRate())  - loanAmount * LOAN_INTEREST_RATE - serviceChargeBehaviour.getServiceCharge();
    }

    public void approveLoan(double amount) {
        depositAmount += amount;
        loanAmount = amount;
    }
}

class Savings extends Account {
    static double minimumAllowableBalance = 1000;
    Savings(String name, double initialDeposit) {
        super(name, initialDeposit);
        depositBehaviour = new SavingsDepositBehaviour();
        loanBehaviour = new SavingsLoanBehaviour();
        serviceChargeBehaviour = new SavingsServiceCharge();
        depositInterestRateBehaviour = new SavingsInterestRate();
    }
    @Override
    public boolean withdraw(double amount) {
        if(depositAmount - amount >= minimumAllowableBalance) {
            return super.withdraw(amount);
        }
        else {
            System.out.println("Invalid transaction; Current balance: " + depositAmount + "$");
            return false;
        }
    }

}

class Student extends Account {
    static double withdrawThreshold = 10000;

    Student(String name, double initialDeposit) {
        super(name, initialDeposit);
        depositBehaviour = new StudentDepositBehaviour();
        loanBehaviour = new StudentLoanBehaviour();
        serviceChargeBehaviour = new StudentServiceCharge();
        depositInterestRateBehaviour = new StudentInterestRate();
    }
    @Override
    public boolean withdraw(double amount) {
        if(amount <= withdrawThreshold) {
            return super.withdraw(amount);
        }
        else {
            System.out.println("Invalid transaction; Current balance: " + depositAmount + "$");
            return false;
        }
    }
}
class FixedDeposit extends Account {
    private int yearsPassed;
    FixedDeposit(String name, double depositAmount) {
        super(name, depositAmount);
        depositBehaviour = new FixedDepositDepositBehaviour();
        loanBehaviour = new FixedDepositLoanBehaviour();
        serviceChargeBehaviour = new FixedDepositServiceCharge();
        yearsPassed = 0;
        depositInterestRateBehaviour = new FixedDepositInterestRate();
    }

    @Override
    public boolean withdraw(double amount) {
        if(yearsPassed > 0) {
            return super.withdraw(amount);
        }
        else {
            System.out.println("Account has not reached its maturity period");
            return false;
        }
    }
    @Override
    public void incrementYear() {
        super.incrementYear();
        yearsPassed++;
    }
}