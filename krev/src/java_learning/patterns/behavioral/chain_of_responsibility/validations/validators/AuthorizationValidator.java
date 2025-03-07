package java_learning.patterns.behavioral.chain_of_responsibility.validations.validators;

import java_learning.patterns.behavioral.chain_of_responsibility.validations.Request;
import java_learning.patterns.behavioral.chain_of_responsibility.validations.User;

public class AuthorizationValidator extends Validator {
    @Override
    public boolean check(Request request) {
        User user = request.getUser();
        String action = request.getAction();
        boolean result = user.getGrants().contains(action);
        if (!result) {
            System.out.println("User " + user.getName() + " does not have grants for action " + action);
            return false;
        } else {
            return checkNext(request);
        }
    }
}
