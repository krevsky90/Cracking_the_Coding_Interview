package java_learning.patterns.structural.proxy;

public class Service implements IService {
    @Override
    public void doSomething() {
        System.out.println("I'm real Service.I do some useful work");
    }
}
