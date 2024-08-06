package live_coding.ood.vendingMachine.impl1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private final Map<Product, Integer> productsToAmountMap;

    private static Inventory instance;

    private Inventory() {
        productsToAmountMap = new ConcurrentHashMap<>();
    }

    public static Inventory getInstance() {
        if (instance == null) {
            synchronized (Inventory.class) {
                if (instance == null) {
                    instance = new Inventory();
                }
            }
        }
        return instance;
    }

    public synchronized void addProduct(Product product, int quantity) {
        int initialQuantity = productsToAmountMap.getOrDefault(product, 0);
        productsToAmountMap.put(product, initialQuantity + quantity);
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void removeProduct(Product product) {
        if (productsToAmountMap.containsKey(product)) {
            int quantity = productsToAmountMap.get(product);
            if (quantity == 1) {
                productsToAmountMap.remove(product);
            } else {
                productsToAmountMap.put(product, quantity - 1);
            }
        }
    }

    public boolean isAvailable(Product product) {
        return productsToAmountMap.containsKey(product);
    }
}
