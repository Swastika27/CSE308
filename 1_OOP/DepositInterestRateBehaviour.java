public interface DepositInterestRateBehaviour {
    double getInterestRate() ;
    void setInterestRate(double newInterestRate) ;
}

class StudentInterestRate implements DepositInterestRateBehaviour {
    static double interestRate = 0.05;

    @Override
    public double getInterestRate() {
        return interestRate;
    }
    @Override
    public void setInterestRate(double newInterestRate) {
        StudentInterestRate.interestRate = newInterestRate;
    }
}
class SavingsInterestRate implements DepositInterestRateBehaviour {
    static double interestRate = 0.1;
    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void setInterestRate(double interestRate) {
        SavingsInterestRate.interestRate = interestRate;
    }
}
class FixedDepositInterestRate implements DepositInterestRateBehaviour {
    static double interestRate = 0.15;
    @Override
    public double getInterestRate() {
        return interestRate;
    }
    @Override
    public void setInterestRate(double newRate) {
        FixedDepositInterestRate.interestRate = newRate;
    }
}
