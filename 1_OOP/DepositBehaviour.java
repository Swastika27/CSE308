interface DepositBehaviour {
    boolean isValidAmount(double amount);
}
class SavingsDepositBehaviour implements DepositBehaviour {
    static double depositThreshold = 0;
    static double minimumInitialDeposit = 0;

    @Override
    public boolean isValidAmount(double amount) {
        return amount >= depositThreshold;
    }
}
class StudentDepositBehaviour implements DepositBehaviour {
    static double depositThreshold = 0;
    static double minimumInitialDeposit = 0;
    @Override
    public boolean isValidAmount(double amount) {
        return amount >= depositThreshold;
    }
}
class FixedDepositDepositBehaviour implements DepositBehaviour {
    static double depositThreshold = 50000;
    static double minimumInitialDeposit = 100000;
    @Override
    public boolean isValidAmount(double amount) {
        return amount >= depositThreshold;
    }
}
