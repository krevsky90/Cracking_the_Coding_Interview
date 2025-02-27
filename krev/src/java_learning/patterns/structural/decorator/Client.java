package java_learning.patterns.structural.decorator;

public class Client {
    public static void main(String[] args) {
        Developer middle = new JavaDeveloper();
        System.out.println("Middle: " + middle.makeJob());
        Developer senior = new SeniorDeveloperDecorator(middle);
        System.out.println("Senior: " + senior.makeJob());
        Developer teamLead = new TeamLeadDeveloperDecorator(senior);
        System.out.println("Team Lead: " + teamLead.makeJob());
    }
}
