/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
本质：不限制次数的0-1背包  但必须恰好用完amount（即背包问题中的size）否则返回-1

然而这个条件会带来一个重要的改变：元素的具体位置和数量都不重要了。我们可以直接扔掉这个维度，就像 Coin Change 和 Combination Sum IV 一样。
因此可以看到，只有对元素的选择(位置) / (数量)有限制性条件的 DP，才会需要另一个维度。
对位置有要求的，用 i 代表“前 i 个元素”；
对数量有要求的，用 j 代表“选 k 个元素”；
两个全有要求的，就两个维度都加上去。
*/

class Solution {
  public static int coinChange(int[] coins, int amount) {
    if (coins == null || coins.length == 0 || amount <= 0)
      return 0;
    int[] minNumber = new int[amount + 1];
    for (int i = 1; i <= amount; i++) {
      minNumber[i] = Integer.MAX_VALUE;   //注意最后是求min  所以初始化成Integer MAX
      for (int j = 0; j < coins.length; j++) {
        //注意if要判断一下minNumber[i - coins[j]] 是不是= Integer.MAX_VALUE
        // 因为有可能minNumber[i - coins[j]]无法完成换币
        if (coins[j] <= i && minNumber[i - coins[j]] != Integer.MAX_VALUE)
          minNumber[i] = Integer.min(minNumber[i], 1 + minNumber[i - coins[j]]);
      }
    }
    if (minNumber[amount] == Integer.MAX_VALUE)
      return -1;
    else
      return minNumber[amount];
    }
}
