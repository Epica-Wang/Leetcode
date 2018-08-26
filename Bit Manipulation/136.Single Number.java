Given an array of integers, every element appears twice except for one.
Find that single one.

Note:
Your algorithm should have a linear runtime complexity.
Could you implement it without using extra memory?


class Solution {
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        if(nums.length<=1) return ans;
        for(int i=1;i<nums.length;i++){
            ans = ans^nums[i];
        }
        return ans;
    }
}
