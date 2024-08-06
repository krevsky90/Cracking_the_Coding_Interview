package live_coding.ood.vendingMachine.impl1.states;

import live_coding.ood.vendingMachine.impl1.Coin;
import live_coding.ood.vendingMachine.impl1.Product;
import live_coding.ood.vendingMachine.impl1.VendingMachine;

public class ReturnChangeState extends State {
    public ReturnChangeState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please collect the change first.");
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Please collect the change first.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Product already dispensed. Please collect the change.");
    }

    @Override
    public void returnChange() {
        double delta = vendingMachine.getBalance() - vendingMachine.getSelectedProduct().getPrice();
        if (delta > 0) {
            System.out.println("Return change: " + delta);
            vendingMachine.resetBalance();
        } else {
            System.out.println("No change to return.");
        }

        vendingMachine.resetSelectedProduct();
        vendingMachine.setCurrentState(new IdleState(vendingMachine));
    }
}
