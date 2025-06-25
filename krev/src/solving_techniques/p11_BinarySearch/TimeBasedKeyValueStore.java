package solving_techniques.p11_BinarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 981. Time Based Key-Value Store (medium)
 * https://leetcode.com/problems/time-based-key-value-store
 * <p>
 * #Company (25.06.2025): 0 - 3 months Axon 7 Amazon 5 Confluent 5 Apple 4 Coinbase 4 Cockroach Labs 4 Google 3 eBay 3 Lyft 3 Gusto 3 0 - 6 months Meta 3 Microsoft 3 Instacart 3 OpenAI 3 Oracle 2 TikTok 2 Anduril 2 Snowflake 2 6 months ago Uber 11 Databricks 10 Netflix 6 CARS24 5 Bloomberg 3 Airbnb 2 Flexport 2 Verkada 2 Nextdoor 2 CrowdStrike 2
 * <p>
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps
 * and retrieve the key's value at a certain timestamp.
 * <p>
 * Implement the TimeMap class:
 * <p>
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the value associated with the largest timestamp_prev.
 * If there are no values, it returns "".
 * <p>
 * Example 1:
 * Input
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 * <p>
 * Explanation
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
 * timeMap.get("foo", 1);         // return "bar"
 * timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
 * timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 * timeMap.get("foo", 4);         // return "bar2"
 * timeMap.get("foo", 5);         // return "bar2"
 * <p>
 * Constraints:
 * 1 <= key.length, value.length <= 100
 * key and value consist of lowercase English letters and digits.
 * 1 <= timestamp <= 10^7
 * All the timestamps timestamp of set are strictly increasing.
 * At most 2 * 10^5 calls will be made to set and get.
 */
public class TimeBasedKeyValueStore {
    /**
     * KREVSKY SOLUTION:
     * idea: hashMap : binary search
     * <p>
     * time to solve ~ 1h
     * a lot of problems with bounds in BS!
     * <p>
     * BEATS ~ 82%
     */
    private Map<String, List<Pair>> map;

    public TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }

    //time ~ O(1)
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Pair(value, timestamp));
    }

    /**
     * Implementation #2: better!
     * idea: save potential result of BS
     */
    public String get2(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        } else {
            List<Pair> list = map.get(key);
            //use binary search by timestamp
            int low = 0;
            int high = list.size() - 1;

            String result = "";

            while (low <= high) {
                int mid = low + (high - low)/2;
                if (list.get(mid).timestamp <= timestamp) {
                    result = list.get(mid).value;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return result;
        }
    }

    /**
     * Implementation #1
     */
    //time ~ O(logN). where N - length of list of Pairs
    public String get1(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        } else {
            List<Pair> list = map.get(key);
            //use binary search by timestamp
            int low = 0;
            int high = list.size() - 1;

            String result = "";

            while (low <= high) {
                int mid = low + (high - low) / 2;
                // if (list.get(mid).timestamp == timestamp) return list.get(mid).value;
                if (list.get(mid).timestamp <= timestamp) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            if (high < 0) return "";
            if (low >= list.size()) return list.get(list.size() - 1).value;

            //since high < low and we need to return result with timestamp <= given timestamp
            return list.get(high).value;
        }
    }

    class Pair {
        String value;
        int timestamp;

        Pair(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
}
