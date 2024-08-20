package solving_techniques.different;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * 2. Add Two Numbers (medium)
 * https://leetcode.com/problems/add-two-numbers
 *
 * #Company: Adobe Aetion Airbnb Alibaba Amazon Apple Baidu Bloomberg ByteDance Capital One Cisco Facebook Flipkart GoDaddy Google Grab Huawei IBM Intel Lyft Mathworks Microsoft Nvidia Oracle Paypal Qualcomm Redfin SAP ServiceNow Tencent Uber VMware Wish Yahoo Yandex Zoho
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * <p>
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * <p>
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * <p>
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbers {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 12 mins
     * time ~ O(n)
     * space ~ O(n)
     * where n - max length of linked lists
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 100%
     */
    public LinkedListNode addTwoNumbers(LinkedListNode l1, LinkedListNode l2) {
        LinkedListNode fakeHead = new LinkedListNode();
        LinkedListNode prev = new LinkedListNode();
        fakeHead = prev;

        int additional = 0;
        while (l1 != null || l2 != null || additional > 0) {
            int val = additional;
            if (l1 != null) {
                val += l1.value;
                l1 = l1.next;
            }

            if (l2 != null) {
                val += l2.value;
                l2 = l2.next;
            }

            additional = val / 10;
            val = val % 10;

            LinkedListNode tempNode = new LinkedListNode(val);
            //link to the result
            prev.next = tempNode;
            prev = prev.next;
        }

        return fakeHead.next;
    }
}
