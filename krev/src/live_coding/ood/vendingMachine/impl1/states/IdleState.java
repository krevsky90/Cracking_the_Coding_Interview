package live_coding.ood.vendingMachine.impl1.states;

import live_coding.ood.vendingMachine.impl1.Coin;
import live_coding.ood.vendingMachine.impl1.Product;
import live_coding.ood.vendingMachine.impl1.VendingMachine;

public class IdleState extends State {
    public IdleState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select Product first");
    }

    @Override
    public void selectProduct(Product product) {
        if (vendingMachine.getInventory().isAvailable(product)) {
            vendingMachine.setSelectedProduct(product);
            vendingMachine.setCurrentState(new ReadyState(vendingMachine));
            System.out.println("Product selected: " + product.getName());
        } else {
            System.out.println("Product not available: " + product.getName());
        }
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select Product first");
    }

    @Override
    public void returnChange() {
        System.out.println("No change to return");
    }
}
