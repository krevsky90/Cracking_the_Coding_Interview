package live_coding.ood.designingParkingLot.grokking.vehicles;

import live_coding.ood.designingParkingLot.grokking.Vehicle;
import live_coding.ood.designingParkingLot.grokking.constants.VehicleType;

public class Truck extends Vehicle {
    public Truck(String licenseNo) {
        super(licenseNo, VehicleType.TRUCK);
    }
}
