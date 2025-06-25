package solving_techniques.p31_OrderedSet;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 981. Time Based Key-Value Store (medium)
 * https://leetcode.com/problems/time-based-key-value-store
 *
 * #Company (25.06.2025): 0 - 3 months Axon 7 Amazon 5 Confluent 5 Apple 4 Coinbase 4 Cockroach Labs 4 Google 3 eBay 3 Lyft 3 Gusto 3 0 - 6 months Meta 3 Microsoft 3 Instacart 3 OpenAI 3 Oracle 2 TikTok 2 Anduril 2 Snowflake 2 6 months ago Uber 11 Databricks 10 Netflix 6 CARS24 5 Bloomberg 3 Airbnb 2 Flexport 2 Verkada 2 Nextdoor 2 CrowdStrike 2
 *
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps
 *      and retrieve the key's value at a certain timestamp.
 *
 * Implement the TimeMap class:
 *
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the value associated with the largest timestamp_prev.
 * If there are no values, it returns "".
 *
 * Example 1:
 * Input
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 *
 * Explanation
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
 * timeMap.get("foo", 1);         // return "bar"
 * timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
 * timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 * timeMap.get("foo", 4);         // return "bar2"
 * timeMap.get("foo", 5);         // return "bar2"
 *
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
     * idea: use map: key -> TreeMap, where TreeMap contains time -> value pairs
     * time to solve ~ 10 mins
     *
     * time ~ O(logN)
     *
     * 1 attempt
     *
     * BEATS ~ 50%
     */
    private Map<String, TreeMap<Integer, String>> map;

    public TimeBasedKeyValueStore() {
        this.map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (map.containsKey(key)) {
            Map.Entry<Integer, String> floorEntry = map.get(key).floorEntry(timestamp);
            if (floorEntry != null) {
                return floorEntry.getValue();
            }
        }

        return "";
    }
}
