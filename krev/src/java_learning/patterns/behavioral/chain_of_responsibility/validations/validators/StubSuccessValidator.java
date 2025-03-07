package java_learning.patterns.behavioral.chain_of_responsibility.validations.validators;

import java_learning.patterns.behavioral.chain_of_responsibility.validations.Request;

public class StubSuccessValidator extends Validator {
    @Override
    public boolean check(Request request) {
        System.out.println("All validations are passed!");
        return true;
    }
}
