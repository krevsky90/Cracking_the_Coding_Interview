package live_coding.real_systems.rateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

public class Application {
    public static void main(String[] args) {
        int user1 = 0;
        int user2 = 1;
        int numberOfUsers = 2;

        UserBucketRepo userBucketRepo = UserBucketRepo.getInstance();

        List<Request> requestList1 = new ArrayList<>();
        requestList1.add(new Request(user1, 11, "r11"));
        requestList1.add(new Request(user1, 12, "r12"));
        requestList1.add(new Request(user1, 13, "r13"));
        requestList1.add(new Request(user1, 14, "r14"));
        requestList1.add(new Request(user1, 15, "r15"));

        List<Request> requestList2 = new ArrayList<>();
        requestList2.add(new Request(user2, 21, "r21"));
        requestList2.add(new Request(user2, 22, "r22"));
        requestList2.add(new Request(user2, 23, "r23"));
        requestList2.add(new Request(user2, 24, "r24"));
        requestList2.add(new Request(user2, 25, "r25"));
        requestList2.add(new Request(user2, 26, "r26"));
        requestList2.add(new Request(user2, 27, "r27"));

        ConcurrentHashMap<Integer, Request> requestsMap = new ConcurrentHashMap<>();
        RequestProcessor requestProcessor = new RequestProcessor(userBucketRepo, requestsMap);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfUsers + 1);
        //user 1:
        executorService.submit(() -> {
            for (Request request : requestList1) {
                if (userBucketRepo.addRequest(request.getUserId(), request.getRequestId())) {
                    requestsMap.put(request.getRequestId(), request);
                }
                try {
                    Thread.sleep((long) (new Random().nextDouble() * 1000));
                } catch (InterruptedException e) {
                    System.out.println("user1 is interrupted");
                }
            }
        });

        executorService.submit(() -> {
            for (Request request : requestList2) {
                if (userBucketRepo.addRequest(request.getUserId(), request.getRequestId())) {
                    requestsMap.put(request.getRequestId(), request);
                }
                try {
                    Thread.sleep((long) (new Random().nextDouble() * 300));
                } catch (InterruptedException e) {
                    System.out.println("user2 is interrupted");
                }
            }
        });

        executorService.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                requestProcessor.handle();
                //todo: why does this cause smth like lock?
//                System.out.println("isEmpty = " + userBucketRepo.getMap().get(1).isEmpty());

                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    System.out.println("Request processor thread is interrupted");
                }
            }
        });

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

//        LeakyBucket bucket0 = userBucketRepo.getMap().get(0);
//        if (bucket0.isEmpty()) System.out.println("bucket0 is empty");
//        LeakyBucket bucket1 = userBucketRepo.getMap().get(1);
//        if (bucket1.isEmpty()) System.out.println("bucket1 is empty");
        System.out.println("Total handled requests = " + requestProcessor.getHandledRequestsCounter());
    }
}
