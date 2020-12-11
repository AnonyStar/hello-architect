package online.icode.leetcode.isValid20;

import java.util.Stack;

/**
 * @author: zhoucx
 * @time: 2020/12/7 17:02
 */
public class IsValid {


    public static void main(String[] args) {
        final boolean valid = new IsValid().isValid("[]");
        System.out.println(valid);
    }
    public boolean isValid(String s) {
        Stack stack = new Stack();
        for(int i =0;i<s.length();i++){
            char curr = s.charAt(i);
            if (isLeft(curr)) {
                stack.push(curr);
            }else {
                if (stack.empty())
                    return false;
                if (!isPair(curr,(char)stack.pop())){
                    return false;
                }
            }
        }
        if (stack.empty()){
            return true;
        }else {
            return false;
        }

    }

    public boolean isPair(char curr,char expt){
        if ((expt == '[' && curr == ']') || (expt == '{' && curr == '}') || (expt == '(' && curr == ')'))
            return true;
        return false;
    }

    public boolean isLeft(char c){
        if (c == '{' || c == '[' || c == '(')
            return true;
        return false;
    }

}
