package live_coding.ood.designingParkingLot.grokking.accounts;

import live_coding.ood.designingParkingLot.grokking.Account;
import live_coding.ood.designingParkingLot.grokking.ParkingTicket;
import live_coding.ood.designingParkingLot.grokking.Person;

public class ParkingAgent extends Account {
    public ParkingAgent(String username, String password, Person person) {
        super(username, password, person);
    }

    public void processTicket(ParkingTicket ticket) {
        //todo:

    }

    public boolean resetPassword(String password) {
        setPassword(password);
        return true;
    }
}
