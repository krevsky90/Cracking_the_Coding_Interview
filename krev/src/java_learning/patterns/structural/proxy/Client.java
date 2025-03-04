package java_learning.patterns.structural.proxy;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        IService proxy = new ProxyService();

        proxy.doSomething();
        System.out.println("-----------------------\n");
        Thread.sleep(500);
        proxy.doSomething();
        System.out.println("-----------------------\n");
        Thread.sleep(5000);
        proxy.doSomething();
        System.out.println("-----------------------\n");
    }
}
