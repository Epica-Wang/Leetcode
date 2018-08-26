Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

/*
2d背包优化空间到1d：
dp[i]表示可否和为i
遍历nums时，从sum到0修改加上current num后可以表示的和
注意要从sum to 0，不能从0到sum
Because dp[i] = dp[i] || dp[i-num] uses smaller index value dp[i-num].
When the current iteration begins, the values in dp[] are the result of previous iteration.
Current iteration's result should only depend on the values of previous iteration.
If you iterate from i = 0, then dp[i-num] will be overwritten before you use it, which is wrong.
You can avoid this problem by iterating from i=sum
*/

class Solution {
  public boolean canPartition(int[] nums) {
    int sum = 0;

    for (int num : nums) {
        sum += num;
    }

    if ((sum & 1) == 1) {
        return false;
    }
    sum /= 2;

    int n = nums.length;
    boolean[] dp = new boolean[sum+1];
    //Arrays.fill(dp, false);
    dp[0] = true;
    for (int num : nums) {
        for (int i = sum; i > 0; i--) {
            if (i >= num) {
                dp[i] = dp[i] || dp[i-num];
            }
        }
    }
    return dp[sum];
   }
}
/*
解法1：2d 背包问题。 n个item 重量weight 是否能装满m的背包
dp[i][j]是否能用前i个物品装满重量为j的背包
Transition function:
  For each number, if we don't pick it, dp[i][j] = dp[i-1][j],
  which means if the first i-1 elements has made it to j, dp[i][j] would also make it to j (we can just ignore nums[i]).
  If we pick nums[i](只有当j>nums[i]的时候才可考虑). dp[i][j] = dp[i-1][j-nums[i]], which represents that j is composed of the current value nums[i] and the remaining composed of other previous numbers.
  Thus, the transition function is dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
*/
public boolean canPartition(int[] nums) {
    int sum = 0;

    for (int num : nums) {
        sum += num;
    }

    if ((sum & 1) == 1) {
        return false;
    }
    sum /= 2;

    int n = nums.length;
    boolean[][] dp = new boolean[n+1][sum+1];
    for (int i = 0; i < dp.length; i++) {
        Arrays.fill(dp[i], false);
    }

    dp[0][0] = true;

    for (int i = 1; i < n+1; i++) {
        dp[i][0] = true;
    }
    for (int j = 1; j < sum+1; j++) {
        dp[0][j] = false;
    }

    for (int i = 1; i < n+1; i++) {
        for (int j = 1; j < sum+1; j++) {
            dp[i][j] = dp[i-1][j];
            if (j >= nums[i-1]) {
                dp[i][j] = (dp[i][j] || dp[i-1][j-nums[i-1]]);
            }
        }
    }

    return dp[n][sum];
}
