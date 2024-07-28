package live_coding.real_systems.rateLimiter;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class LeakyBucket implements IRateLimiter {
    public static final int MAX_BUCKET_CAPACITY = 4;

    //idea: use BlockingDeque since it is thread-safe!
    private final BlockingDeque<Integer> queue;

    public LeakyBucket(int capacity) {
        this.queue = new LinkedBlockingDeque<>(capacity);
    }

    @Override
    public boolean grantAccess() {
        return MAX_BUCKET_CAPACITY - queue.size() > 0;
    }

    @Override
    public Integer pollItem() {
        //NOTE: if we don't set timeout, it will block the method that called pollItem FOREVER!
        try {
            return this.queue.pollFirst(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("Bucket is empty! Return null");
            return null;
        }
    }

    @Override
    public void addRequest(int requestId) {
        this.queue.add(requestId);
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
