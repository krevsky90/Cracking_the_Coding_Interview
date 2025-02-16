package java_learning.nestedClasses;

public class LocalNestedClassExample {
    private String a = "a";

    public String getResult(int n1, int n2) {
        String k = "k";
        final int num = 100500;
        //NOTE: n1, n2, num should be final of effectively final
        class Concat implements Math {
            private String init = "init";

            public String concat() {
                return a + k + init + (n1 + n2) + num;
            }

            //NOTE: local class can also implement interfaces
            @Override
            public int doOperation(int a, int b) {
                return a + b;
            }
        }

        Concat concat = new Concat();
        String s = concat.concat();
        System.out.println(concat.doOperation(11, 11));
        return s;
    }

    public static void main(String[] args) {
        System.out.println(new LocalNestedClassExample().getResult(3, 50));
    }
}

interface Math {
    int doOperation(int a, int b);
}
