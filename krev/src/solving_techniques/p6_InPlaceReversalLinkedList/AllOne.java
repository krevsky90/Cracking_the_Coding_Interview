package solving_techniques.p6_InPlaceReversalLinkedList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 432. All O`one Data Structure (hard)
 * https://leetcode.com/problems/all-oone-data-structure
 *
 * #Company: 0 - 3 months LinkedIn 12 Google 8 Meta 3 Microsoft 2 Amazon 2 Bloomberg 2 Atlassian 2 0 - 6 months Uber 4
 *
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 *
 * Implement the AllOne class:
 *
 * AllOne() Initializes the object of the data structure.
 * inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 * dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 * getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 *
 *  Example 1:
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 *
 * Explanation
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "leet"
 *
 * Constraints:
 * 1 <= key.length <= 10
 * key consists of lowercase English letters.
 * It is guaranteed that for each call to dec, key is existing in the data structure.
 * At most 5 * 10^4 calls will be made to inc, dec, getMaxKey, and getMinKey.
 */
public class AllOne {
    /**
     * NOT SOLVED by myself
     * idea: https://www.youtube.com/watch?v=rtIVvQ1z8jY&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=137
     * extra idea (not implemented):
     *      keep dummy head = new Node(0), tail = new Node(0), and min = head.next, max = tail.prev
     *
     * 1) keep map: word -> Node
     * 2) keep Doubly-Linked List of Nodes,
     *      where Node contains:
     *          frequency of words, set of words, prev ptr, next ptr
     *
     * time to implement ~ 50 mins
     *
     * time ~ O(1)
     * space ~ O(n) - n - number of words
     *
     * 2 attempts:
     * - forgot to update "map.put(key, node1);"
     *
     * BEATS ~ 90+%
     */
    Node head;  //for min
    Node tail;  //for max
    Map<String, Node> map;

    public AllOne() {
        map = new HashMap<>();
    }

    public void inc(String key) {
        if (map.containsKey(key)) {
            Node startNode = map.get(key);
            //find or create newNode
            Node nextNode = startNode.next;
            Node newNode = nextNode;
            if (nextNode == null || nextNode.freq != startNode.freq + 1) {
                newNode = insertNode(startNode.freq + 1, startNode, nextNode);
            }
            //move word
            newNode.words.add(key);
            startNode.words.remove(key);
            //update map
            map.put(key, newNode);
            //remove startNode if does not contain any words
            if (startNode.words.isEmpty()) {
                removeNode(startNode);
            }
        } else {
            Node node1 = head;  //suppose head has freq = 1
            if (head == null || head.freq > 1) {
                node1 = insertNode(1, null, head);
            }
            //set word
            node1.words.add(key);
            //update map
            map.put(key, node1);
        }
    }

    public void dec(String key) {
        Node startNode = map.get(key);
        if (startNode.freq == 1) {
            //there is no necessity to create new prevNode with freq = 0

            //move word
            startNode.words.remove(key);
            //update map
            map.remove(key);
            //remove startNode if does not contain any words
            if (startNode.words.isEmpty()) {
                removeNode(startNode);
            }
        } else {
            //find or create prev node
            Node prevNode = startNode.prev;
            Node newNode = prevNode;
            if (prevNode == null || prevNode.freq != startNode.freq - 1) {
                newNode = insertNode(startNode.freq - 1, prevNode, startNode);
            }
            //move word
            newNode.words.add(key);
            startNode.words.remove(key);
            //update map
            map.put(key, newNode);
            //remove startNode if does not contain any words
            if (startNode.words.isEmpty()) {
                removeNode(startNode);
            }
        }
    }

    public String getMaxKey() {
        return tail == null ? "" : tail.words.iterator().next();
    }

    public String getMinKey() {
        return head == null ? "" : head.words.iterator().next();
    }

    private Node insertNode(int freq, Node prev, Node next) {
        Node node = new Node(freq, prev, next);
        if (prev != null) prev.next = node;
        if (next != null) next.prev = node;

        if (prev == null) {
            //it means head = prev => move head
            head = node;
        }

        if (next == null) {
            //it means tail = next => move tail
            tail = node;
        }

        return node;
    }

    private void removeNode(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev;
        if (nextNode != null) nextNode.prev = prevNode;
        if (prevNode != null) prevNode.next = nextNode;

        if (head == node) {
            head = nextNode;
        }

        if (tail == node) {
            tail = prevNode;
        }
    }
}

class Node {
    int freq;
    Set<String> words = new HashSet<>();
    Node prev;
    Node next;

    Node(int freq, Node prev, Node next) {
        this.freq = freq;
        this.prev = prev;
        this.next = next;
    }
}
