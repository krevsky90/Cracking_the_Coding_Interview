package solving_techniques.p14_KwayMerge;

import data_structures.chapter2_linked_lists.LinkedListNode;
import data_structures.chapter2_linked_lists.LinkedListUtils;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a348bd7bde61668d15d011
 * OR
 * 23. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists (hard)
 */
public class MergeKSortedLists {
    public static void main(String[] args) {
        LinkedListNode n11 = new LinkedListNode(1);
        LinkedListNode n12 = new LinkedListNode(4);
        LinkedListNode n13 = new LinkedListNode(5);
        n11.next = n12;
        n12.next = n13;

        LinkedListNode n21 = new LinkedListNode(1);
        LinkedListNode n22 = new LinkedListNode(3);
        LinkedListNode n23 = new LinkedListNode(4);
        n21.next = n22;
        n22.next = n23;

        LinkedListNode n31 = new LinkedListNode(2);
        LinkedListNode n32 = new LinkedListNode(6);
        n31.next = n32;

        LinkedListNode[] lists = new LinkedListNode[3];
        lists[0] = n11;
        lists[1] = n21;
        lists[2] = n31;

        LinkedListNode result = mergeKLists2(lists);
        LinkedListUtils.printLinkedList(result);

    }

    /**
     * KREVSKY SOLUTION:
     * idea: described in readme
     * +idea #1: stop condition = queue is Empty
     * +idea #2: use ArrayList/array (to store curNodes, to have ability to set changed current element by index. it is really necessary! I lost 40 mins without it!
     * <p>
     * time to solve ~ 40 mins coding + 40 mins debugging due to missed idea #2 = 80 mins
     * <p>
     * not optimized: in the end of the algorithm curNodes collection contains only null's => it would be good to cut them,
     * but without usage of  linkedList (I tried it firstly, until came to the idea #2) I do not know how to do this in O(1)
     * <p>
     *
     * 2 attempts:
     * - missed the idea #2
     */
    public static LinkedListNode mergeKLists(LinkedListNode[] lists) {
        Queue<LinkedListNode> pq = new PriorityQueue<>((a, b) -> a.value - b.value); //i.e. min heap

        //do not want to change the initial array lists.
        //will create its copy to use it as list of current nodes.
        //moreover it will be simplier to remove empty lists
        List<LinkedListNode> curNodes = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                curNodes.add(lists[i]);
            }
        }

        for (LinkedListNode tempCur : curNodes) {
            pq.add(tempCur);
        }

        LinkedListNode result = null;
        LinkedListNode tempEnd = null;

        while (!pq.isEmpty()) {
            LinkedListNode tempMin = pq.poll();

            //store found minimum to the result chain
            if (result == null) {
                result = tempMin;
                tempEnd = tempMin;
            } else {
                tempEnd.next = tempMin;
                tempEnd = tempMin;
            }

            //find the list that contains tempMin as current node
            //ATTENTION!!! this is NOT needed! we just should get tempMin.next (that is obviously in the same list as tempMin node)
            //  and add it to the queue (it the next element is not null)
            for (int i = 0; i < curNodes.size(); i++) {
                //we can compare the objects since we put the object itself to the PriorityQueue
                LinkedListNode tempCur = curNodes.get(i);
                if (tempCur == tempMin) {
                    tempCur = tempCur.next;
                    curNodes.set(i, tempCur);   //need to set it. otherwise curNodes collection will not be changed!
                    if (tempCur != null) {
                        pq.add(tempCur);
                    }
                    break;
                }
            }
        }

        return result;
    }

    /**
     * GFG solution - optimized Krevsky's solution
     *
     * NOTE: hack to avoid if-else while storing the link to 'result' node -  create fake head + temp pointer. and finally return head.next
     * time ~ O(N*K*logK)
     * space ~ O(K), where k - amount of lists, N - total amount of elements (in all lists)
     */
    public static LinkedListNode mergeKListsGFG(LinkedListNode[] lists) {
        // Priority_queue 'queue' implemented as min heap with the help of 'compare' function
        Queue<LinkedListNode> pq = new PriorityQueue<>((a, b) -> a.value - b.value); //i.e. min heap

        // Push the head nodes of all the k lists in 'queue'
        for (LinkedListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        // Handles the case when k = 0 or lists have no elements in them
        if (pq.isEmpty()) {
            return null;
        }

        LinkedListNode fakeHead = new LinkedListNode(-100500);
        LinkedListNode last = fakeHead;

        while (!pq.isEmpty()) {
            // Get the top element of 'queue'
            LinkedListNode tempMin = pq.poll();

            // Add the top element of 'queue' to the resultant merged list
            last.next = tempMin;
            last = last.next;

            // Check if there is a node next to the 'top' node in the list of which 'top' node is a member
            if (tempMin.next != null) {
                // Push the next node of top node in 'queue'
                pq.add(tempMin.next);
            }
        }

        // Address of head node of the required merged list
        return fakeHead.next;
    }


    /**
     * Alternative solution is MERGE SORT for sublists of lists, until sublist contains only 2 lists. In this case we just use usual mergeSort
     * https://leetcode.com/problems/merge-k-sorted-lists/solutions/4677050/merge-sort-approach/
     */
    public static LinkedListNode mergeKLists2(LinkedListNode[] lists) {
        // Base condition
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private static LinkedListNode mergeKLists(LinkedListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        // Mid of list of lists
        int mid = start + (end - start) / 2;
        // Recursive call for left sublist
        LinkedListNode left = mergeKLists(lists, start, mid);
        // Recursive call for right sublist
        LinkedListNode right = mergeKLists(lists, mid + 1, end);
        // Merge the left and right sublist
        return merge(left, right);
    }

    //NOTE! good technique: create dummy head, link it to the chain of nodes and return head.next!
    private static LinkedListNode merge(LinkedListNode left, LinkedListNode right) {
        // Create a dummy node
        LinkedListNode head = new LinkedListNode(-1);
        // Temp node
        LinkedListNode temp = head;
        // Loop until any of the list becomes null
        while (left != null && right != null) {
            // Choose the value from the left and right which is smaller
            if (left.value < right.value) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }
        // Take all nodes from left list if remaining
        while (left != null) {
            temp.next = left;
            left = left.next;
            temp = temp.next;
        }
        // Take all nodes from right list if remaining
        while (right != null) {
            temp.next = right;
            right = right.next;
            temp = temp.next;
        }
        return head.next;
    }
}