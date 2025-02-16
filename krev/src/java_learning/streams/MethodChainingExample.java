package java_learning.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MethodChainingExample {
    public static void main(String[] args) {
        //1.
        int[] arr = {3,5,3,7,3,9,1,10,56,34};
        //take only odd numbers
        // then - if filtered numbers are dividable by 3 => divide them.
        // then - calc sum of the numbers
        int res = Arrays.stream(arr).filter(e -> e % 2 == 1).map(e -> {
                if (e % 3 == 0) {
                    e /= 3;
                }
                return e;
        }).reduce(0, (sum, e) -> sum + e);

        //2.
        List<Employee> empList = List.of(
                new Employee(5, "name5", 50),
                new Employee(1, "name1", 10),
                new Employee(3, "name3", 30),
                new Employee(4, "name4", 40),
                new Employee(2, "name2", 20));

        empList.stream().map(e -> {
            String name = e.getName();
            String newName = name.substring(0,1).toUpperCase() + name.substring(1);
            e.setName(newName);
            return e;
        }).filter(e -> e.getAge() > 24).sorted((e1, e2) -> e1.getId() - e2.getId()).forEach(System.out::println);



    }
}
