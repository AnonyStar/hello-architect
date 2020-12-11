package online.icode.datastructure;

/**
 * @author: zhoucx
 * @time: 2020/12/8 21:08
 */
public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.push("i-code");
        linkedList.push("云栖简码");
        linkedList.push("AnonyStar");
        System.out.println(linkedList.pop());
        System.out.println(linkedList.pop());
        System.out.println(linkedList.pop());
        System.out.println(linkedList.pop());
    }
}
