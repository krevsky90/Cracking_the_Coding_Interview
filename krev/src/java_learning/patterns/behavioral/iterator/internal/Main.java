package java_learning.patterns.behavioral.iterator.internal;

public class Main {
    public static void main(String[] args) {
        Developer developer = new Developer("John");
        developer.addSkills("Java", "SQL", "Hibernate");

        MyIterator it = developer.getIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
