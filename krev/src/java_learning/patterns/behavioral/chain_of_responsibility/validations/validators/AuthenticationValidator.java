package java_learning.patterns.behavioral.chain_of_responsibility.validations.validators;

import java_learning.patterns.behavioral.chain_of_responsibility.validations.Request;

import java.util.Map;

public class AuthenticationValidator extends Validator {
    private final Map<String, String> usernameToPassword;

    public AuthenticationValidator(Map<String, String> usernameToPassword) {
        this.usernameToPassword = usernameToPassword;
    }

    @Override
    public boolean check(Request request) {
        String username = request.getUser().getName();
        String password = request.getUser().getPassword();

        for (String name : usernameToPassword.keySet()) {
            if (username.equals(name) && password.equals(usernameToPassword.get(name))) {
                return checkNext(request);
            }
        }

        System.out.println("Authentication failed for user " + username);
        return false;
    }
}
