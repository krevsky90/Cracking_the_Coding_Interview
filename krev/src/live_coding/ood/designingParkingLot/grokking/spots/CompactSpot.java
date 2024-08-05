package live_coding.ood.designingParkingLot.grokking.spots;

import live_coding.ood.designingParkingLot.grokking.lotParts.ParkingSpot;
import live_coding.ood.designingParkingLot.grokking.constants.ParkingSpotType;

public class CompactSpot extends ParkingSpot {
    public CompactSpot(String id) {
        super(id, ParkingSpotType.COMPACT);
    }
}
