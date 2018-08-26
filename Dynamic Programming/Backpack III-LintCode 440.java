/*
https://mnmunknown.gitbooks.io/algorithm-notes/content/82ff0c_bei_bao_wen_ti.html
和背包 II 看起来结构一样，但是这次多了一个新条件：同一个元素可以取多次。
然而这个条件会带来一个重要的改变：元素的具体位置和数量都不重要了。我们可以直接扔掉这个维度，就像 Coin Change 和 Combination Sum IV 一样。
因此可以看到，只有对元素的选择(位置) / (数量)有限制性条件的 DP，才会需要另一个维度。
对位置有要求的，用 i 代表“前 i 个元素”；
对数量有要求的，用 j 代表“选 k 个元素”；
两个全有要求的，就两个维度都加上去。
两个维度都加上去，我们就有了 k sum 问题。
*/
public class Solution {
    /**
     * @param A an integer array   Ai: size of item i
     * @param V an integer array   Vi: value of item i
     * @param m an integer    backpack size m
     * @return an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        // Write your code here
        // Maximum value we can get
        int[] dp = new int[m + 1];
        //dp[j] : 前 i 个数填满大小为 j 的背包，最大值是多少。
        for(int i = 0; i < A.length; i++){
            for(int j = 1; j <= m; j++){
                if(j - A[i] >= 0)
                    dp[j] = Math.max(dp[j], dp[j - A[i]] + V[i]);
            }
        }

        return dp[m];
    }
}
