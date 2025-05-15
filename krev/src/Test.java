import java.util.*;

public class Test {



    private static void test1_stackOperations() {
        Stack<Integer> s = new Stack<>();
        s.addAll(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("");
        s.remove(2);
        System.out.println("");
    }

    private static void test2_convertListToArray() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        Integer[] arr = new Integer[list.size()];
        list.toArray(arr);
        Arrays.stream(arr).forEach(a -> System.out.print(a + " "));
        //or
        System.out.println("\n--");
        Integer[] arr2 = list.toArray(new Integer[0]);
        Arrays.stream(arr2).forEach(a -> System.out.print(a + " "));
    }

    private static void test3_Enums() {
        KrevTypes t1enum = KrevTypes.valueOf("T1");
        String t1val = KrevTypes.T1.getType();
    }

    /**
     * see https://javarush.com/en/groups/posts/en.1963.enum-in-java-how-to-use-a-class
     */
    enum KrevTypes {
        T1("type1"),
        T2("type2");

        private String type;

        KrevTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }
}
