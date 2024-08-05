package live_coding.ood.designingParkingLot.grokking;

public class NoAvailableSlotsException extends Exception {
    public NoAvailableSlotsException(String message) {
        super(message);
    }
}
