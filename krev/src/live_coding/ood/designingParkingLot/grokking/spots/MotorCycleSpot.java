package live_coding.ood.designingParkingLot.grokking.spots;

import live_coding.ood.designingParkingLot.grokking.lotParts.ParkingSpot;
import live_coding.ood.designingParkingLot.grokking.constants.ParkingSpotType;

public class MotorCycleSpot extends ParkingSpot {
    public MotorCycleSpot(String id) {
        super(id, ParkingSpotType.MOTORBIKE);
    }
}
