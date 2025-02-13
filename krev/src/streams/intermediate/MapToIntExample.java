package streams.intermediate;

import streams.Employee;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class MapToIntExample {
    public static void main(String[] args) {
        List<Employee> empList = List.of(
                new Employee(5, "name5", 50),
                new Employee(1, "name1", 10),
                new Employee(3, "name3", 30),
                new Employee(4, "name4", 40),
                new Employee(2, "name2", 20));

        //NOTE: since age is int, but we need Integer => use boxed() method
        List<Integer> ages = empList.stream().mapToInt(e -> e.getAge()).boxed().collect(Collectors.toList());

        // also there are methods mapToDouble etc

        //2. methods of mapToInt:
        int sum = empList.stream().mapToInt(e -> e.getAge()).sum();

        OptionalDouble optAvg = empList.stream().mapToInt(e -> e.getAge()).average();
        double avg = optAvg.getAsDouble();

        OptionalInt optMin = empList.stream().mapToInt(e -> e.getAge()).min();
        int min = optMin.getAsInt();
    }
}
