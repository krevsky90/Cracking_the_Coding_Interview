package data_structures.chapter2_linked_lists;

import java.util.ArrayList;
import java.util.List;

public class LinkedListUtils {
//    public static String linkedListToString(LinkedListNode head) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("(").append(head.value).append(") -> ");
//        LinkedListNode tempNode = head.next;
//        while (tempNode != null) {
//            sb.append("(").append(tempNode.value).append(") -> ");
//            tempNode = tempNode.next;
//        }
//        return sb.toString();
//    }

    public static String linkedListToString(LinkedListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + head.value + ")");
        LinkedListNode tempNode = head.next;
        while (tempNode != null) {
            sb.append(" -> ").append("(" + tempNode.value + ")");
            tempNode = tempNode.next;
        }
        return sb.toString();
    }

    public static void printLinkedList(LinkedListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(" " + head.value);
            head = head.next;
        }

        System.out.println(sb.toString());
    }
}
