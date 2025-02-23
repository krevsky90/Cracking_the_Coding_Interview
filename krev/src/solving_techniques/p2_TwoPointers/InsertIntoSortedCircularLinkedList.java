package solving_techniques.p2_TwoPointers;

/**
 * 708. Insert into a Sorted Circular Linked List (medium) (locked)
 * https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list
 * <p>
 * #Company (23.02.2025): 0 - 3 months Meta 24 0 - 6 months Anduril 2 6 months ago Amazon 3 Google 2
 * <p>
 * Given a Circular Linked List node, which is sorted in non-descending order,
 * write a function to insert a value insertVal into the list such that it remains a sorted circular list.
 * The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.
 * <p>
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value.
 * After the insertion, the circular list should remain sorted.
 * <p>
 * If the list is empty (i.e., the given node is null), you should create a new single circular list
 * and return the reference to that single node. Otherwise, you should return the originally given node.
 * <p>
 * Example 1:
 * Input: head = [3,4,1], insertVal = 2
 * Output: [3,4,1,2]
 * Explanation: In the figure above, there is a sorted circular list of three elements.
 * You are given a reference to the node with value 3, and we need to insert 2 into the list.
 * The new node should be inserted between node 1 and node 3.
 * After the insertion, the list should look like this, and we should still return node 3.
 * <p>
 * Example 2:
 * Input: head = [], insertVal = 1
 * Output: [1]
 * Explanation: The list is empty (given head is null).
 * We create a new single circular list and return the reference to that single node.
 * <p>
 * Example 3:
 * Input: head = [1], insertVal = 0
 * Output: [1,0]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 5 * 10^4].
 * -10^6 <= Node.val, insertVal <= 10^6
 */
public class InsertIntoSortedCircularLinkedList {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 45 mins
     * <p>
     * idea:
     * 1) consider 2 cases:
     * - insert between cur and next when cur <= next
     * - insert between cur and next when cur > next
     * 2) check if all elements in the list are the same. if so - insert where you want
     * <p>
     * edge case: list is empty => create and return node
     * <p>
     * time ~ O(n)
     * space ~ (1)
     * <p>
     * 2 attempts:
     * - did not handle case when all elements are the same
     * <p>
     * BEATS ~ 100%
     */
    public Node insert(Node head, int v) {
        Node vNode = new Node(v);
        if (head == null) {
            vNode.next = vNode;
            return vNode;
        }

        Node cur = head;
        int theSame = -1;   //0 - no, 1 - yes
        while (true) {
            if (cur == head && theSame == 1) break;

            if (cur.val <= v && v <= cur.next.val) {
                vNode.next = cur.next;
                cur.next = vNode;
                return head;
            } else if (cur.val > cur.next.val && (cur.val <= v || cur.next.val >= v)) {
                vNode.next = cur.next;
                cur.next = vNode;
                return head;
            }

            if (theSame == -1 || theSame == 1) theSame = cur.val == cur.next.val ? 1 : 0;

            cur = cur.next;
        }

        if (theSame == 1) {
            //all elements are the same in the circular list - paste anywhere
            vNode.next = cur.next;
            cur.next = vNode;
        }

        return head;
    }

    /**
     * Alternative solution:
     * to avoid 'theSame' variable - use do while to traverse the whole list once
     * if we did not face any of 2 general cases => all elements are the same
     */
    public Node insertOfficial(Node head, int insertVal) {
        if (head == null) {
            Node newNode = new Node(insertVal, null);
            newNode.next = newNode;
            return newNode;
        }

        Node prev = head;
        Node curr = head.next;
        boolean toInsert = false;

        do {
            if (prev.val <= insertVal && insertVal <= curr.val) {
                // Case 1).
                toInsert = true;
            } else if (prev.val > curr.val) {
                // Case 2).
                if (insertVal >= prev.val || insertVal <= curr.val)
                    toInsert = true;
            }

            if (toInsert) {
                prev.next = new Node(insertVal, curr);
                return head;
            }

            prev = curr;
            curr = curr.next;
        } while (prev != head);

        // Case 3).
        prev.next = new Node(insertVal, curr);
        return head;
    }

    /**
     * OR https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/editorial/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days
     * we break if we have found the place where we can insert
     * if we did not find => all elements are the same => insert where we are at this moment
     */
    public Node insertFromComments(Node head, int insertVal) {
        if (head == null) {
            Node newNode = new Node(insertVal);
            newNode.next = newNode;
            return newNode;
        }
        Node node = head;

        while (node.next != head) {
            if (node.val <= node.next.val) {
                if (insertVal >= node.val && insertVal <= node.next.val) {
                    break;
                }
            } else {
                if (insertVal >= node.val || insertVal <= node.next.val) {
                    break;
                }
            }
            node = node.next;
        }

        Node next = node.next;
        node.next = new Node(insertVal, next);
        return head;
    }

    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

}
