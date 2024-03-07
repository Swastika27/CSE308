import java.util.HashMap;
import java.util.Map;

public class Bank {
    private double internalFund;
    private HashMap<String, Employees> employeeMap;
    private HashMap<String, Account> accountMap;
    private HashMap<String, Double> loanRequestMap;

    private void addDefaultStaffs() {
        // add 1 managing director
        employeeMap.put("MD", new ManagingDirector());
        // add 2 officer
        for(int i = 0; i < 2; i++) {
            employeeMap.put("S" + (i + 1), new Officer());
        }
        // add 5 cashiers
        for(int i = 0; i < 5; i++) {
            employeeMap.put("C" + (i + 1), new Cashier());
        }
    }
    public Bank() {
        internalFund = 1000000;
        employeeMap = new HashMap<>();
        accountMap = new HashMap<>();
        loanRequestMap = new HashMap<>();

        addDefaultStaffs();
        System.out.println("Bank created");
    }

    public String createAccount(String name, String type, double initialDeposit) throws Exception {
        if(accountMap.get(name) == null) {
            Account account;
            if(type.equalsIgnoreCase("Savings") && initialDeposit >= SavingsDepositBehaviour.minimumInitialDeposit) {
                account = new Savings(name, initialDeposit);
            }
            else if (type.equalsIgnoreCase("Student") && initialDeposit >= SavingsDepositBehaviour.minimumInitialDeposit) {
                account = new Student(name, initialDeposit);
            }
            else if (type.equalsIgnoreCase("Fixed Deposit")) {
                if(initialDeposit >= FixedDepositDepositBehaviour.minimumInitialDeposit) {
                    account = new FixedDeposit(name, initialDeposit);
                }
                else {
                    throw new Exception("Initial deposit for fixed deposit account must not be less than minimum amount");
                }
            }
            else {
                throw new Exception("Invalid command");
            }

            accountMap.put(name, account);
            return name;
        }
        else {
            throw new Exception("Account against this name already exists");
        }
    }
    public boolean isAccount(String name) {
        return accountMap.get(name) != null;
    }
    public boolean isEmployee(String name) {
        return employeeMap.get(name) != null;
    }
    public void depositToAccount(String name, double amount) throws Exception {
        Account account = accountMap.get(name);
        if(account != null) {
            account.deposit(amount);
            internalFund += amount;
        }
        else {
            throw new Exception("Account does not exist");
        }
    }
    public void withdrawFromAccount(String name, double amount) throws Exception {
        Account account = accountMap.get(name);
        if(account != null) {
            account.withdraw(amount);
            internalFund -= amount;
        }
        else {
            throw new Exception("Account does not exist");
        }
    }
    public double getAccountDeposit(String name) throws Exception {
        Account account = accountMap.get(name);
        if(account != null) {
            return account.getDepositAmount();
        }
        else {
            throw new Exception("Account does not exist");
        }
    }

    public void requestLoan(String name, double amount) throws Exception {
        Account account = accountMap.get(name);
        if(account != null) {
            if(account.isValidLoanRequest(amount)) {
                loanRequestMap.put(name, amount);
            }
            else {
                throw new Exception("Loan request is not valid");
            }
        }
        else {
            throw new Exception("Account not exist");
        }
    }
    public void approveAllLoanRequests(String employeeName) {
        for(Map.Entry<String, Double> entry : loanRequestMap.entrySet()) {
            Account account = accountMap.get(entry.getKey());
            Employees employee = employeeMap.get(employeeName);
            if(employee.approveLoan(account, entry.getValue())) {
                internalFund -= entry.getValue();
            }
            else {
                return;
            }
        }
    }
    public boolean changeInterestRate(String employeeName, String accountType, double newRate) throws Exception {
        Employees employee = employeeMap.get(employeeName);
        try {
            employee.changeInterestRate(accountType, newRate);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
    public double seeInternalFund(String employeeName) {
        if (employeeName.equals("MD")) {
            return internalFund;
        }
        else {
            return -1;
        }
    }
    public void incrementYear() {
        for(Account account : accountMap.values()) {
            account.incrementYear();

            double incrementAmount = account.getDepositAmount() * account.getDepositInterestRateBehaviour().getInterestRate();
            internalFund = internalFund - incrementAmount + account.getServiceChargeBehaviour().getServiceCharge();
        }
    }
}
