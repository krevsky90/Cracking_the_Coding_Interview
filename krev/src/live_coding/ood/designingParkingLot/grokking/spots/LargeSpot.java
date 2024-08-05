package live_coding.ood.designingParkingLot.grokking.spots;

import live_coding.ood.designingParkingLot.grokking.lotParts.ParkingSpot;
import live_coding.ood.designingParkingLot.grokking.constants.ParkingSpotType;

public class LargeSpot extends ParkingSpot {
    public LargeSpot(String id) {
        super(id, ParkingSpotType.LARGE);
    }
}
