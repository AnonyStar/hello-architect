package online.icode.datastructure;

/**
 * @author: zhoucx
 * @time: 2020/12/8 17:04
 */
public class StackTest {


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(3);
        stack.push(13);
        stack.push(2);
        stack.push(1);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }
}
