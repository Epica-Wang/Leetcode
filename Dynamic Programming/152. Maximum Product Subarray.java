Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

/*
contiguous subarray的题，每个位置的result都跟：前一个位置的result和当前位置的element有关系
dp[i]: 以nums[i]结尾所能achieve的max product

dp[i] = nums[i]: if dp[i-1]*nums[i]<0
dp[i] = dp[i-1]*nums[i]: if
*/
