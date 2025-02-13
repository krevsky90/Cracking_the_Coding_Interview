package streams.terminal;

import streams.Employee;

import java.util.List;

public class MinMaxExample {
    public static void main(String[] args) {
        //min and max - terminal methods which returns Optional
        List<Employee> empList = List.of(
                new Employee(5, "name5", 50),
                new Employee(1, "name1", 10),
                new Employee(3, "name3", 30),
                new Employee(4, "name4", 40),
                new Employee(2, "name2", 20));

        Employee eMin = empList.stream().min((a,b) -> a.getAge() - b.getAge()).get();
        System.out.println(eMin);
    }
}
