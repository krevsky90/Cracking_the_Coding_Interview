package java_learning.patterns.structural.facade;

/**
 * this is facade
 */
public class Portal implements IPortal {
    //this initialization is not the best way, just as example
    private ProcessOrchestrator orchestrator = new ProcessOrchestrator();
    private Billing billing = new Billing();

    @Override
    public Product orderProduct(String type, String address) {
        System.out.println("Ordering product...");
        Product product = ProductFactory.createProduct(type);

        orchestrator.process(product);
        billing.createBill(address, product);
        return product;
    }
}
