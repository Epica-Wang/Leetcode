/*
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=441773&extra=&page=1
一道DP的题目。玩卡牌，N张卡，卡上有数字，可正可负。
两个玩家，每个人最多可以从front选1，2或3张牌，自己先开始，问最多能获得的分数是多少，score就是player选择的卡上数字之和。
*/

题目解析:

两个玩家A和B，每个人每次可以选择1，2或3张牌。从A玩家开始，目标是消耗完所有卡牌A能获取的最大分数。
首先明确一个原则，任何一个玩家在选择卡牌时，目标都是要获取最大的分数。
所以任何玩家面对任意多张卡牌时的目标都是获取最大分数。
即A先选，A会计算面对剩余的i张卡牌取1，2或者3张获得最大分数。
轮到B选择时，B会计算面对剩余的A取之后的卡牌取1，2或者3张怎么获得最大分数。


基本思路:
基于以上的分析，我们可以知道，A面对n张卡片获取的最大分数与:B面对n－1张卡片获取的最大分数，B面对n－2张卡片获取的最大分数和B面对n－3张卡片获取的最大分数有关。
什么关系呢？具体推倒如下，我们定义A面对n张卡片获取的最大分数为dp[A][n].上面的关系即可以表达为dp[A][n]取决于dp[A][n－1], dp[A][n－2]和dp[B][n－3]。到底dp[A][n]如何表达呢?
如果A选择1张卡牌，dp[A][n] = sum(n-1) - dp[B][n-1] + cards[0]
如果A选择2张卡牌，dp[A][n] = sum(n-2) - dp[B][n-2] + cards[0] + card[1]
如果A选择3张卡牌，dp[A][n] = sum(n-3) - dp[B][n-3] + cards[0] + card[1] + card[2]

根据题目要求取以上三种情况下的最大值.

我们发现 sum(n-1) + cards[0] = sum(n-2) + cards[0] + cards[1] = sum(n-3) + cards[0] + cards[1] + cards[2] = sum(n).
所以dp[A][n] ＝ max(sum[n] - dp[B][n-1], sum[n] - dp[B][n-2], sum[n] - dp[B][n-3]);
或者说 dp[A][n] ＝ sum[n] － min(dp[B][n-1], dp[B][n-2], dp[B][n-3]);

因此我们可以写出以下代码
class Solution {
    int maxScore(int[] cards) {
        int n = cards.length;
        int[][] dp = new int[2][n + 1];
        int sum = 0;
        int A, B;
        A = 0; B = 1;

        for (int i = 1; i <= n; i++) {
            // sum is the score sum of left i cards
            sum += cards[n - i];

            for (int j = 0; j < 2; j++) {
                int min = Integer.MAX_VALUE;
                if (i >= 3) min = Math.min(min, dp[B][i - 3]);
                if (i >= 2) min = Math.min(min, dp[B][i - 2]);
                if (i >= 1) min = Math.min(min, dp[B][i - 1]);
                dp[A][i] = sum - min;

                A = (A + 1) % 2;
                B = (B + 1) % 2;
            }

        }

        return dp[0][n];
    }
}

优化1:

手动跑cards[] = {1, 2, -3, 8}; 我们发现其实dp[A]和dp[B]是完全相同的。
因为dp[A][n]意味A先选和dp[B][n]意味着B先选。
A和B的位置可以互选且对称的。因此我们可以仅仅定义一维数组dp[n]. dp[n]表示玩家面对n张卡牌获取的最大分数。

class Solution {
    int maxScore(int[] cards) {
        int n = cards.length;
        int[] dp = new int[n + 1];
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += cards[n - i];
            int min = Integer.MAX_VALUE;
            if (i >= 3) min = Math.min(min, dp[i - 3]);
            if (i >= 2) min = Math.min(min, dp[i - 2]);
            if (i >= 1) min = Math.min(min, dp[i - 1]);

            dp[i] = sum - min;
        }

        return dp[n];
    }
}


优化2:
再次审视代码，dp[n]只与dp[n－1]，dp[n－2]和dp[n－3]有关，我们甚至不需要一维数组，三个变量即可。
class Solution {
    int maxScore(int[] cards) {
        int n = cards.length;
        int p, q, r, sum;

        p = q = Integer.MAX_VALUE;  //p代表i-3, q代表i-2

        // init when no card
        r = sum = 0;   // r代表i

        for (int i = 1; i <= n; i++) {
            sum += cards[n - i];

            int min = Math.min(Math.min(p, q), r);
            p = q;
            q = r;
            r = sum - min;
        }
        return r;
    }
}
