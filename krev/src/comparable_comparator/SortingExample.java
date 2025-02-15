package comparable_comparator;

import streams.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingExample {
    public static void main(String[] args) {
        List<Employee> emps = Arrays.asList(
                new Employee(1, "n1", 10),
                new Employee(2, "n2", 20),
                new Employee(5, "n5", 50),
                new Employee(3, "n3", 30),
                new Employee(4, "n4", 40)
        );
        System.out.println("before sorting:");
        System.out.println(emps);
        Collections.sort(emps); //or Arrays.sort(emps)
        //NOTE: Arrays.sort will throw exception if emps does not implement Comaparable
        //whereas Collections.sort shows compile error
        System.out.println("after sorting:");
        System.out.println(emps);

        /**
         * Comparator's implementation overrides default implementation of compareTo method that is in Employee class!
         */
        Comparator<Employee> ageComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getAge() - o2.getAge();
            }
        };

        Collections.sort(emps, ageComparator);
        System.out.println("after sorting by age:");
        System.out.println(emps);

        //use lambda instead of comparator (sort in desc order
        Collections.sort(emps, (o1, o2) -> o2.getAge() - o1.getAge());
        System.out.println("after reversed sorting by age:");
        System.out.println(emps);



    }
}
