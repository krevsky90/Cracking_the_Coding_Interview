package live_coding.ood.vendingMachine.impl1.states;

import live_coding.ood.vendingMachine.impl1.Coin;
import live_coding.ood.vendingMachine.impl1.Product;
import live_coding.ood.vendingMachine.impl1.VendingMachine;

public abstract class State {
    protected VendingMachine vendingMachine;

    public State(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public abstract void insertCoin(Coin coin);
    public abstract void selectProduct(Product product);
    public abstract void dispenseProduct();
    public abstract void returnChange();
}
