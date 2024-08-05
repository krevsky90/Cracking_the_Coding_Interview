package live_coding.ood.designingParkingLot.grokking.lotParts;

import live_coding.ood.designingParkingLot.grokking.ParkingRate;
import live_coding.ood.designingParkingLot.grokking.ParkingTicket;
import live_coding.ood.designingParkingLot.grokking.Vehicle;

import java.util.*;

public class ParkingLot {
    private static ParkingLot instance;

    private final List<ParkingFloor> floors;
    private final List<Entrance> entrances;
    private final List<Exit> exits;
    private final List<ParkingTicket> parkingTickets;

    private ParkingLot() {
        floors = new ArrayList<>();
        entrances = new ArrayList<>();
        exits = new ArrayList<>();
        parkingTickets = new ArrayList<>();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new ParkingLot();
                }
            }
        }
        return instance;
    }

    public synchronized ParkingTicket issueNewParkingTicket() {
        ParkingTicket ticket = new ParkingTicket(UUID.randomUUID().toString(), new Date());
        parkingTickets.add(ticket);
        return ticket;
    }

    public boolean isFree() {
        for (ParkingFloor floor : floors) {
            if (floor.isFree()) {
                return true;
            }
        }
        return false;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            if (floor.parkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            if (floor.unparkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void addEntrance(Entrance entrance) {
        entrances.add(entrance);
    }

    public void addExit(Exit exit) {
        exits.add(exit);
    }

    public void addParkingTicket(ParkingTicket ticket) {
        this.parkingTickets.add(ticket);
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public List<Entrance> getEntrances() {
        return entrances;
    }

    public List<Exit> getExits() {
        return exits;
    }

    public List<ParkingTicket> getParkingTickets() {
        return parkingTickets;
    }

    public void showFreeSpot() {
        for (ParkingFloor floor : floors) {
            floor.showFreeSpot();
        }
    }
}
