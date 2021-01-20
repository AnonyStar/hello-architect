package online.icode.leetcode.list.leet141;

/**
 * @author: zhoucx
 * @time: 2021/1/20 11:28
 */
public class HasCycle02 {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode f = head.next;
        ListNode s = head;
        while (f != s) {
            if (f.next == null || f.next.next == null)
                return false;

            f = f.next.next;
            s = s.next;
        }
        return true;

    }
}
