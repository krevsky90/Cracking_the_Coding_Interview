package data_structures.chapter2_linked_lists;

import com.sun.deploy.util.StringUtils;

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
        List<String> sb = new ArrayList<>();
        sb.add("(" + head.value + ")");
        LinkedListNode tempNode = head.next;
        while (tempNode != null) {
            sb.add("(" + tempNode.value + ")");
            tempNode = tempNode.next;
        }
        return StringUtils.join(sb, " -> ");
    }
}
