package online.icode.leetcode.array.leet283;

/**
 * @author: zhoucx
 * @time: 2021/1/31 15:28
 */
public class MoveZeroes01 {

    // 输入: [0,1,0,3,12]
    //输出: [1,3,12,0,0]
    public void moveZeroes(int[] nums) {
        if (nums == null) return;

        int y = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                nums[y] = nums[i];
                if (y != i){
                    nums[i] = 0;
                }
                y++;

            }
        }


    }

}
