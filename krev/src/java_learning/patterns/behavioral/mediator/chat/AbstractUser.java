package java_learning.patterns.behavioral.mediator.chat;

public abstract class AbstractUser {
    private IMediator mediator;
    private final String name;

    public AbstractUser(IMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    //all interaction with other components are handled by mediator! no direct interactions!
    public void sendMessage(String message) {
        this.mediator.notify(this, message);
    }

    public void readMessage(String message) {
        System.out.println(this.name + " is receiving the message '" + message + "'");
    }

    public String getName() {
        return name;
    }
}
