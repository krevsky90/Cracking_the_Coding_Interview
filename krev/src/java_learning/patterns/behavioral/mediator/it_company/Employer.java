package java_learning.patterns.behavioral.mediator.it_company;

/**
 * Employer DOES NOT extend AEmployee! since it can't be fired!
 */
public class Employer implements Employee {
    private Mediator mediator;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void startProject() {
        System.out.println("Employer starts project");
        mediator.prepareTests();
        mediator.startDevelopment();
    }

    public void fireAll() {
        System.out.println("Employer fires All employees");
        mediator.fireAll();
    }
}
