package online.icode.datastructure;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * @author: zhoucx
 * @time: 2020/12/9 8:55
 */
public class reverseList {
    /*
    给定一个包含 n 个元素的链表，现在要求每 k 个节点一组进行翻转，打印翻转后的链表结果。其中，k 是一个正整数，
    且 n 可被 k 整除。
    例如，链表为 1 -> 2 -> 3 -> 4 -> 5 -> 6，k = 3，则打印 321654。
     */


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.push(2);
        listNode.push(3);
        listNode.push(4);
        listNode.push(5);
        listNode.push(6);
        reverseKGroup(listNode,3);

    }


    public static void reverseKGroup(ListNode list,int k){

        Stack<Integer> stack = new Stack<>();
        LinkedList<Integer> result = new LinkedList<>();
        while (list != null){
            for (int i = 0; i < 3; i++) {
                final int val = list.val;
                list = list.next;
                stack.push(val);
            }
            for (int i = 0; i < 3; i++) {
                final Integer pop = stack.pop();
                result.push(pop);
                System.out.print(pop);
            }
        }
        System.out.println(result);

    }



    static class ListNode {
        int val;
        ListNode next;
        ListNode() {

        }
        ListNode(int val) {
            this.val = val;
            top = this;
            fail = this;
        }
        ListNode(int val, ListNode next) {
            this.val = val; this.next = next;
            top = this;
            fail = this;
        }
        private ListNode top ;
        private ListNode fail ;
        public void push(int e){
            if (top == null){
                top = new ListNode(e,null);
                fail = top;
            }else {
                ListNode node = new ListNode(e,null);
                fail.next = node;
                fail = node;
            }

        }

        public int pop(){
            if (top == null){
                throw new NoSuchElementException();
            }
            final int val = top.val;
            top = top.next;
            return val;
        }
    }
}
