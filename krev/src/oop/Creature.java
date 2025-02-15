package oop;

public abstract class Creature {
    private String name;

    static {
        System.out.println("static block of " + Creature.class.getSimpleName());
    }

    static {
        System.out.println("non-static block of " + Creature.class.getSimpleName());
    }

//    Creature() {
//        System.out.println("default constructor of Creature abstract class");
//    }

    Creature(String name) {
//        this();
        System.out.println("default constructor of Creature abstract class");
    }

    protected abstract void voice();

    public String getName() {
        return name;
    }

    static void someStaticMethodInAbstractClass() {}


}
