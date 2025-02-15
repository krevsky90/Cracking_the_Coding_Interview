package streams;

import java.util.ArrayList;
import java.util.List;

public class Company implements Comparable<Company> {
    public int id;
    public String name;
    public List<Employee> emps;

    public Company(String name) {
        this.name = name;
        emps = new ArrayList<>();
    }

    public void addEmployee(Employee e) {
        emps.add(e);
    }

    @Override
    public int compareTo(Company o) {
        return this.id - o.id;
    }

    @Override
    public String toString() {
        return "company: [id = " + id + ", name = " + name + ", emps = {" + emps + "}]";
    }
}
