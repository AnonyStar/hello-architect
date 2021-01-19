package online.icode.leetcode.array.leet283;

import java.util.Arrays;

/**
 * @author: zhoucx
 * @time: 2021/1/18 20:05
 */
public class MoveZeroes {


    public static void main(String[] args) {
        int[] arr = {1,0,3,4,0,5,0,4};
        new MoveZeroes().moveZeroes(arr);
    }
    /*
    1. loop count zeros
    2. 新开数组 loop
    3. index
     */
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j){
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}
