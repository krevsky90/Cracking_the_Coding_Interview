package streams;

import java.util.ArrayList;
import java.util.List;

public class Company {
    public String name;
    public List<Employee> emps;

    public Company(String name) {
        this.name = name;
        emps = new ArrayList<>();
    }

    public void addEmployee(Employee e) {
        emps.add(e);
    }
}
