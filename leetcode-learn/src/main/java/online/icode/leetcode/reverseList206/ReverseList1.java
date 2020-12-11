package online.icode.leetcode.reverseList206;

import java.util.Stack;

/**
 * @author: zhoucx
 * @time: 2020/12/11 9:44
 */
public class ReverseList1 {
    /**
     * 通过栈来实现
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        Stack<Integer> stack = new Stack();
        while (head != null){
            stack.push(head.val);
            head = head.next;
        }
        if (stack.empty()) return null;
        ListNode newhead = new ListNode(stack.pop());
        ListNode h = newhead;
        final int size = stack.size();
        for (int i = 0; i < size; i++) {
            newhead.next  =  new ListNode(stack.pop());
            newhead = newhead.next;
        }
        return h;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
