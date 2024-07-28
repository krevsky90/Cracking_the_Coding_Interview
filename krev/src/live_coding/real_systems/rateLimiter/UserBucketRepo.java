package live_coding.real_systems.rateLimiter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static live_coding.real_systems.rateLimiter.LeakyBucket.MAX_BUCKET_CAPACITY;

/**
 * Business case:
 * for each User we will store separate bucket
 */
public class UserBucketRepo {
    private final Map<Integer, LeakyBucket> map = new HashMap<>();
    private static UserBucketRepo instance;

    private UserBucketRepo() {
    }

    public static UserBucketRepo getInstance() {
        if (instance == null) {
            synchronized (UserBucketRepo.class) {
                if (instance == null) {
                    instance = new UserBucketRepo();
                }
            }
        }
        return instance;
    }

    public LeakyBucket getBucketByUser(int userId) {
        if (map.containsKey(userId)) {
            return map.get(userId);
        } else {
            throw new IllegalArgumentException("there is not bucket for user = " + userId);
        }
    }

    public Integer pollRequest(int userId) {
        LeakyBucket bucket = getBucketByUser(userId);
        Integer requestId = bucket.pollItem();
        return requestId;   //might be null!
    }

    public synchronized boolean addRequest(int userId, int requestId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new LeakyBucket(MAX_BUCKET_CAPACITY));
        }

        LeakyBucket bucket = getBucketByUser(userId);
        if (!bucket.grantAccess()) {
            System.out.println(Thread.currentThread().getName() + ": [time = " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + "]: [user = " + userId + "]: Unable to add request = " + requestId);
            return false;
        }
        bucket.addRequest(requestId);
        System.out.println(Thread.currentThread().getName() + ": [time = " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + "]: [user = " + userId + "]: able to add request = " + requestId + " to the application");
        return true;
    }

    public Map<Integer, LeakyBucket> getMap() {
//        System.out.println("getMap is called");
        return map;
    }
}
