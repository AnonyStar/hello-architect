package online.icode.datastructure;


/**
 * @url: i-code.online
 * @author: 云栖简码
 * @time: 2020/12/8 16:48
 */
public class Stack<T> {

    private Object[] stack;
    private int stackSize;
    private int top = -1;

    public Stack(int size){
        stackSize = size;
        stack = new Object[size];
    }

    public void push(T value){
        if (top < stackSize-1){
            top++;
            stack[top] = value;
            return;
        }
        throw new ArrayIndexOutOfBoundsException(top +"越界");
    }

    public T pop(){
        if (top > -1){
            top--;
           return (T) stack[top+1];
        }
        throw new ArrayIndexOutOfBoundsException(top +"越界");
    }

    public boolean empty(){
        return top == -1;
    }

}
