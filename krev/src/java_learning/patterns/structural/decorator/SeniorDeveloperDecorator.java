package java_learning.patterns.structural.decorator;

public class SeniorDeveloperDecorator extends DeveloperDecorator implements Developer {
    public SeniorDeveloperDecorator(Developer developer) {
        super(developer);
    }

    @Override
    public String makeJob() {
        return developer.makeJob() + " Performs code review.";
    }
}
