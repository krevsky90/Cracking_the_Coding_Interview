package java_learning.patterns.structural.facade;

public class Billing {

    public void createBill(String address, Product product) {
        System.out.println("Creating bill for user with address " + address + " and product " + product);
    }
}
