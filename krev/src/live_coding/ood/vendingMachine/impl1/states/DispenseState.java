package live_coding.ood.vendingMachine.impl1.states;

import live_coding.ood.vendingMachine.impl1.Coin;
import live_coding.ood.vendingMachine.impl1.Product;
import live_coding.ood.vendingMachine.impl1.VendingMachine;

public class DispenseState extends State {
public DispenseState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Payment already made. Please collect the dispensed product.");
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected. Please collect the dispensed product.");
    }

    @Override
    public void dispenseProduct() {
        Product product = vendingMachine.getSelectedProduct();
        vendingMachine.getInventory().removeProduct(product);
        System.out.println("Product dispensed: " + product.getName());
        vendingMachine.setCurrentState(new ReturnChangeState(vendingMachine));
    }

    @Override
    public void returnChange() {
        System.out.println("Please collect the dispensed product first.");
    }
}
