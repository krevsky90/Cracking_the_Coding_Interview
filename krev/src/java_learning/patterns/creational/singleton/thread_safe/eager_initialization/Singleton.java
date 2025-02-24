package java_learning.patterns.creational.singleton.thread_safe.eager_initialization;

/**
 * Pros:
 * Simple and straightforward.
 * Thread-safe without requiring additional synchronization.
 *
 * Cons:
 * The instance is created even if it is not used, which may lead to unnecessary memory usage if the instance is never accessed.
 */
public class Singleton {
    // The instance is created at class loading time
    private static final Singleton INSTANCE = new Singleton();

    // Private constructor to prevent instantiation from outside
    private Singleton() {
        // Prevent instantiation
    }

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        return INSTANCE;
    }
}