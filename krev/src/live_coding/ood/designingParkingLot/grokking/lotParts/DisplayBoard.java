package live_coding.ood.designingParkingLot.grokking.lotParts;

import live_coding.ood.designingParkingLot.grokking.constants.Constants;
import live_coding.ood.designingParkingLot.grokking.constants.ParkingSpotType;

import java.util.HashMap;
import java.util.Map;

public class DisplayBoard {
    private final int id;
    private final ParkingFloor floor;

    public DisplayBoard(int id, ParkingFloor floor) {
        this.id = id;
        this.floor = floor;
    }

    public void showFreeSpot() {
        Map<ParkingSpotType, Integer> map = new HashMap<>();

        for (ParkingSpot spot : floor.getSpots()) {
            if (spot.isFree()) {
                ParkingSpotType type = spot.getSpotType();
                map.put(type, map.getOrDefault(type, 0) + 1);
            }
        }

        StringBuilder sb = new StringBuilder("level: ")
                .append(floor.getLevel()).append(":\n");

        for (ParkingSpotType type : map.keySet()) {
            sb.append("[")
                    .append(type.name())
                    .append(": ")
                    .append(map.get(type))
                    .append("/")
                    .append(Constants.MAX_SPOTS_AMOUNT_PER_TYPE_PER_FLOOR)
                    .append(" available]\n");
        }

        System.out.println(sb);
    }
}
