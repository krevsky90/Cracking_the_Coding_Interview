package live_coding.ood.designingParkingLot.grokking.lotParts;

import live_coding.ood.designingParkingLot.grokking.ParkingTicket;
import live_coding.ood.designingParkingLot.grokking.Vehicle;

public class Entrance {
    private final String id;
    private final ParkingLot parkingLot;

    public Entrance(String id, ParkingLot parkingLot) {
        this.id = id;
        this.parkingLot = parkingLot;
    }

    public ParkingTicket getTicket(Vehicle vehicle) {
        ParkingTicket ticket = parkingLot.issueNewParkingTicket();
        parkingLot.addParkingTicket(ticket);
        return ticket;
    }

    public String getId() {
        return id;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }
}
