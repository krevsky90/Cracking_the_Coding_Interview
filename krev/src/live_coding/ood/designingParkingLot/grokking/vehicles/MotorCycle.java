package live_coding.ood.designingParkingLot.grokking.vehicles;

import live_coding.ood.designingParkingLot.grokking.Vehicle;
import live_coding.ood.designingParkingLot.grokking.constants.VehicleType;

public class MotorCycle extends Vehicle {
    public MotorCycle(String licenseNo) {
        super(licenseNo, VehicleType.MOTORCYCLE);
    }
}
