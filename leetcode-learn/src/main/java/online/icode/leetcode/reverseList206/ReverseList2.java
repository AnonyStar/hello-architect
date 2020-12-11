package online.icode.leetcode.reverseList206;

import java.util.Stack;

/**
 * @author: zhoucx
 * @time: 2020/12/11 9:44
 */
public class ReverseList2 {
    /**
     * 通过链表转换
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head== null) return null;
        ListNode tail = head;
        while (tail.next != null){
            tail =tail.next;
        }
        ListNode prev = tail.next;
        ListNode h = head;
        while (prev != tail){
            ListNode nex = h.next;
            h.next = prev;
            prev = h;
            h = nex;
        }
        return tail;
    }


    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
