package java_learning.patterns.structural.decorator;

public class TeamLeadDeveloperDecorator extends DeveloperDecorator implements Developer {
    public TeamLeadDeveloperDecorator(Developer developer) {
        super(developer);
    }

    public String makeJob() {
        return developer.makeJob() + " Sends weekly report.";
    }
}
