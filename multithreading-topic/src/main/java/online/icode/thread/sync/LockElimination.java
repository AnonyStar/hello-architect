package online.icode.thread.sync;

/**
 * @author: zhoucx
 * @time: 2020/10/20 15:30
 */
public class LockElimination {


    public static void main(String[] args) {
        LockElimination elimination = new LockElimination();
        System.out.println(elimination.concatString("asd", "e", "12"));
    }

    private String concatString(String s1,String s2,String s3){
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        sb.append(s3);
        return sb.toString();
    }
}
