package java_learning.patterns.behavioral.chain_of_responsibility.validations.validators;

import java_learning.patterns.behavioral.chain_of_responsibility.validations.Request;

public class TooLongMessageValidator extends Validator {
    private static final int MAX_MESSAGE_LENGTH = 10;

    @Override
    public boolean check(Request request) {
        String message = request.getMessage();
        boolean result = message.length() <= MAX_MESSAGE_LENGTH;
        if (!result) {
            System.out.println("The message " + message + " is too long!");
            return false;
        } else {
            return checkNext(request);
        }
    }
}
