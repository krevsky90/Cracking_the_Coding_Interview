package live_coding.ood.designingParkingLot.grokking.spots;

import live_coding.ood.designingParkingLot.grokking.lotParts.ParkingSpot;
import live_coding.ood.designingParkingLot.grokking.constants.ParkingSpotType;

public class HandicappedSpot extends ParkingSpot {
    public HandicappedSpot(String id) {
        super(id, ParkingSpotType.HANDICAPPED);
    }
}
