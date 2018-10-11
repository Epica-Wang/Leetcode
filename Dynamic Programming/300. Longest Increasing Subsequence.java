Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?

/* dp写法 O(N^2)
dp[i]: from start to index i (end with nums[i]), the lengthOfLIS.
对于每个i，dp[i]需要scan之前所有的位置才能确定。不止跟前一个位置有关。而是跟前面所有小于nums[i]的元素都有关
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
      if (nums.length == 0) return 0;
      int[] dp = new int[nums.length];
      Arrays.fill(dp, 1);
      int max = 1;
      for (int i = 1; i < nums.length; i++){
        for (int j=i-1; j >= 0; j--){
          if (nums[i] > nums[j]){
            dp[i] = Math.max(dp[i], dp[j] + 1);
          }
        }
        max = Math.max(max, dp[i]);
      }
      return max;
    }
}
