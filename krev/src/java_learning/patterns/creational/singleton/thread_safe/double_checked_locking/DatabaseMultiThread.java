package java_learning.patterns.creational.singleton.thread_safe.double_checked_locking;

/**
 * Pros:
 * Lazy initialization: The instance is created only when it is needed.
 * Thread-safe: Double-checked locking ensures that only one instance is created even in a multithreaded environment.
 *
 * Cons:
 * Slightly more complex due to the double-checked locking mechanism.
 * Performance overhead due to synchronization (though minimal after the instance is created).
 */
public class DatabaseMultiThread {
    private static volatile DatabaseMultiThread INSTANCE;
    private String url;

    private DatabaseMultiThread(String url) {
        this.url = url;
    }

    // Lazy Initialization with Double-Checked Locking
    public static DatabaseMultiThread getInstance(String url) {
        if (INSTANCE == null) {
            synchronized (DatabaseMultiThread.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseMultiThread(url);
                }
            }
        }
        return INSTANCE;
    }
}
