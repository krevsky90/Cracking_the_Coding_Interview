package data_structures.chapter2_linked_lists;

/**
 * https://leetcode.com/discuss/interview-question/5158290/Amazon-OA
 * problem #1
 *
 * #Company: Amazon (OA)
 *
 * Find the longest non-increasing sublist
 *
 */
public class AmazonProcessors_LongestNonIncreasingSublist {
    public static void main(String[] args) {
//        LinkedListNode n1 = new LinkedListNode(2);
//        LinkedListNode n2 = new LinkedListNode(5);
//        LinkedListNode n3 = new LinkedListNode(4);
//        LinkedListNode n4 = new LinkedListNode(4);
//        LinkedListNode n5 = new LinkedListNode(5);

        LinkedListNode n1 = new LinkedListNode(1);
        LinkedListNode n2 = new LinkedListNode(3);
        LinkedListNode n3 = new LinkedListNode(2);
        LinkedListNode n4 = new LinkedListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
//        n4.next = n5;

        AmazonProcessors_LongestNonIncreasingSublist obj = new AmazonProcessors_LongestNonIncreasingSublist();
        LinkedListNode res = obj.longestNonIncreasingSublist(n1);
        System.out.println("");
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) keep track cntFinal, startNodeFinal, endNodeFinal - which means the length, start, end of the longest non-increasing sub-list
     * 2) keep track cnt, startNode, endNode - which means the length, start, end of the current non-increasing sub-list
     * 3) corner case: if startNodeFinal => all list is non-increasing list => return it as is
     * 4) if endNode.next = null => return startNode if current sublist is longer than the existing stored sublist
     *      OR cut endNodeFinal.next = null and return startNodeFinal
     *
     *  time to solve ~ 15 mins to think + 10 mins to implement + 5 mins to debug
     *  time ~ O(n)
     *  space ~ O(1)
     *
     *  1 attempt
     */
    public LinkedListNode longestNonIncreasingSublist(LinkedListNode head) {
        if (head == null || head.next == null) return head;

        LinkedListNode startNodeFinal = null;
        LinkedListNode endNodeFinal = null;
        int cntFinal = 0;
        LinkedListNode startNode = head;
        LinkedListNode endNode = head;
        int cnt = 1;

        while (endNode != null) {
            while (endNode.next != null && endNode.next.value <= endNode.value) {
                endNode = endNode.next;
                cnt++;
            }

            if (endNode.next == null) {
                if (startNodeFinal == null) {
                    //all list is non-increasing list
                    return head;
                } else {
                    if (cnt > cntFinal) {
                        return startNode;
                    } else {
                        endNodeFinal.next = null;
                        return startNodeFinal;
                    }
                }
            } else {
                //i.e. endNode.next.value > endNode.value
                if (cnt > cntFinal) {
                    startNodeFinal = startNode;
                    endNodeFinal = endNode;
                    cntFinal = cnt;
                }

                startNode = endNode.next;
                endNode = endNode.next;
                cnt = 1;
            }
        }

        return null;
    }
}
