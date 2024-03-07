public interface ServiceChargeBehaviour {
    double getServiceCharge() ;
}
class SavingsServiceCharge implements ServiceChargeBehaviour {
    static double serviceCharge = 500;

    @Override
    public double getServiceCharge() {
        return serviceCharge;
    }
}

class StudentServiceCharge implements ServiceChargeBehaviour {
    static double serviceCharge = 0;

    @Override
    public double getServiceCharge() {
        return serviceCharge;
    }
}
class FixedDepositServiceCharge implements ServiceChargeBehaviour {
    static double serviceCharge = 500;

    @Override
    public double getServiceCharge() {
        return serviceCharge;
    }
}
