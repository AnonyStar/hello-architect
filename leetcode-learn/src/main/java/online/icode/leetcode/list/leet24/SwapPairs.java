package online.icode.leetcode.list.leet24;

/**
 * @author: zhoucx
 * @time: 2021/1/19 14:27
 */
public class SwapPairs {
    /*
   相邻链表反转
   1. 暴力， 交互值，取巧，并未实际交换节点
    */
    public ListNode swapPairs(ListNode head) {
        //    3->5->34->5
        if (head == null || head.next == null) return null;

        while (head!=null && head.next != null){ //到尾节点结束
            final ListNode node2 = head.next;
            final int val = node2.val;
            node2.val = head.val;
            head.val = val;
            head = node2.next;
        }
        return head;
    }


}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}