package data_structures.chapter2_linked_lists;

/**
 * p.107
 * Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
 * beginning of the loop.
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
 * as to make a loop in the linked list.
 * EXAMPLE
 * Input: A -> B -> C -> D -> E -> C [the same C as earlier]
 * Output: C
 * Hints: #50, #69, #83, #90
 * <p>
 * ASSUMPTION/VALIDATION:
 *
 * the same problem is
 * 142. Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 */
public class Problem2_8 {
    public static void main(String[] args) {
        LinkedListNode n11 = new LinkedListNode(1);
        LinkedListNode n12 = new LinkedListNode(2);
//        LinkedListNode n13 = new LinkedListNode(3);
//        LinkedListNode n14 = new LinkedListNode(4);
//        LinkedListNode n15 = new LinkedListNode(5);
        n11.next = n12;
        n12.next = n11;
//        n12.next = n13;
//        n13.next = n14;
//        n14.next = n15;
//        n15.next = n13;

        LinkedListNode result = getStartLoopNode(n11);
        System.out.println(result == null ? null : result.value);

    }

    /**
     * SOLUTION: p.235
     * Idea: slowRunner and fastRunner approach.
     * FastSpeed / SlowSpeed = 2
     * Much like two cars racing around a track at different steps, they must eventually meet. Why?
     * Suppose that FastRunner did hop over SlowRunner, such that
     * SlowRunner is at spot i and FastRunner is at spot (i + 1). In the previous step, SlowRunner would
     * be at spot (i - 1) and FastRunner would at spot ((i + 1) - 2), or spot (i - 1).That is, they would
     * have collided.
     * <p>
     * пусть k нод вне цикла, L нод - длина цикла.
     * Когда slowRunner придет в точку startLoopNode, сделав k шагов, fastRunner сделает 2k шагов.
     * fastRunner будет впереди на mod(k/L) шагов. Но т.к. fastRunner в роли догоняющего, то указатели встретятся через L - mod(k/L) шагов.
     * К моменту встречи slowRunner будет находиться на расстоянии L - mod(k/L) от startLoopNode.
     * Или... на расстоянии mod(k/L), если смотреть в др сторону. Однако также верно утверждение, что на расстоянии k шагов.
     * <p>
     * Итак, мы знаем headNode (дано), collideNode (место встречи fastRunner = slowRunner),
     * а также то, что от headNode до startLoopNode k шагов, и от collideNode до startLoopNode тоже k шагов.
     * Значит, можем идти от headNode и от collideNode с одинаковой скоростью. Место встречи = startLoopNode
     */
    public static LinkedListNode getStartLoopNode(LinkedListNode head) {
        if (head == null) {
            return null;
        }

        LinkedListNode slowRunner = head;
        LinkedListNode fastRunner = head;
        LinkedListNode collideNode = null;
        /* Find meeting point. This will be LOOP_SIZE - k steps into the linked list. */
        while (fastRunner != null && fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
            if (slowRunner == fastRunner) {
                //collideNode is found
                collideNode = fastRunner;
                break;
            }
        }

        //list doesn't have loop since we escaped from while
        if (collideNode == null) {
            return null;
        } else {
            //find startLoopNode
            LinkedListNode startFromHead = head;
            while (startFromHead != slowRunner) {
                startFromHead = startFromHead.next;
                slowRunner = slowRunner.next;
            }
            //startLoopNode = slowRunner = startFromHead
            return slowRunner;
        }
    }

    /**
     * SOLUTION: original
     */
    public static LinkedListNode getStartLoopNodeOriginal(LinkedListNode head) {
        LinkedListNode slowRunner = head;
        LinkedListNode fastRunner = head;
        /* Find meeting point. This will be LOOP_SIZE - k steps into the linked list. */
        while (fastRunner != null && fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
            if (slowRunner == fastRunner) { //collideNode is found
                break;
            }
        }

        /* Error check - no meeting point, and therefore no loop */
        if (fastRunner != null && fastRunner.next != null) {
            return null;
        }

        /* Move slow to Head. Keep fast at Meeting Point. Each are k steps from the
         * Loop Start. If they move at the same pace, they must meet at Loop Start. */
        slowRunner = head;
        while (slowRunner != fastRunner) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next;
        }
        /* Both now point to the start of the loop. */
        return slowRunner;
    }
}
