package live_coding.ood.vendingMachine.impl1;

public enum Coin {
    PENNY(0.01),
    QUARTER(0.25d)
    ;

    private final double amount;

     Coin(double amount) {
         this.amount = amount;
     }

     public double getAmount() {
         return this.amount;
     }
}
