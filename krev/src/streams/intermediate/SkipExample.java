package streams.intermediate;

import streams.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class SkipExample {
    //skip - intermediate method
    //it skips the first K elements of our stream
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

        System.out.println("with skip = 2:");
        empList.stream().filter(e -> e.getAge() > 10).skip(2).forEach(System.out::println);

        //NOTE: if skip has negative parameter => it will throw IllegalArgumentException
        //if skip has parameter which > number of elements in the stream => returns no elements. In case if we convert stream to list, we will get empty list
        List<Employee> res = empList.stream().filter(e -> e.getAge() > 10).skip(7).collect(Collectors.toList());
        System.out.println("Result list in case when we skip all elements:");
        System.out.println(res);

    }
}
