package java_learning.patterns.behavioral.mediator.it_company;

public class Developer extends AEmployee {
    public Developer(String name) {
        super(name);
    }

    public void writeCode() {
        System.out.println("Developer writes code");
    }

    public void stopCoding() {
        System.out.println("Developer stops writing code");
        mediator.startTesting();
    }

    public void bugFixing() {
        System.out.println("Developer is fixing bug");
    }

    public void requestRetest() {
        System.out.println("Developer requests re-test of fixed issue");
        mediator.retestIssue();
    }

}
