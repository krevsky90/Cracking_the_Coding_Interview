package java_learning.patterns.behavioral.mediator.it_company;

import java.util.ArrayList;
import java.util.List;

public class Mediator {
    private Employer employer;
    private List<Developer> developers;
    private List<Tester> testers;
    private DevOps devOps;

    public Mediator() {
        this.developers = new ArrayList<>();
        this.testers = new ArrayList<>();
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
        employer.setMediator(this);          //NOTE: we can set mediator here
    }

    public void addDeveloper(Developer developer) {
        this.developers.add(developer);
        developer.setMediator(this);        //NOTE: we can set mediator here
    }

    public void addTester(Tester tester) {
        this.testers.add(tester);
        tester.setMediator(this);           //NOTE: we can set mediator here
    }

    public void setDevOps(DevOps devOps) {
        this.devOps = devOps;
        devOps.setMediator(this);           //NOTE: we can set mediator here
    }

    public void startDevelopment() {
        for (Developer dev : developers) {
            dev.writeCode();
        }
    }

    public void prepareTests() {
        for (Tester tester : testers) {
            tester.writeTests();
        }
    }

    public void startTesting() {
        for (Tester tester : testers) {
            tester.testSystem();
        }
    }

    public void installSystem() {
        devOps.deploy();
    }

    public void bugFixing() {
        developers.getFirst().bugFixing();
    }

    public void retestIssue() {
        testers.getFirst().retest();
    }
    public void fireAll() {
        for (Developer dev : developers) {
            dev.beFired();
        }

        for (Tester tester : testers) {
            tester.beFired();
        }

        devOps.beFired();
    }




}
