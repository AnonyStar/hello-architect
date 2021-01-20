package online.icode.leetcode.list.leet25;

/**
 * @author: zhoucx
 * @time: 2021/1/20 15:03
 */
public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 3-4-3-21-1
        // 划分问题  每个子节点的转换
        // 引入pre作为一个子节点的上一个节点， end为尾节点
        final ListNode dump = new ListNode();
        dump.next = head;
        ListNode pre = dump,end = dump;

        while (end.next != null){
            for (int i = 0; i < k && end!= null; i++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode start = pre.next;
            // ListNode next = end.next;
            ListNode node = reverse(start,end);
            pre.next = node;
            pre = start;
            end = pre;
        }

        return dump.next;
    }

    private ListNode reverse(ListNode start,ListNode end) {
        //2-3-4-22
        while (start != end) {
            final ListNode next = end.next;
            end.next = start;
            final ListNode node = start.next;
            start.next = next;
            start = node;
        }
        return end;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
