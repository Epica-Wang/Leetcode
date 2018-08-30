Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

/* 对比53 maximum subarray，多了个product的要求。需要考虑多负数相乘也可能为正的情况  每个位置只存max就不够了
contiguous subarray的题，每个位置的result都跟：前一个位置的result和当前位置的element有关系
dp[i]需要存两个变量: 以nums[i]结尾所能achieve的max product和min product。
因为这道题有负数

如果已知dp[i-1][min] & dp[i-1][max],则
dp[i][min] = min(nums[i], dp[i-1][max]*nums[i], dp[i-1][min]*nums[i])
dp[i][max] = max(nums[i], dp[i-1][max]*nums[i], dp[i-1][min]*nums[i])
*/

class Solution {
    public int maxProduct(int[] nums) {
      if(nums.length==0) return 0;
      int[][] dp = new int[nums.length][2];
      dp[0][0] = nums[0]; // min
      dp[0][1] = nums[0]; // max
      int maxProduct = nums[0];
      for(int i=1;i<nums.length;i++){
        dp[i][0] = Math.min(nums[i], Math.min(dp[i-1][0]*nums[i], dp[i-1][1]*nums[i]));
        dp[i][1] = Math.max(nums[i], Math.max(dp[i-1][0]*nums[i], dp[i-1][1]*nums[i]));
        maxProduct = Math.max(maxProduct, dp[i][1]);
      }
      return maxProduct;
    }


}
