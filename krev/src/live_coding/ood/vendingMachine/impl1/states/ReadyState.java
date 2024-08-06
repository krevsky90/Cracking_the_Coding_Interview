package live_coding.ood.vendingMachine.impl1.states;

import live_coding.ood.vendingMachine.impl1.Coin;
import live_coding.ood.vendingMachine.impl1.Product;
import live_coding.ood.vendingMachine.impl1.VendingMachine;

public class ReadyState extends State {

    public ReadyState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertCoin(Coin coin) {
        vendingMachine.addCoin(coin);
        System.out.println("Coin inserted: " + coin);
        checkBalance();
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected. Please make payment.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please make payment first.");
    }

    @Override
    public void returnChange() {
        System.out.println("Please make payment first.");
    }

    private void checkBalance() {
        if (vendingMachine.getSelectedProduct().getPrice() <= vendingMachine.getBalance()) {
            vendingMachine.setCurrentState(new DispenseState(vendingMachine));
        }
    }
}
