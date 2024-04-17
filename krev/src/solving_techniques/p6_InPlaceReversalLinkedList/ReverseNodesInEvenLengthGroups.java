package solving_techniques.p6_InPlaceReversalLinkedList;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * 2074. Reverse Nodes in Even Length Groups
 * https://leetcode.com/problems/reverse-nodes-in-even-length-groups
 *
 * You are given the head of a linked list.
 *
 * The nodes in the linked list are sequentially assigned to non-empty groups whose lengths form the sequence of the natural numbers (1, 2, 3, 4, ...).
 * The length of a group is the number of nodes assigned to it. In other words,
 *
 * The 1st node is assigned to the first group.
 * The 2nd and the 3rd nodes are assigned to the second group.
 * The 4th, 5th, and 6th nodes are assigned to the third group, and so on.
 * Note that the length of the last group may be less than or equal to 1 + the length of the second to last group.
 *
 * Reverse the nodes in each group with an even length, and return the head of the modified linked list.
 *
 * Example 1:
 * Input: head = [5,2,6,3,9,1,7,3,8,4]
 * Output: [5,6,2,3,9,1,4,8,3,7]
 * Explanation:
 * - The length of the first group is 1, which is odd, hence no reversal occurs.
 * - The length of the second group is 2, which is even, hence the nodes are reversed.
 * - The length of the third group is 3, which is odd, hence no reversal occurs.
 * - The length of the last group is 4, which is even, hence the nodes are reversed.
 *
 * Example 2:
 * Input: head = [1,1,0,6]
 * Output: [1,0,1,6]
 * Explanation:
 * - The length of the first group is 1. No reversal occurs.
 * - The length of the second group is 2. The nodes are reversed.
 * - The length of the last group is 1. No reversal occurs.
 *
 * Example 3:
 * Input: head = [1,1,0,6,5]
 * Output: [1,0,1,5,6]
 * Explanation:
 * - The length of the first group is 1. No reversal occurs.
 * - The length of the second group is 2. The nodes are reversed.
 * - The length of the last group is 2. The nodes are reversed.
 *
 *
 * Constraints:
 * The number of nodes in the list is in the range [1, 10^5].
 * 0 <= Node.val <= 10^5
 */
public class ReverseNodesInEvenLengthGroups {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 40 mins
     * idea: https://leetcode.com/problems/reverse-nodes-in-even-length-groups/solutions/4721003/solution-with-detail-explanation-faang-interview-question/
     * 1) for each group count its real length. Then decide whether we need to reverse or not, looking ONLY count value (but not expectedLength!)
     * 2) to link reversed group we need to store the pointers to
     *      the last element of the previous group: lastElPrevGroup
     *      the first element of the next group: current
     *      the last element of current group (that initially is the first): firstElCurGroup
     *      the first element of current group (that initially is the last): local variable prev of 'reverse' method
     * 3) to be ready to the case when count % 2 = 1, we need to track 'prev' pointer during each traversing from 1 to expectedLength.
     *      It helps to update firstElCurGroup and lastElPrevGroup before checking the next group
     * 4) do not forget to decrement 'count' after traversing, since "<=" makes 'count' bigger, than the size of considered group
     *
     * time ~ O(n)
     * space ~ O(1)
     *
     * 1 attempt
     *
     * BEATS = 95%
     */
    public LinkedListNode reverseEvenLengthGroups(LinkedListNode head) {
        int expectedLength = 2;
        LinkedListNode current = head.next;
        LinkedListNode prev = head;
        LinkedListNode lastElPrevGroup = head;
        LinkedListNode firstElCurGroup = head.next;

        while (current != null) {
            int count = 1;
            while (current != null && count <= expectedLength) {
                prev = current;
                current = current.next;
                count++;
            }
            count--;    //since <= condition above

            //reverse of not looking at count (not expectedLength)
            if (count % 2 == 0) {
                lastElPrevGroup.next = reverse(firstElCurGroup, count);
                firstElCurGroup.next = current;

                //reset lastElPrevGroup and firstElCurGroup
                lastElPrevGroup = firstElCurGroup;
                firstElCurGroup = current;
            } else {
                //reset lastElPrevGroup and firstElCurGroup
                lastElPrevGroup = prev;
                firstElCurGroup = current;
            }
            expectedLength++;
        }

        return head;
    }

    private LinkedListNode reverse(LinkedListNode head, int count) {
        LinkedListNode prev = null;
        LinkedListNode current = head;
        LinkedListNode next = null;

        int i = 0;
        while (current != null && i < count) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            i++;
        }

        return prev;
    }
}