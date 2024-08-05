package live_coding.ood.designingParkingLot.grokking;

public class CardPayment extends Payment {
    public CardPayment(double amount) {
        super(amount);
    }

    @Override
    public boolean initiateTransaction() {
        System.out.println("paid by card");
        return true;
    }
}
