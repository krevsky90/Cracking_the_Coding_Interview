package java_learning.streams.terminal.short_circuiting;

import java_learning.streams.Employee;

import java.util.List;

public class FindFirstExample {
    public static void main(String[] args) {
        List<Employee> empList = List.of(
                new Employee(5, "name5", 50),
                new Employee(1, "name1", 10),
                new Employee(3, "name3", 30),
                new Employee(4, "name4", 40),
                new Employee(2, "name2", 20));

        Employee ee = empList.stream().map(e -> {
            String name = e.getName();
            String newName = name.substring(0,1).toUpperCase() + name.substring(1);
            e.setName(newName);
            return e;
        }).filter(e -> e.getAge() > 24).sorted((e1, e2) -> e1.getId() - e2.getId()).findFirst().orElse(null);

        System.out.println(ee);
    }
}
