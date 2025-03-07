package java_learning.patterns.behavioral.chain_of_responsibility.validations;

public class Request {
    private final String message;
    private final User user;
    private final String action;

    public Request(String message, User user, String action) {
        this.message = message;
        this.user = user;
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public String getAction() {
        return action;
    }
}
