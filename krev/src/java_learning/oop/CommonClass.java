package java_learning.oop;

public class CommonClass {
    //static field
    //can NOT be [private/protected/default/public] or [final]
    static int staticA = 5;

    //static block
    //can NOT be [private/protected/default/public] or [final]
    static {
        int staticB = staticA + 3;
        System.out.println("static block of " + CommonClass.class.getClass().getName());
    }

    //non-static field
    //can be [private/protected/default/public] or [final]
    int a = 4;

    //non-static block
    //can be [private/protected/default/public] or [final]
    {
        System.out.println("block of " + CommonClass.class.getClass().getName());
        int b = a + 3;
    }

    //constructors
    //can be [private/protected/default/public]
    //can NOT be static or final or asbtract
    public CommonClass(int a) {
        this();
        this.a = a;

    }

    private CommonClass() {

    }

    //static-method
    //can NOT be [private/protected/default/public] or [final]
    static void staticMethod() {
        System.out.println( CommonClass.class.getClass().getName() + " # staticMethod");
    }

    //static method can call the other static method
    static void staticMethod2() {
        staticMethod();
        System.out.println( CommonClass.class.getClass().getName() + " # staticMethod");
    }

    //NOTE: static methods/blocks can NOT use non-static methods/fields!

    //non-static methods
    //can be [private/protected/default/public] and [final]
    private void privateMethod() {
        System.out.println(getClass().getName() + " # privateMethod");
    }

    protected void protectedMethod() {
        System.out.println(getClass().getName() + " # protectedMethod");
    }

    void defaultMethod() {
        System.out.println(getClass().getName() + " # defaultMethod");
    }

    public void publicMethod() {
        System.out.println(getClass().getName() + " # publicMethod");
    }

    //non-static methods can call static methods
    public void publicMethod2() {
        staticMethod();
        System.out.println(getClass().getName() + " # publicMethod2");
    }




}
