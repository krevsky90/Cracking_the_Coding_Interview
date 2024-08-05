package live_coding.ood.designingParkingLot.grokking;

import live_coding.ood.designingParkingLot.grokking.constants.VehicleType;

public abstract class Vehicle {
    private final String licenseNo;
    private final VehicleType vehicleType;
    private ParkingTicket ticket;

    public Vehicle(String licenseNo, VehicleType vehicleType) {
        this.licenseNo = licenseNo;
        this.vehicleType = vehicleType;
    }

    public boolean assignTicket(ParkingTicket ticket) {
        this.ticket = ticket;
        return true;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String toString() {
        return getVehicleType().name() + " " + licenseNo;
    }
}
