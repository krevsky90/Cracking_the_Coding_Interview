package java_learning.streams;

import java.util.List;

public class Initialization {
    public static List<Company> companies;

    static {
        Employee e1 = new Employee(1, "name1", 10);
        Employee e2 = new Employee(2, "name2", 20);
        Employee e3 = new Employee(3, "name3", 30);
        Employee e4 = new Employee(4, "name4", 40);
        Employee e5 = new Employee(5, "name5", 50);

        Company comp1 = new Company("comp1");
        comp1.addEmployee(e1);
        comp1.addEmployee(e2);

        Company comp2 = new Company("comp2");
        comp2.addEmployee(e3);
        comp2.addEmployee(e4);
        comp2.addEmployee(e5);

        companies = List.of(comp1, comp2);
    }

}
