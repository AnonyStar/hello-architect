package online.icode.leetcode.array.leet15;

/**
 * //给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
 * //复的三元组。
 * //
 * // 注意：答案中不可以包含重复的三元组。
 * //
 * //
 * //
 * // 示例 1：
 * //
 * //
 * //输入：nums = [-1,0,1,2,-1,-4]
 * //输出：[[-1,-1,2],[-1,0,1]]
 * //
 * //
 * // 示例 2：
 * //
 * //
 * //输入：nums = []
 * //输出：[]
 * //
 * //
 * // 示例 3：
 * //
 * //
 * //输入：nums = [0]
 * //输出：[]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: zhoucx
 * @time: 2021/1/19 10:41
 */
public class ThreeSum {


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> listList = new ArrayList();
        if (nums == null || nums.length <= 2) return listList;

        //排序 将数组
        Arrays.sort(nums); // nlogn

        for (int i = 0; i < nums.length - 2; i++) { //O(n^2)

            if (nums[i] > 0) break; //大于零后面的都比这大，

            if (i >0 && nums[i] == nums[i - 1]) continue;

            int target = -nums[i];
            //进行左右指针移动计算
            int left = i + 1, right = nums.length - 1;

            while (left < right){
                if (nums[left] + nums[right] == target) {
                    listList.add(Arrays.asList(nums[left], nums[right], -target));
                    left ++;
                    right --;
                    while (left < right && nums[left] == nums[left - 1]) left ++;
                    while (left < right && nums[right] == nums[right + 1]) right --;

                } else if(nums[left] + nums[right] > target) {
                    //得到的值大了移动右侧的指针
                    right --;
                } else {
                    left ++;
                }
            }
        }

        return listList;
    }

}
