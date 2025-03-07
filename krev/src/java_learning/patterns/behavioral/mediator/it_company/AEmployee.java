package java_learning.patterns.behavioral.mediator.it_company;

public abstract class AEmployee implements Employee {
    protected Mediator mediator;
    protected final String name;

    protected AEmployee(String name) {
        this.name = name;
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void beFired() {
        System.out.println(this.name + ": oh no! I'm fired!");
    }
}
