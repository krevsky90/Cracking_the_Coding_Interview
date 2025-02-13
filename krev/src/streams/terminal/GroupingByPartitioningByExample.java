package streams.terminal;

import streams.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupingByPartitioningByExample {
    public static void main(String[] args) {
        Employee e1 = new Employee(1, "name1", 10, "comp1");
        Employee e2 = new Employee(2, "name2", 20, "comp1");
        Employee e3 = new Employee(3, "name3", 30, "comp2");
        Employee e4 = new Employee(4, "name4", 40, "comp2");
        Employee e5 = new Employee(5, "name5", 50, "comp2");

        List<Employee> emps = List.of(e1, e2, e3, e4, e5);//, employeeWithoutCompany);

        //1. example of grouping
        Map<String, List<Employee>> companyToEmployees = emps.stream().map(e -> {
            e.setName(e.getName().toUpperCase());
            return e;
        }).collect(Collectors.groupingBy(e -> e.getCompany()));

        //print
        companyToEmployees.entrySet().stream().forEach(e -> System.out.println(
                e.getKey() + " -> " + e.getValue().toString())
        );

        //NOTE: if employee does NOT have company => collect method will throw exception:
        // java.lang.NullPointerException: element cannot be mapped to a null key
        Employee employeeWithoutCompany = new Employee(6, "name6", 60); //no company!
        List<Employee> emps2 = List.of(e1, e2, e3, e4, e5, employeeWithoutCompany);

        try {
            emps2.stream().map(e -> {
                e.setName(e.getName().toUpperCase());
                return e;
            }).collect(Collectors.groupingBy(e -> e.getCompany()));
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException: element cannot be mapped to a null key");
        }

        //1.2: get Map<String, List<name of employee>>
        //to obtain name of employee we use method Collectors.mapping("what we need to extract", "which collection we use to store the extracted data")
        List<Employee> emps3 = List.of(e1, e2, e3, e4, e5);
        Map<String, List<String>> companyToEmpNamesMap = emps3.stream()
                .collect(Collectors.groupingBy(
                                e -> e.getCompany(),
                                Collectors.mapping(e -> e.getName(), Collectors.toList())
                        )
                );

        //1.3. get the oldest emp in each company:
        Map<String, Optional<Employee>> companyToOldestEmpMap = emps3.stream()
                .collect(
                        Collectors.groupingBy(
                                e -> e.getCompany(),
                                Collectors.maxBy(Comparator.comparingInt(Employee::getAge)))
                );

        //2. example of partitioning by (Predicate) -> map will contain only 2 groups, key is boolean
        Map<Boolean, List<Employee>> mapPartitioning = emps.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25));

        //print
        mapPartitioning.entrySet().stream().forEach(e -> System.out.println(
                e.getKey() + " -> " + e.getValue().toString())
        );


    }
}
