package streams.intermediate;

import streams.Company;
import streams.Initialization;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ToMapExample {
    public static void main(String[] args) {
        List<Company> companies = Initialization.companies;

        System.out.println("get map: company name -> amount of emps in the company:");
        Map<String, Integer> companyToSize = companies.stream().collect(Collectors.toMap(company -> company.name, company -> company.emps.size()));
        System.out.println(companyToSize);

        System.out.println("get map: company name -> total age of emps in the company:");
        Map<String, Integer> companyToTotalAge = companies.stream().collect(Collectors.toMap(c -> c.name, c -> c.emps.stream().mapToInt(e -> e.getAge()).sum()));
        System.out.println(companyToTotalAge);


    }
}
