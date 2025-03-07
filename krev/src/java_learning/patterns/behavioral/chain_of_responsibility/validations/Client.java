package java_learning.patterns.behavioral.chain_of_responsibility.validations;

import java_learning.patterns.behavioral.chain_of_responsibility.validations.validators.*;

import java.util.*;

public class Client {
    public static void main(String[] args) {
        User u1 = new User("name1", "pwd1", new HashSet<>(Arrays.asList("WRITE", "READ")));
        User u2 = new User("name2", "pwd2", new HashSet<>(Arrays.asList("READ")));
        User unknownUser = new User("name3", "12345", new HashSet<>());

        String shortMsg = "abc";
        String longMsg = "depgkfpowkgrewkfpke";

        Map<String, String> usernameToPassword = new HashMap<>();
        usernameToPassword.put("name1", "pwd1");
        usernameToPassword.put("name2", "pwd2");

        //build chain:
        //1) we can just create List<IValidator> and use for loop to check
        //2) use setNext method to build chain dynamically
        TooLongMessageValidator tooLongMessageValidator = new TooLongMessageValidator();
        AuthorizationValidator authorizationValidator = new AuthorizationValidator();
        AuthenticationValidator authenticationValidator = new AuthenticationValidator(usernameToPassword);
        StubSuccessValidator stubSuccessValidator = new StubSuccessValidator();

        Validator chain = Validator.link(
                authenticationValidator,
                authorizationValidator,
                tooLongMessageValidator,
                stubSuccessValidator);

        List<Request> requests = List.of(
                new Request("some message", unknownUser, "WRITE"),
                new Request("some message", u2, "WRITE"),
                new Request(longMsg, u1, "WRITE"),
                new Request(shortMsg, u1, "WRITE")
        );

        for (Request request : requests) {
            System.out.println("--- Start validation of temp request --- ");
            chain.check(request);
            System.out.println("--- End validation of temp request --- \n");
        }
    }
}
