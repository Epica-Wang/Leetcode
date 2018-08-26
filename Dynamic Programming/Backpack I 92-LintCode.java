/*
Given n items with size Ai, an integer m denotes the size of a backpack.
How full you can fill this backpack?

题解： https://zhengyang2015.gitbooks.io/lintcode/backpack_i_92.html
*/
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        if(m == 0 || A == null || A.length == 0){
            return 0;
        }

        int n = A.length;
        int[][] fillPack = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++){
            fillPack[i][0] = 0;
        }

        for(int j = 0; j <= n; j++){
            fillPack[0][j] = 0;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(i - A[j - 1] >= 0){
                    //一种情况为不要第j件物品，则最优情况为背包容量为i时前j－1件物品的最优值；第二种情况为要第j件物品，则最优情况为背包容量为i-第j件物品体积时前j－1件物品的最优值加上第j件物品的值。
                    fillPack[i][j] = Math.max(fillPack[i][j - 1], fillPack[i - A[j - 1]][j - 1] + A[j - 1]);
                }else{
                    fillPack[i][j] = fillPack[i][j - 1];
                }
            }
        }

        return fillPack[m][n];
    }
}
