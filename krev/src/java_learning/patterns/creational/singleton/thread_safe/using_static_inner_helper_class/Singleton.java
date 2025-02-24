package java_learning.patterns.creational.singleton.thread_safe.using_static_inner_helper_class;

/**
 * Explanation:
 * The SingletonHelper class is loaded ONLY when the getInstance() method is called for the first time.
 * The class loader guarantees that the INSTANCE is created in a thread-safe manner.
 *
 * Pros:
 * Lazy initialization: The instance is created only when getInstance() is called.
 * Thread-safe: No explicit synchronization is required, as the class loader handles the thread safety.
 * Simple and efficient.
 *
 * Cons:
 * None significant. This is considered one of the best ways to implement the Singleton pattern in Java.
 */
public class Singleton {
    // Private constructor to prevent instantiation from outside
    private Singleton() {
        // Prevent instantiation
    }

    // Static inner helper class to hold the instance
    private static class SingletonHelper {
        private static final Singleton INSTANCE = new Singleton();
    }

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}