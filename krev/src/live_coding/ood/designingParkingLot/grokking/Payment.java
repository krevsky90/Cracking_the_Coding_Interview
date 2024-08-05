package live_coding.ood.designingParkingLot.grokking;

import live_coding.ood.designingParkingLot.grokking.constants.PaymentStatus;

public abstract class Payment {
    private final double amount;
    private PaymentStatus status;
//    private long time;  //todo: ???

    public Payment(double amount) {//}, long time) {
        this.amount = amount;
//        this.time = time;
        this.status = PaymentStatus.UNPAID;
    }

    public abstract boolean initiateTransaction();

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

//    public long getTime() {
//        return time;
//    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

//    public void setTime(long time) {
//        this.time = time;
//    }
}
