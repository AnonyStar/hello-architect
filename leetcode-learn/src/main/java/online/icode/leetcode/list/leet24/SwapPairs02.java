package online.icode.leetcode.list.leet24;

/**
 * @author: zhoucx
 * @time: 2021/1/19 14:29
 */
public class SwapPairs02 {

    /*
相邻链表反转
1. 暴力，遍历，直接交换连个节点
2. 采用递归实现，可以发现，都是在重复步骤 ，交换 head 和 next 两个节点，让head 链接后面交换好的字链，next 链接head即可
 */
    public ListNode swapPairs(ListNode head) {
        if (head ==null || head.next == null) return head;
        final ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next =head;
        return next;
    }
}
