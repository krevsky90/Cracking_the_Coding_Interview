package live_coding.ood.designingParkingLot.grokking.vehicles;

import live_coding.ood.designingParkingLot.grokking.Vehicle;
import live_coding.ood.designingParkingLot.grokking.constants.VehicleType;

public class Car extends Vehicle {
    public Car(String licenseNo) {
        super(licenseNo, VehicleType.CAR);
    }
}
