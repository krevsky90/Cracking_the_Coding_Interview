package live_coding.ood.designingParkingLot.grokking;

import live_coding.ood.designingParkingLot.grokking.accounts.Admin;
import live_coding.ood.designingParkingLot.grokking.accounts.ParkingAgent;
import live_coding.ood.designingParkingLot.grokking.lotParts.*;
import live_coding.ood.designingParkingLot.grokking.spots.CompactSpot;
import live_coding.ood.designingParkingLot.grokking.spots.HandicappedSpot;
import live_coding.ood.designingParkingLot.grokking.spots.LargeSpot;
import live_coding.ood.designingParkingLot.grokking.spots.MotorCycleSpot;
import live_coding.ood.designingParkingLot.grokking.vehicles.Car;
import live_coding.ood.designingParkingLot.grokking.vehicles.MotorCycle;
import live_coding.ood.designingParkingLot.grokking.vehicles.Truck;

import static live_coding.ood.designingParkingLot.grokking.constants.Constants.MAX_SPOTS_AMOUNT_PER_TYPE_PER_FLOOR;

public class ParkingLotDemo {
    public static void main(String[] args) throws NoAvailableSlotsException {
        Person p1 = new Person("p1", "addr1", "phone1", "email1");
        Admin admin = new Admin("admin", "admin", p1);

        Person p2 = new Person("p2", "addr2", "phone2", "email2");
        ParkingAgent agent = new ParkingAgent("agent", "agent", p2);

        ParkingLot parkingLot = initiateParkingLot(admin);

        Vehicle car1 = new Car("c1");
//        Vehicle car2 = new Car("c2");
//        Vehicle truck1 = new Truck("tr1");
//        Vehicle motorcycle1 = new MotorCycle("m1");

        ////demo for parking
        parkingLot.showFreeSpot();
        Entrance entrance = parkingLot.getEntrances().get(0);   //for example
        ParkingTicket ticket = processEntering(car1, entrance, parkingLot);
        parkingLot.showFreeSpot();

        Exit exit = parkingLot.getExits().get(0);   //for example
        processExiting(car1, ticket, exit, parkingLot);
        parkingLot.showFreeSpot();
    }

    public static ParkingTicket processEntering(Vehicle vehicle, Entrance entrance, ParkingLot parkingLot) throws NoAvailableSlotsException {
        if (parkingLot.parkVehicle(vehicle)) {
            ParkingTicket ticket = entrance.getTicket(vehicle);
            vehicle.assignTicket(ticket);
            return ticket;
        } else {
            throw new NoAvailableSlotsException("Unable to park " + vehicle.toString());
        }
    }

    public static boolean processExiting(Vehicle vehicle, ParkingTicket ticket, Exit exit, ParkingLot parkingLot) {
        exit.scanTicket(ticket);
        Payment payment = new CardPayment(ticket.getAmount());
        ticket.setPayment(payment);
        if (exit.processTicket(ticket)) {
            parkingLot.unparkVehicle(vehicle);
            return true;
        }
        return false;
    }

    private static ParkingLot initiateParkingLot(Admin admin) {
        ParkingLot parkingLot = ParkingLot.getInstance();

        for (int level = 0; level < 4; level++) {
            ParkingFloor floor = new ParkingFloor(level);
            admin.addDisplay(floor, new DisplayBoard(level, floor));
            for (int i = 0; i < MAX_SPOTS_AMOUNT_PER_TYPE_PER_FLOOR; i++) {
                admin.addParkingSpot(floor, new CompactSpot(level + "_" + i + "_compact"));
                admin.addParkingSpot(floor, new HandicappedSpot(level + "_" + i + "_handicapped"));
                admin.addParkingSpot(floor, new LargeSpot(level + "_" + i + "_large"));
                admin.addParkingSpot(floor, new MotorCycleSpot(level + "_" + i + "_motorcycle"));
            }
            admin.addParkingFloor(parkingLot, floor);
        }

        admin.addEntrance(parkingLot, new Entrance("entrance1", parkingLot));
        admin.addExit(parkingLot, new Exit("exit1"));
        return parkingLot;
    }
}
