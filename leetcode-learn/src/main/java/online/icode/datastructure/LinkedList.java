package online.icode.datastructure;

import java.util.NoSuchElementException;

/**
 * @url: i-code.online
 * @author: 云栖简码
 * @time: 2020/12/8 20:57
 */
public class LinkedList<E> {


    private Node<E> top = new Node<>(null,null);


    public void push(E e){
        Node<E> node = new Node<>(e,top.next);
        top.next = node;
    }

    public E pop(){
        if (top.next == null){
            throw new NoSuchElementException();
        }
        final Node<E> next = top.next;
        top.next = next.next;
        return next.item;
    }



    private static class Node<E>{
        E item;
        Node<E> next;

        public Node(E item, Node<E> next){
            this.item = item;
            this.next = next;
        }
    }

}
