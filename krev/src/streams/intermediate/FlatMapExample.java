package streams.intermediate;

import streams.Company;
import streams.Employee;
import streams.Initialization;

import java.util.*;

public class FlatMapExample {
    public static void main(String[] args) {
        // flatMap - intermediate method
        // works with elements of elements of our collection
        List<Company> companies = Initialization.companies;

        companies.stream().flatMap(company -> company.emps.stream()).forEach(e -> System.out.println(e.getName()));

        //2. calc total sum of ages of emps for all companies:
        int totalAge = companies.stream().flatMap(c -> c.emps.stream()).map(ee -> ee.getAge()).reduce(0, (sum, age) -> sum + age);
        //or
        totalAge = companies.stream().flatMap(c -> c.emps.stream()).mapToInt(Employee::getAge).sum();
        System.out.println(totalAge);

        //3. calc avg age of emps in company with name = comp2
        double avgAgeOpt = companies.stream().filter(c -> c.name.equals("comp2")).flatMap(c -> c.emps.stream()).mapToInt(Employee::getAge).average().orElse(0);
        System.out.println("avgAgeOpt = " + avgAgeOpt);



    }
}
