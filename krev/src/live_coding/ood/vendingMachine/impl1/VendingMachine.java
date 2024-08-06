package live_coding.ood.vendingMachine.impl1;

import live_coding.ood.vendingMachine.impl1.states.IdleState;
import live_coding.ood.vendingMachine.impl1.states.State;

public class VendingMachine {
    private State currentState;
    private Product selectedProduct;
    private double balance;
    private final Inventory inventory;

    private static VendingMachine instance;

    private VendingMachine() {
        this.currentState = new IdleState(this);
        this.balance = 0.0d;
        this.inventory = Inventory.getInstance();
    }

    public static VendingMachine getInstance() {
        if (instance == null) {
            synchronized (VendingMachine.class) {
                if (instance == null) {
                    instance = new VendingMachine();
                }
            }
        }
        return instance;
    }

    //main methods - start
    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }

    public void selectProduct(Product product) {
        currentState.selectProduct(product);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void returnChange() {
        currentState.returnChange();
    }
    //main methods - end

    //extra methods - start
    public void addCoin(Coin coin) {
        this.balance += coin.getAmount();
    }

    public void resetSelectedProduct() {
        this.selectedProduct = null;
    }

    public void resetBalance() {
        this.balance = 0.0d;
    }
    //extra methods - end

    public void setCurrentState(State state) {
        this.currentState = state;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public double getBalance() {
        return balance;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
