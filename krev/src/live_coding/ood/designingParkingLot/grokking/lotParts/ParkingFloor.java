package live_coding.ood.designingParkingLot.grokking.lotParts;

import live_coding.ood.designingParkingLot.grokking.Vehicle;
import live_coding.ood.designingParkingLot.grokking.spots.CompactSpot;
import live_coding.ood.designingParkingLot.grokking.spots.HandicappedSpot;
import live_coding.ood.designingParkingLot.grokking.spots.LargeSpot;
import live_coding.ood.designingParkingLot.grokking.spots.MotorCycleSpot;

import java.util.ArrayList;
import java.util.List;

import static live_coding.ood.designingParkingLot.grokking.constants.Constants.MAX_SPOTS_AMOUNT_PER_TYPE_PER_FLOOR;

public class ParkingFloor {
    private final int level;
    private DisplayBoard board;
    private List<ParkingSpot> spots;

    public ParkingFloor(int level) {
        this.level = level;
        this.spots = new ArrayList<>();
    }

    public void addParkingSpot(ParkingSpot spot) {
        this.spots.add(spot);
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.isFree() && spot.isFit(vehicle)) {
                spot.assignVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean unparkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (!spot.isFree() && spot.getVehicle().getLicenseNo().equals(vehicle.getLicenseNo())) {
                spot.removeVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public boolean isFree() {
        for (ParkingSpot spot : spots) {
            if (spot.isFree()) {
                return true;
            }
        }
        return false;
    }

    public void showFreeSpot() {
        board.showFreeSpot();
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public DisplayBoard getBoard() {
        return board;
    }

    public int getLevel() {
        return level;
    }

    public void setDisplay(DisplayBoard board) {
        this.board = board;
    }
}

