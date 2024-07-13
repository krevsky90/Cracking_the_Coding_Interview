package solving_techniques.p13_TopKElements;

import java.util.*;

/**
 * 355. Design Twitter
 * https://leetcode.com/problems/design-twitter/ (medium)
 *
 * Design a simplified version of Twitter where users can post tweets,
 * follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.
 *
 * Implement the Twitter class:
 * Twitter() Initializes your twitter object.
 * void postTweet(int userId, int tweetId)
 *      Composes a new tweet with ID tweetId by the user userId.
 *      Each call to this function will be made with a unique tweetId.
 * List<Integer> getNewsFeed(int userId)
 *      Retrieves the 10 most recent tweet IDs in the user's news feed.
 *      Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
 * void follow(int followerId, int followeeId)
 *      The user with ID followerId started following the user with ID followeeId.
 * void unfollow(int followerId, int followeeId)
 *      The user with ID followerId started unfollowing the user with ID followeeId.
 *
 * Example 1:
 * Input
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
 * Output
 * [null, null, [5], null, null, [6, 5], null, [5]]
 *
 * Explanation
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
 * twitter.follow(1, 2);    // User 1 follows user 2.
 * twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.unfollow(1, 2);  // User 1 unfollows user 2.
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
 *
 * Constraints:
 * 1 <= userId, followerId, followeeId <= 500
 * 0 <= tweetId <= 104
 * All the tweets have unique IDs.
 * At most 3 * 10^4 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
 */
public class DesignTwitter {
    /**
     * KREVSKY SOLUTION:
     * idea: see maps
     *
     * time to solve ~ 60 mins (about 20-25 mkins to debug)
     *
     * 5 attempts:
     * - NPE since userToFollowees.get(..) returns null
     * - NPE since userToItsTweets.get(userId) returns null
     * - forgot to sort resultPair by time
     * - MAIN mistake: set currentTimeMillis to tweets Map! it caused the same time for several tweets. Fixed: set size of tweets map
     *
     * BEATS ~ 95%
     */
    private Map<Integer, Integer> tweets = new HashMap<>();    //tweetId -> time
    private Map<Integer, Set<Integer>> userToFollowees = new HashMap<>();
    private Map<Integer, List<Integer>> userToItsTweets = new HashMap<>();

    public static void main(String[] args) {
        DesignTwitter designTwitter = new DesignTwitter();
        designTwitter.postTweet(1, 4);
        designTwitter.postTweet(2, 5);
        designTwitter.unfollow(1, 2);
        designTwitter.follow(1, 2);
        List<Integer> res = designTwitter.getNewsFeed(1);
        System.out.println();
    }

    public DesignTwitter() {
    }

    public void postTweet(int userId, int tweetId) {
        //1.
        if (!userToItsTweets.containsKey(userId)) {
            userToItsTweets.put(userId, new ArrayList<>());
        }
        userToItsTweets.get(userId).add(tweetId);

        //2.
        tweets.put(tweetId, tweets.size() + 1);
    }

    public List<Integer> getNewsFeed(int userId) {
        //latest tweets => 10 largest time should be in queue => smaller values should be excluded => min heap
        Queue<Pair> q = new PriorityQueue<>((a, b) -> (int) (a.time - b.time));

        Set<Integer> users = new HashSet<>();   //user + its all followees
        users.add(userId);
        if (userToFollowees.containsKey(userId)) {
            users.addAll(userToFollowees.get(userId));
        }


        for (Integer tempUserId : users) {
            List<Pair> usersTweets10 = get10TweetsByUserId(tempUserId);
            for (Pair pair : usersTweets10) {
                q.add(pair);
                if (q.size() > 10) {
                    q.poll();
                }
            }
        }

        List<Pair> resultPair = new ArrayList<>();
        while (!q.isEmpty()) {
            resultPair.add(q.poll());
        }

        //NOTE: we have 10 latest tweets, BUT q.poll() is the oldest tweet => we need sort the result by time
        Collections.sort(resultPair, (a,b) -> b.time - a.time);

        List<Integer> result = new ArrayList<>();
        for (Pair pair : resultPair) {
            result.add(pair.tweetId);
        }

        return result;
    }

    //List of "tweetId => time"
    private List<Pair> get10TweetsByUserId(int userId) {
        List<Pair> result = new ArrayList<>();
        List<Integer> allTweets = userToItsTweets.get(userId);
        if (allTweets == null) {
            return new ArrayList<>();
        }

        //collect tweets from the end while we get 10 tweets
        for (int i = allTweets.size() - 1; i >= 0 && i > allTweets.size() - 1 - 10; i--) {
            int tweetId = allTweets.get(i);
            int time = tweets.get(tweetId);
            result.add(new Pair(tweetId, time));
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {
        if (!userToFollowees.containsKey(followerId)) {
            userToFollowees.put(followerId, new HashSet<>());
        }
        userToFollowees.get(followerId).add(followeeId);
    }

    private class Pair {
        int tweetId;
        int time;

        public Pair(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if (userToFollowees.containsKey(followerId)) {
            userToFollowees.get(followerId).remove(followeeId);
        }
    }
}