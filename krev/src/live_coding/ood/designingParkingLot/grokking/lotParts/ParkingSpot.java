package live_coding.ood.designingParkingLot.grokking.lotParts;

import live_coding.ood.designingParkingLot.grokking.constants.ParkingSpotType;
import live_coding.ood.designingParkingLot.grokking.constants.VehicleType;
import live_coding.ood.designingParkingLot.grokking.Vehicle;

public abstract class ParkingSpot {
    private final String id;
    private boolean isFree;
    private Vehicle vehicle;
    private final ParkingSpotType spotType;

    public ParkingSpot(String id, ParkingSpotType spotType) {
        this.id = id;
        this.spotType = spotType;
        this.isFree = true;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        isFree = false;
    }

    public void removeVehicle(Vehicle vehicle) {
        this.vehicle = null;
        this.isFree = true;
    }

    public String getId() {
        return id;
    }

    public boolean isFree() {
        return isFree;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpotType getSpotType() {
        return spotType;
    }

    //extra
    public boolean isFit(Vehicle vehicle) {
        if (spotType == ParkingSpotType.COMPACT && vehicle.getVehicleType() == VehicleType.CAR) return true;
        if (spotType == ParkingSpotType.HANDICAPPED && vehicle.getVehicleType() == VehicleType.CAR) return true;
        if (spotType == ParkingSpotType.LARGE && (vehicle.getVehicleType() == VehicleType.VAN || vehicle.getVehicleType() == VehicleType.TRUCK)) return true;
        if (spotType == ParkingSpotType.MOTORBIKE && vehicle.getVehicleType() == VehicleType.MOTORCYCLE) return true;

        return false;
    }
}
