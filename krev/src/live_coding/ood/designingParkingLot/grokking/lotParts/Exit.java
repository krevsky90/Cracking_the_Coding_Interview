package live_coding.ood.designingParkingLot.grokking.lotParts;

import live_coding.ood.designingParkingLot.grokking.ParkingRate;
import live_coding.ood.designingParkingLot.grokking.ParkingTicket;
import live_coding.ood.designingParkingLot.grokking.constants.PaymentStatus;

import java.util.Date;

public class Exit {
    private final String id;

    public Exit(String id) {
        this.id = id;
    }

    public void scanTicket(ParkingTicket ticket) {
        /*
         Perform validation logic for the parking ticket
         Calculate parking charges, if necessary
         Handle the exit process
        */
        ticket.setEndTime(new Date());
        double hours = (ticket.getEndTime().getTime() - ticket.getTimestamp().getTime())/1000.0/3600;
        ParkingRate rate = new ParkingRate(hours, 5);
        double fee = rate.calculate();
        ticket.setAmount(fee);
    }

    public boolean processTicket(ParkingTicket ticket) {
        if (ticket.getPayment().initiateTransaction()) {
            System.out.println("successfully paid");
            ticket.getPayment().setStatus(PaymentStatus.COMPLETED);
            return true;
        }
        System.out.println("transaction failed");
        return false;

    }

    public String getId() {
        return id;
    }
}
