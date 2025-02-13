package streams.intermediate;

import streams.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortedExample {
    public static void main(String[] args) {
        int[] arr = {3,5,3,7,3,9,1,10,56,34};
        int[] arr2 = Arrays.stream(arr).sorted().toArray();
        System.out.println(Arrays.toString(arr2));

        List<Employee> empList = List.of(
                new Employee(5, "name5", 50),
                new Employee(1, "name1", 10),
                new Employee(3, "name3", 30),
                new Employee(4, "name4", 40),
                new Employee(2, "name2", 20));

        List<Employee> resList = empList.stream().sorted((a,b) -> a.getAge() - b.getAge()).collect(Collectors.toList());
        System.out.println(resList);
    }
}
