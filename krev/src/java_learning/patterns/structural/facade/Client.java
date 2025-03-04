package java_learning.patterns.structural.facade;

public class Client {
    public static void main(String[] args) {
        IPortal portalFacade = new Portal();
        Product product = portalFacade.orderProduct("TV", "Usa, NY, 11 avenue");

    }
}
