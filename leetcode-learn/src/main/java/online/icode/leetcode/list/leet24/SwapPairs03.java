package online.icode.leetcode.list.leet24;

/**
 * @author: zhoucx
 * @time: 2021/1/19 15:18
 */
public class SwapPairs03 {

    /*
    相邻链表反转
    1. 暴力，遍历，直接交换连个节点
    2. 采用递归实现，可以发现，都是在重复步骤 ，交换 head 和 next 两个节点，让head 链接后面交换好的字链，next 链接head即可
    3. 引入一个 pre 节点，然后转换为每次两个节点转换，pre作为指引头链接他们转换好的链
      pre -> 1 -> 2-> 3
      pre -2 -> 1 -> 3
                ^
               pre
     */
    public ListNode swapPairs(ListNode head) {
        if (head ==null || head.next == null) return head;

        ListNode pre =new ListNode();
        pre.next = head;
        ListNode tmp = pre;
        while (tmp.next != null && tmp.next.next != null){
            final ListNode start = tmp.next;
            final ListNode end = tmp.next.next;
            start.next = end.next;
            end.next = start;
            tmp.next = end;
            tmp = start;
        }
        return pre.next;

    }
}
