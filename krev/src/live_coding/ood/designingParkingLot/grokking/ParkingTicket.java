package live_coding.ood.designingParkingLot.grokking;

import java.util.Date;

public class ParkingTicket {
    private final String ticketNo;
    private Date timestamp; //start time
    private Date endTime;
    private double amount;
    private Payment payment;

    public ParkingTicket(String ticketNo, Date timestamp) {
        this.ticketNo = ticketNo;
        this.timestamp = timestamp;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Date getEndTime() {
        return endTime;
    }

    public double getAmount() {
        return amount;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
