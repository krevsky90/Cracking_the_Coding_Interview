package java_learning.patterns.behavioral.chain_of_responsibility.validations;

import java.util.Set;

public class User {
    private final String name;
    private final String password;
    private final Set<String> grants;

    public User(String name, String password, Set<String> grants) {
        this.name = name;
        this.password = password;
        this.grants = grants;

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getGrants() {
        return grants;
    }
}
