package live_coding.ood.designingParkingLot.grokking;

public class CashPayment extends Payment {
    public CashPayment(double amount) {
        super(amount);
    }

    @Override
    public boolean initiateTransaction() {
        System.out.println("paid by cash");
        return true;
    }
}
