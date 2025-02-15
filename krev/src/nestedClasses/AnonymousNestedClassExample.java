package nestedClasses;

public class AnonymousNestedClassExample {
    private static int t = 2;

    static public void method() {
        Math2 m2 = new Math2() {
            @Override
            public int doOperation(int a, int b) {
                return a + b;
            }
        };

        Math2 m3 = new Math2() {
            @Override
            public int doOperation(int a, int b) {
                return a * b + t;
            }
        };

        //we can also override method of the other class when we create object of this class
        SomeClass sc = new SomeClass() {
            @Override
            public void print(String s) {
                System.out.println("overriden " + s);
            }
        };

        System.out.println(m2.doOperation(4, 9));
        System.out.println(m3.doOperation(4, 9));
        sc.print("test string");


    }

    public static void main(String[] args) {
        method();
    }
}

interface Math2 {
    int doOperation(int a, int b);
}

class SomeClass {
    public void print(String s) {
        System.out.println(s);
    }
}
