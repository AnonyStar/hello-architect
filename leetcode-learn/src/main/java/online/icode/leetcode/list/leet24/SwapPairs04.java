package online.icode.leetcode.list.leet24;

/**
 * @author: zhoucx
 * @time: 2021/2/1 10:16
 */
public class SwapPairs04 {


    /*
      相邻链表反转
     */
    public ListNode swapPairs(ListNode head) {
        //1,2,45,23,54
        if (head == null || head.next == null) return head;
        ListNode tmp = head;
        while (head != null && head.next != null) {
            //换值
            final ListNode next = head.next;
            final int val = head.val;
            head.val = next.val;
            next.val = val;
            head = head.next.next;
        }
        return tmp;
    }

}
