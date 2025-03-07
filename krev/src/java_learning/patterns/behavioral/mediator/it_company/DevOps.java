package java_learning.patterns.behavioral.mediator.it_company;

public class DevOps extends AEmployee {
    public DevOps(String name) {
        super(name);
    }

    public void deploy() {
        System.out.println("DevOps deploys system");
    }
}
