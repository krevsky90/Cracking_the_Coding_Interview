package live_coding.ood.designingParkingLot.grokking.vehicles;

import live_coding.ood.designingParkingLot.grokking.Vehicle;
import live_coding.ood.designingParkingLot.grokking.constants.VehicleType;

public class Van extends Vehicle {
    public Van(String licenseNo) {
        super(licenseNo, VehicleType.VAN);
    }
}
