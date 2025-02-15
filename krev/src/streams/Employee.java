package streams;

import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private int age;
    private String company;

    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Employee(int id, String name, int age, String company) {
        this(id, name, age);
        this.company = company;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof Employee))) return false;
        Employee e = (Employee) o;
        return this.id == e.id && Objects.equals(this.name, e.name) && this.age == e.age && Objects.equals(this.company, company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.age, this.company);
    }

    @Override
    public String toString() {
        return "[id = " + id + ", name = " + name + ", age = " + age + ", company = " + company + "]";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public int compareTo(Employee o) {
//      return name.compareTo(o.getName()); - just as example when we use compareTo method of String class which implements Comparable interface
        return this.id - o.id;

    }
}
