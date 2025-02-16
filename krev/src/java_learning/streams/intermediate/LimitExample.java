package java_learning.streams.intermediate;

import java_learning.streams.Employee;

import java.util.List;

public class LimitExample {
    public static void main(String[] args) {
        //limit - intermediate method
        List<Employee> empList = List.of(
                new Employee(5, "name5", 50),
                new Employee(1, "name1", 10),
                new Employee(3, "name3", 30),
                new Employee(4, "name4", 40),
                new Employee(2, "name2", 20));

        //without limit
        System.out.println("without limit:");
        empList.stream().filter(e -> e.getAge() > 10).forEach(System.out::println);

        System.out.println("with limit = 2:");
        empList.stream().filter(e -> e.getAge() > 10).limit(2).forEach(System.out::println);
    }
}
