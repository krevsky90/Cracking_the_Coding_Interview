package data_structures.chapter2_linked_lists.amazon_igotanoffer;

public class DoubleListNode {
    public int val;
    public DoubleListNode next;
    public DoubleListNode prev;

    public DoubleListNode() {
    }

    public DoubleListNode(int val) {
        this.val = val;
    }

    public DoubleListNode(int val, DoubleListNode prev, DoubleListNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}

