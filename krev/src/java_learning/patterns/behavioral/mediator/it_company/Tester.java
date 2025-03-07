package java_learning.patterns.behavioral.mediator.it_company;

public class Tester extends AEmployee {
    public Tester(String name) {
        super(name);
    }

    public void testSystem() {
        System.out.println("Tester tests the system");
    }

    public void writeTests() {
        System.out.println("Tester writes tests");
    }

    public void stopTesting() {
        System.out.println("Tester stops testing");
        mediator.installSystem();
    }

    public void retest() {
        System.out.println("Tester re-testing the system");
    }

    public void createBug() {
        System.out.println("Tester creates bug");
        mediator.bugFixing();
    }

}
