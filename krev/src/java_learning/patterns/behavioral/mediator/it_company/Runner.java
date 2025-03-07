package java_learning.patterns.behavioral.mediator.it_company;

public class Runner {
    public static void main(String[] args) {
        Developer dev1 = new Developer("dev1");
        Developer dev2 = new Developer("dev2");
        Tester tester1 = new Tester("tester1");
        Tester tester2 = new Tester("tester2");
        DevOps devOps = new DevOps("devops");
        Employer employer = new Employer();

        Mediator mediator = new Mediator();
        mediator.addDeveloper(dev1);
        mediator.addDeveloper(dev2);
        mediator.addTester(tester1);
        mediator.addTester(tester2);
        mediator.setEmployer(employer);
        mediator.setDevOps(devOps);
        System.out.println("---");
        employer.startProject();
        dev1.stopCoding();
        System.out.println("---");
        tester1.createBug();
        dev1.requestRetest();
        System.out.println("---");
        employer.fireAll();

    }
}
