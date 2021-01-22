package online.icode.leetcode.list.leet142;

import java.util.HashMap;

/**
 * @author: zhoucx
 * @time: 2021/1/22 12:49
 */
public class DetectCycle {

    /*
    给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     */
    public ListNode detectCycle(ListNode head) {
        //通过hash来实现
        if (head == null || head.next == null) return null;
        int pos = 0;
        HashMap<ListNode,Integer> map = new HashMap<>();
        while (head.next != null) {
            if (map.containsKey(head)){
                return head;
            }
            map.put(head,pos);
            head = head.next;
            pos ++;
        }
        return null;
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
