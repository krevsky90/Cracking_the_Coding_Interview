package live_coding.real_systems.rateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestProcessor {
    private final UserBucketRepo userBucketRepo;
    private final ConcurrentHashMap<Integer, Request> requestsMap;
    private AtomicInteger handledRequestsCounter = new AtomicInteger(0);

    public RequestProcessor(UserBucketRepo userBucketRepo, ConcurrentHashMap<Integer, Request> requestsMap) {
        this.userBucketRepo = userBucketRepo;
        this.requestsMap = requestsMap;
    }

    public void handle() {
        if (userBucketRepo.getMap().isEmpty()) {
            System.out.println("Map is empty");
            return;
        }

        //get random user
        int userId = getRandomUser();
        if (userId == Integer.MIN_VALUE) {
            System.out.println("userId = MIN VALUE");
            return;
        }

        //take task from bucket's queue:
        Integer requestId = userBucketRepo.pollRequest(userId);

        if (requestId == null) {
            System.out.println("There are no requests for user = " + userId);
            return;
        }

        Request request = requestsMap.get(requestId);

        //handle
        System.out.println("Request " + requestId + " is handled for user = " + request.getUserId() + ": Content: " + request.getContent());
        handledRequestsCounter.incrementAndGet();
        System.out.println("============================");
    }

    private int getRandomUser() {
//        System.out.println("call getRandomUser()");
        int size = userBucketRepo.getMap().size();
        if (size == 0) return Integer.MIN_VALUE;

        //find non-empty buckets:
        List<Integer> usersWithTasks = new ArrayList<>();
        for (Map.Entry<Integer, LeakyBucket> entry : userBucketRepo.getMap().entrySet()) {
            if (!entry.getValue().isEmpty()) {
                usersWithTasks.add(entry.getKey());
//                System.out.println("getRandomUser # added user = " + entry.getKey());
            }
        }

        int r = new Random().nextInt(usersWithTasks.size());    //fixes Problem #1
        int i = 0;
        for (int userId : usersWithTasks) {
            if (i == r) {
//                System.out.println("getRandomUser # return user = " + userId);
                return userId;
            }
            i++;
        }


        throw new IllegalStateException("Something went wrong while picking a random element.");
    }

    public int getHandledRequestsCounter() {
        return handledRequestsCounter.intValue();
    }
}
