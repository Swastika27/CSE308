public interface LoanBehaviour {
    boolean isValidLoanRequest(double amount) ;
}
class SavingsLoanBehaviour implements LoanBehaviour {
    static double maxLimit = 10000;
    @Override
    public boolean isValidLoanRequest(double amount) {
        return (amount > 0 && amount <= maxLimit);
    }
}
class StudentLoanBehaviour implements LoanBehaviour {
    static double maxLimit = 1000;
    @Override
    public boolean isValidLoanRequest(double amount) {
        return (amount > 0 && amount <= maxLimit);
    }
}
class FixedDepositLoanBehaviour implements LoanBehaviour {
    static double maxLimit = 100000;
    @Override
    public boolean isValidLoanRequest(double amount) {
        return (amount > 0 && amount <= maxLimit);
    }
}

