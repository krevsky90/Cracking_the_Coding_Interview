package live_coding.real_systems.rateLimiter;

public interface IRateLimiter {
    boolean grantAccess();

    Integer pollItem() throws InterruptedException;

    void addRequest(int requestId);

    boolean isEmpty();
}
