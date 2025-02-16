package java_learning;

public interface TestInterface {
    default void test() {
        System.out.println("test default method of java_learning.TestInterface");
    }

//    default boolean equals(Object o) {
//
//    }
}
