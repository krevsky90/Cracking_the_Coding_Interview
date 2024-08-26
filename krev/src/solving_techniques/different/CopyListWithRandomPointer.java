package solving_techniques.different;

import java.util.HashMap;
import java.util.Map;

/**
 * 138. Copy List with Random Pointer (medium)
 * https://leetcode.com/problems/copy-list-with-random-pointer
 * <p>
 * #Company: Adobe Alibaba Amazon Apple Bloomberg Capital One eBay Expedia Facebook Google Mathworks Microsoft Oracle Qualtrics Uber Visa Yahoo
 * <p>
 * A linked list of length n is given such that each node contains an additional random pointer,
 * which could point to any node in the list, or null.
 * <p>
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
 * where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list
 * such that the pointers in the original list and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 * <p>
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * <p>
 * Return the head of the copied linked list.
 * <p>
 * The linked list is represented in the input/output as a list of n nodes.
 * Each node is represented as a pair of [val, random_index] where:
 * <p>
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
 * or null if it does not point to any node.
 * <p>
 * Your code will only be given the head of the original linked list.
 * <p>
 * Example 1:
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * Example 2:
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * <p>
 * Example 3:
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 * <p>
 * Constraints:
 * 0 <= n <= 1000
 * -10^4 <= Node.val <= 10^4
 * Node.random is null or is pointing to some node in the linked list.
 */
public class CopyListWithRandomPointer {
    /**
     * KREVSKY SOLUTION:
     * see https://leetcode.com/problems/copy-list-with-random-pointer/solutions/4003262/97-92-hash-table-linked-list/
     */

    /**
     * SOLUTION #2:
     * info:
     * https://www.youtube.com/watch?v=7Wch5jgbyZw&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=13
     * similar to KREVSKY idea, but more elegant,
     * since there is only one traversal and creation of Node immediately for next and ALSO for random pointers
     * idea:
     * 0) keep map old (original) node -> new (cloned) node
     * 1) traverse through the linked list and for each node
     * - check if the node is already cloned (by checking map). if does not exist - clone it
     * - check if the node = current.next is already cloned. if does not exist - clone it AND link to cloned current node by next pointer
     * - check if the node = current.random is already cloned. if does not exist - clone it AND link to cloned current node by random pointer
     * <p>
     * time to think similar idea ~ 10 mins
     * time to implement ~ 7 mins
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 100%
     */
    public Node copyRandomList(Node head) {
        Node current = head;
        Map<Node, Node> oldToNew = new HashMap<>();
        while (current != null) {
            Node currentClone = null;
            if (!oldToNew.containsKey(current)) {
                currentClone = new Node(current.val);
                oldToNew.put(current, currentClone);
            }
            currentClone = oldToNew.get(current);

            if (current.next != null) {
                Node currentNextClone = null;
                if (!oldToNew.containsKey(current.next)) {
                    currentNextClone = new Node(current.next.val);
                    oldToNew.put(current.next, currentNextClone);
                }
                currentNextClone = oldToNew.get(current.next);

                currentClone.next = currentNextClone;
            }

            if (current.random != null) {
                Node currentRandomClone = null;
                if (!oldToNew.containsKey(current.random)) {
                    currentRandomClone = new Node(current.random.val);
                    oldToNew.put(current.random, currentRandomClone);
                }
                currentRandomClone = oldToNew.get(current.random);

                currentClone.random = currentRandomClone;
            }

            current = current.next;
        }

        return oldToNew.get(head);
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}