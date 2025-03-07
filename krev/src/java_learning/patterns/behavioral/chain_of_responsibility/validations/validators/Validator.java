package java_learning.patterns.behavioral.chain_of_responsibility.validations.validators;

import java_learning.patterns.behavioral.chain_of_responsibility.validations.Request;

/**
 * NOTE:
 * remove interface, put check method to abstract class
 *
 * here we also use this class to build chains right here and to implement comment parts of check method
 * ONLY detailed checking will be delegated to subclasses!
 */
public abstract class Validator {
    private Validator next;

    public static Validator link(Validator first, Validator... others) {
        Validator cur = first;
        for (Validator validator : others) {
            cur.next = validator;
            cur = validator;
        }
        return first;
    }

    public abstract boolean check(Request request);

    protected boolean checkNext(Request request) {
        //if current validation passed => give request to the next validator
        if (next != null) {
            return next.check(request);
        } else {
            return true;
        }
    }
}
