package live_coding.ood.designingParkingLot.grokking.accounts;

import live_coding.ood.designingParkingLot.grokking.Account;
import live_coding.ood.designingParkingLot.grokking.Person;
import live_coding.ood.designingParkingLot.grokking.lotParts.*;

public class Admin extends Account {
    public Admin(String username, String password, Person person) {
        super(username, password, person);
    }

    public boolean addParkingFloor(ParkingLot parkingLot, ParkingFloor floor) {
        parkingLot.addFloor(floor);
        return true;
    }

    public boolean addParkingSpot(ParkingFloor floor, ParkingSpot spot) {
        floor.addParkingSpot(spot);
        return true;
    }

    public boolean addDisplay(ParkingFloor floor, DisplayBoard board) {
        floor.setDisplay(board);
        return true;
    }

    public boolean addEntrance(ParkingLot parkingLot, Entrance entrance) {
        parkingLot.addEntrance(entrance);
        return true;
    }

    public boolean addExit(ParkingLot parkingLot, Exit exit) {
        parkingLot.addExit(exit);
        return true;
    }

    public boolean resetPassword(String password) {
        setPassword(password);
        return true;
    }
}
