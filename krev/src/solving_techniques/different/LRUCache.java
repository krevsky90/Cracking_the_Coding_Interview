package solving_techniques.different;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU Cache (medium)
 * https://leetcode.com/problems/lru-cache
 * <p>
 * #Company: Yandex
 * <p>
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists.
 * Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 * <p>
 * Example 1:
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 * <p>
 * <p>
 * Constraints:
 * 1 <= capacity <= 3000
 * 0 <= key <= 10^4
 * 0 <= value <= 10^5
 * At most 2 * 10^5 calls will be made to get and put.
 */
public class LRUCache {
    public static void main(String[] args) {
        int capacity = 4;
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(capacity, 0.75f, true);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.get(1);
        map.get(2);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);

        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put1(2, 1); // cache is {1=1}
        lRUCache.put1(1, 1); // cache is {1=1, 2=2}
        lRUCache.put1(2, 3);
        lRUCache.put1(4, 1);
        System.out.println(lRUCache.get1(1));    // return 1
        System.out.println(lRUCache.get1(2));    // returns -1 (not found)
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * #1: use LinkedHashMap
     * #2: get the key the oldest element of LinkedHashMap buy map.keySet().iterator().next()
     * <p>
     * time to solve ~ 17 mins
     * <p>
     * several attempts due to stupid compile errors
     * <p>
     * BEATS ~ 76%
     */
    private LinkedHashMap<Integer, Integer> linkedHashMap;
//    private int maxCapacity;

//    public LRUCache(int capacity) {
//        maxCapacity = capacity;
//        boolean accessOrder = true;
//        linkedHashMap = new LinkedHashMap(capacity, 0.75f, accessOrder);
//    }

    public int get1(int key) {
        if (!linkedHashMap.containsKey(key)) return -1;

        return linkedHashMap.get(key);
    }

    public void put1(int key, int value) {
        linkedHashMap.put(key, value);
        if (linkedHashMap.size() > maxCapacity) {
            //remove the oldest element - i.e. 0-th element of LinkedHashMap.
            //but since LinkedHashMap does not have index => use iterator of its keySet and remove it.next() element
            int keyToRemove = linkedHashMap.keySet().iterator().next();
            linkedHashMap.remove(keyToRemove);
        }
    }

    /**
     * KREVSKY SOLUTION #2:
     * implement LRU cache without LinkedHashMap
     * <p>
     * time to solve ~ 60 mins
     * <p>
     * time ~ O(1)
     * space ~ O(1)
     * <p>
     * 5 attempts:
     * - moved touched node to head, but needed to tail
     * - forgot head = nextNode;
     * - forgot tail.next = node;
     * - did not use moveToTail(..) in get method
     *
     * BEATS ~ 79%
     */
    //like this https://leetcode.com/problems/lru-cache/solutions/5492972/easy-to-understand-double-link-list-with-map/

    class Node {
        Node prev;
        Node next;
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head = null;
    Node tail = null;

    Map<Integer, Node> map;
    int maxCapacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        maxCapacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            int valueToReturn = node.value;

            //move touched Node to tail (if it is not tail)
            moveToTail(node);

            return valueToReturn;
        } else {
            return -1;
        }
    }

    private void moveToTail(Node node) {
        if (node == tail) return;

        //link prev and next nodes to each other
        Node prevNode = node.prev;
        Node nextNode = node.next;
        if (prevNode != null) prevNode.next = nextNode;
        nextNode.prev = prevNode;

        //update head pointer if initially head = node
        //see example get(1): null < 1 <> 2 <> 3   -----> null < 2 <> 3 <> 1 > null
        if (head == node) {
            head = nextNode;
        }

        //make node as tail:
        //see example get(2): null < 1 <> 2 <> 3   -----> null < 1 <> 3 <> 2 > null
        node.prev = tail;
        node.next = null;
        tail.next = node;
        tail = node;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            //update if exists
            Node node = map.get(key);
            node.value = value;

            //move touched Node to tail (if it is not tail)
            moveToTail(node);
        } else {
            //otherwise insert to doubly-LL
            Node node = new Node(key, value);

            node.prev = tail;
            if (tail != null) tail.next = node;

            tail = node;

            if (head == null) {
                head = node;
            }

            //insert to map:
            map.put(key, node);

            //evict if necessary
            if (map.size() > maxCapacity) {
                //remove head from map:
                map.remove(head.key);

                //remove from LL:
                Node nextHead = head.next;
                nextHead.prev = null;

                head.next = null;   //optional

                head = nextHead;
            }
        }
    }
}


