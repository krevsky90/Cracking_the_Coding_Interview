package java_learning.patterns.creational.singleton;

public class DatabaseSingleThread {
    private static DatabaseSingleThread INSTANCE;
    private String url;

    private DatabaseSingleThread(String url) {
        this.url = url;
    }

    public static DatabaseSingleThread getInstance(String url) {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseSingleThread(url);
        }
        return INSTANCE;
    }
}
