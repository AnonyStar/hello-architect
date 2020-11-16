package online.icode.threadpool;

/**
 * @author: zhoucx
 * @time: 2020/11/2 14:56
 */
public class ForkJoinApp2 {

    /**
     * 0,1,1,2,3,5,8,13,21,34,55
     * @param args
     */
    public static void main(String[] args) {
        ForkJoinApp2 app2 = new ForkJoinApp2();
        final int num = app2.num(10);

        System.out.println(num);
    }



    private int num(int num){
        if (num <= 1){
            return num;
        }
        num = num(num-1) + num(num -2);
        return num;
    }




}
