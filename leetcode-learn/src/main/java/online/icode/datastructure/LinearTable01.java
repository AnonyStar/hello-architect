package online.icode.datastructure;


/**
 * @author: zhoucx
 * @time: 2020/11/20 10:43
 */
public class LinearTable01 {
    /*
    例 1，链表的翻转。给定一个链表，输出翻转后的链表。\
    例如，输入1 ->2 -> 3 -> 4 ->5，输出 5 -> 4 -> 3 -> 2 -> 1。
     */

    public static void main(String[] args) {
        //Node node = new Node();
        NodeList<String> list = new NodeList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(list);


    }
}


class NodeList<T>{

    //头节点
    private Node<T> first;
    //头指针
    private Node<T> head;
    private int size;
    public NodeList(){
        size =0;
        first = new Node<T>(null,null);
        head = new Node<T>(null,first);
    }
    public Node<T> getNode(int index){
        Node<T> rende = head;
        for (int i = -2; i < index; i++) {
              rende = rende.next;
        }
        return rende;
    }

    public void add(T v){
        Node<T> node = new Node<>(v,null);
        getNode(size-1).next = node;
        size++;
    }


    @Override
    public String toString() {
        return "NodeList{" +
                "first=" + first +
                ", head=" + head +
                ", size=" + size +
                '}';
    }
}


class Node<T>{
    T value;
    Node<T> next;
    public Node(T value,Node<T> node){
        this.value = value;
        this.next = node;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}


