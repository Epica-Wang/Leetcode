/*
这题考察环形数组中，subproblem 的拆分与覆盖。
数组变成环形之后，就需要考虑首尾相接的问题了~ 理论上说，对于长度为 n 的环形数组，我们现在有了 n 种不同的切分方式，去处理长度为 n 的线性数组。
不过我们不需要考虑所有的可能切分，因为只有相邻元素之间会出现问题。我们的 subproblem 不能再以 size = n 开始 top-down了，因为无法保证正确性； 但是 size = n - 1 的所有 subproblems，一定是正确的。我们只需要考虑，如何拆分 size = n - 1 的 subproblems，并且如何用他们构造出全局最优解。
只需要把环形数组拆分成两个头尾不同的 size = n - 1 的 subproblems 就可以了：
【1, 7, 5, 9, 2】
下面的 subproblem 覆盖了所有不抢最后一座房子的 subproblems；
【(1, 7, 5, 9) 2】
如下的 subproblem 覆盖了所有不抢第一座房子的 subproblems；
【1,(7, 5, 9, 2)】
如果最后的最优解第一座和最后一座房子都不抢，也一定会被包含在左右两个 subproblem 的范围内，因为其 size = n - 2;
【1, (7, 5, 9), 2】
由于我们不能同时抢第第一和最后一座房子，上面两个 overlap subproblem 一定覆盖了所有子问题的最优解，并且符合全局最优解的 optimal substructure，保证了算法的正确性。
*/
public class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);

        int leftMax = helper(nums, 0);
        int rightMax = helper(nums, 1);

        return Math.max(leftMax, rightMax);
    }

    private int helper(int[] nums, int start){
        int max = 0;
        int[] dp = new int[2];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);

        for(int i = 2; i < nums.length - 1; i++){
            dp[i % 2] = Math.max(dp[(i - 1) % 2], dp[(i - 2) % 2] + nums[i + start]);
        }

        return dp[(nums.length - 2) % 2];
    }
}
