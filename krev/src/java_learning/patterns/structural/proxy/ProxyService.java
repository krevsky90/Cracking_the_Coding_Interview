package java_learning.patterns.structural.proxy;

public class ProxyService implements IService {
    private Service service;
    private long lastTimeOfCaching;

    public ProxyService() {
        this.service = new Service();
        this.lastTimeOfCaching = 0L;
    }

    private boolean checkAccess() {
        System.out.println("Optionally we can check if current use is authorized to call this service");
        return true;
    }
    @Override
    public void doSomething() {
        System.out.println("I'm proxy");
        if (checkAccess()) {
            if (System.currentTimeMillis() - lastTimeOfCaching < 3000) {
                System.out.println("Return cached result");
            } else {
                System.out.println("Logging: start calling the real service...");
                service.doSomething();
                System.out.println("Logging: the real service finished its work");
                lastTimeOfCaching = System.currentTimeMillis();
            }
        }
    }

    public void resetCache() {
        this.lastTimeOfCaching = 0L;
    }
}
