package java_learning.patterns.creational.builder.nested_final_params;

public class Main {
    public static void main(String[] args) {
        // Using the builder to create a Person object
        Person person = new Person.Builder("John", "Doe")
                .age(30)
                .email("john.doe@example.com")
                .build();

        System.out.println(person);
    }
}