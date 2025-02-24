package java_learning.patterns.creational.builder.nested_final_params;


/**
 * source: https://chat.qwenlm.ai/
 * question: how to implement builder class to create object with final fields on java
 *
 * Key Points:
 * Immutability : The Person class has final fields, making it immutable once constructed.
 * Optional Fields : The Builder class allows setting optional fields (age, email) while ensuring that required fields (firstName, lastName) are always provided.
 * Method Chaining : The builder methods return this, allowing for fluent method chaining.
 * Validation : You can add validation logic in the build() method to ensure that the object is constructed correctly.
 */
public class Person {
    // Final fields
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String email;  // Optional field

    // Private constructor to enforce object creation via the builder
    private Person(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.email = builder.email;
    }

    // Getters (since fields are final, no setters are needed)
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    // Static nested Builder class
    public static class Builder {
        // Required fields
        private final String firstName;
        private final String lastName;

        // Optional fields
        private int age = 0;  // Default value
        private String email = null;  // Default value

        // Constructor for required fields
        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        // Setter for optional field 'age'
        public Builder age(int age) {
            this.age = age;
            return this;  // Return the builder for method chaining
        }

        // Setter for optional field 'email'
        public Builder email(String email) {
            this.email = email;
            return this;  // Return the builder for method chaining
        }

        // Build method to create the Person object
        public Person build() {
            // You can add validation logic here if needed
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}