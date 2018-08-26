Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

/*
3 pointer。 跟3sum一样，但少了去重的步骤
scan的时候维护一个跟target的差值
*/
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3) return -1;

        int curMin = Math.abs(nums[0] + nums[1] + nums[2] - target);
        int curSum = nums[0] + nums[1] + nums[2];

        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++){
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == target){
                    return sum;
                } else if(sum < target){
                    left ++;
                } else if(sum > target){
                    right --;
                }

                if(Math.abs(sum - target) < curMin){
                    curMin = Math.abs(sum - target);
                    curSum = sum;
                }
            }
        }

        return curSum;
    }
}
