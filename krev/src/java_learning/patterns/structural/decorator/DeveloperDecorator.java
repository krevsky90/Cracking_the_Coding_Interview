package java_learning.patterns.structural.decorator;

/**
 * basic decorator class which only calls given developer
 */
public class DeveloperDecorator implements Developer {
    protected Developer developer;

    public DeveloperDecorator(Developer developer) {
        this.developer = developer;
    }

    @Override
    public String makeJob() {
        return developer.makeJob();
    }
}
