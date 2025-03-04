package java_learning.patterns.structural.facade;

public class ProductFactory {
    public static Product createProduct(String type) {
        Product product = null;
        if ("TV".equals(type)) {
            product = new TV("my " + type);
        } else if ("Mobile".equals(type)) {
            product = new MobilePhone("my " + type);
        } else {
            throw new IllegalArgumentException("Unknown product type " + type);
        }

        System.out.println("product " + product.name + " has been created");
        return product;
    }
}
