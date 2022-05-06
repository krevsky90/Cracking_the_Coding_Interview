package data_structures.chapter2_linked_lists;

import java.util.Stack;

import static data_structures.chapter2_linked_lists.LinkedListUtils.linkedListToString;

/**
 * p.107
 * Palindrome: Implement a function to check if a linked list is a palindrome.
 * Hints: #5, #13, #29, #61, #101
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem2_6 {
    public static void main(String[] args) {
        LinkedListNode n1 = new LinkedListNode(3);
        LinkedListNode n2 = new LinkedListNode(5);
        LinkedListNode n3 = new LinkedListNode(7);
        LinkedListNode n4 = new LinkedListNode(5);
        LinkedListNode n5 = new LinkedListNode(3);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println(linkedListToString(n1));
        boolean isPalindrome = isPalindrome3(n1);
        System.out.println("is palindrome = " + isPalindrome);
    }

    /**
     * KREVSKY iterative approach = SOLUTION #1
     * Note that when we compare the linked list to the reversed list, we only actually need to compare the first
     * half of the list. If the first half of the normal list matches the first half of the reversed list, then the second half
     * of the normal list must match the second half of the reversed list.
     * NOTE: несмотря на то, что логично сравнивать лишь первые половины листов, в коде листы сравниваются полностью!
     *
     * минусы:
     * 1) приходится копировать весь лист - место O(n)
     * 2) приходится пробегать лист два раза
     *
     */
    public static boolean isPalindrome(LinkedListNode head) {
        if (head == null) {
            return false;
        }

        //clone and reverse linked list (O(n) - time, O(n) - space)
        LinkedListNode reverseHead = new LinkedListNode(head.value);
        LinkedListNode nextNode = head.next;
        LinkedListNode reverseTempNode = null;

        while (nextNode != null) {
            reverseTempNode = new LinkedListNode(nextNode.value);
            reverseTempNode.next = reverseHead;
            reverseHead = reverseTempNode;

            nextNode = nextNode.next;
        }

        //go through both lists and compare values of the elements (O(n) - time)
        LinkedListNode p1 = head;
        LinkedListNode p2 = reverseHead;
        while (p1 != null && p2 != null) {
            if (p1.value != p2.value) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    /**
     * If we don't know the size of the linked list, we can iterate through the linked list, using the fast runner / slow
     * runner technique described in the beginning of the chapter. At each step in the loop, we push the data from
     * the slow runner onto a stack. When the fast runner hits the end of the list, the slow runner will have reached
     * the middle of the linked list. By this point, the stack will have all the elements from the front of the linked
     * list, but in reverse order.
     * After that we simply iterate through the rest of the linked list. At each iteration, we compare the node to the top
     * of the stack. If we complete the iteration without finding a difference, then the linked list is a palindrome.
     */
    public static boolean isPalindromeUsingStack(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;

        Stack<Integer> stack = new Stack<>();
        /**
         * Push elements from first half of linked list onto stack. When fast runner
         * (which is moving at 2x speed) reaches the end of the linked list, then we
         * know we're at the middle
         */
        while (fast != null && fast.next != null) {
            stack.push(slow.value);
            slow = slow.next;
            fast = fast.next.next;
        }

        // Has odd number of elements, so skip the middle element
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            int top = stack.pop().intValue();
            if (top != slow.value) {
                return false;
            }
            slow = slow.next;
        }

        return true;
    }

    /**
     * SOLUTION #3:
     * each call compares its head to returned_node, and then passes returned_node. next
     * up the stack. In this way, every node i gets compared to node n - i. If at any point the values do not
     * match, we return false, and every call up the stack checks for that value.
     * But sometimes we'll return a boolean value, and sometimes we're returning a node.
     * For this purpose we create a simple class with two members, a boolean and a node, and return an instance of
     * that class.
     */
    public static boolean isPalindrome3(LinkedListNode head) {
        //get length
        int length = length(head);
        Result res = isPalindromeRecursive(head, length);

        return res.result;
    }

    private static Result isPalindromeRecursive(LinkedListNode head, int length) {
        if (head == null || length <= 0) {
            return new Result(head, true);
        } else if (length == 1) {   //odd number of nodes
            return new Result(head.next, true);
        }

        // recurse on sublist
        Result res = isPalindromeRecursive(head.next, length - 2);

        // If child calls are not a palindrome, pass back up a failure
        if (!res.result || res.node == null) {
            return res;
        }

        // Check if matches corresponding node on other side
        res.result = head.value == res.node.value;
        // Return corresponding node
        res.node = res.node.next;

        return res;
    }

    private static int length(LinkedListNode node) {
        int result = 0;
        while (node != null) {
            result++;
            node = node.next;
        }

        return result;
    }

    static class Result {
        public LinkedListNode node;
        public boolean result;

        public Result(LinkedListNode node, boolean result) {
            this.node = node;
            this.result = result;
        }
    }
}
