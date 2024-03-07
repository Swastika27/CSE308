public interface Employees {
    double lookup(Account account);
    boolean approveLoan(Account account, double amount);
    boolean changeInterestRate(String accountType, double newRate) throws Exception;

}

class Cashier implements Employees {
    @Override
    public double lookup(Account account) {
        return account.getDepositAmount();
    }
    @Override
    public boolean approveLoan(Account account, double amount) {
        return false;
    }
    @Override
    public boolean changeInterestRate(String accountType, double newRate) {
        return false;
    }
}
class Officer implements Employees {
    @Override
    public double lookup(Account account) {
        return account.getDepositAmount();
    }
    @Override
    public boolean approveLoan(Account account, double amount) {
        // they can approve loan
        account.approveLoan(amount);
        return true;
    }
    @Override
    public boolean changeInterestRate(String accountType, double newRate) throws Exception {
        return false;
    }
}
class ManagingDirector extends Officer {
    @Override
    public double lookup(Account account) {
        return account.getDepositAmount();
    }
    @Override
    public boolean approveLoan(Account account, double amount) {
        // they can approve loan
        account.approveLoan(amount);
        return true;
    }
    @Override
    public boolean changeInterestRate(String accountType, double newRate) throws Exception {
        if(accountType.equalsIgnoreCase("Savings")) {
            SavingsInterestRate.interestRate = newRate;
            return true;
        }
        else if(accountType.equalsIgnoreCase("Student")) {
            StudentInterestRate.interestRate = newRate;
            return true;
        }
        else if(accountType.equalsIgnoreCase("FixedDeposit")){
            FixedDepositInterestRate.interestRate = newRate;
            return true;
        }
        throw new Exception("Invalid argument");
    }
}
