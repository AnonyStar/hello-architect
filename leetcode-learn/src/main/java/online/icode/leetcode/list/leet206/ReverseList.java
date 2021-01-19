package online.icode.leetcode.list.leet206;

/**
 * @author: zhoucx
 * @time: 2021/1/19 12:54
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        // 1. 定位最后一个节点
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        // 2. 以 tail 为 头节点开始新构建，直到遇到节点的  == tail
        while (head != null && head != tail) {
            final ListNode next = tail.next;
            ListNode newHead = head.next;
            tail.next = head;
            head.next = next;
            head = newHead;
        }
        return tail;

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
