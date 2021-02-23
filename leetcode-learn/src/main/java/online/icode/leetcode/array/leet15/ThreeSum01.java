package online.icode.leetcode.array.leet15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: zhoucx
 * @time: 2021/1/31 14:26
 */
public class ThreeSum01 {


    /*
     * //输入：nums = [-1,0,1,2,-1,-4]
     * //输出：[[-1,-1,2],[-1,0,1]]
     */
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> listList = new ArrayList<>();
        if (nums == null || nums.length < 3) return listList;
        //排序， 从小到达
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //-4.-2,-1,2,4,6
            while (left < right){


                if (nums[i] > nums[left] + nums[right]) {
                    //小了
                    left ++;
                } else if (nums[i] < nums[left] + nums[right]) {
                    right --;
                } else {
                    //等于 则记录
                    listList.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left ++;
                    right --;
                    while (left < right && nums[left] == nums[left - 1]) left ++;
                    while (right < right && nums[right] == nums[right + 1]) right --;

                }

            }
        }
        return listList;
    }
}
