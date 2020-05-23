/**
考察二维array与matrix下标的对应关系

一个r * c的矩阵，按row-traversing的顺序，标号为N的元素(从0开始， 实际上为第N+1 个元素若从1开始数)
所在的row，col（也base on 0）为：
row = N / c;
col = N % c;

栗子：5 * 6 的矩阵，r = 5， c = 6. 按0开始数row traversing，元素标号为0~29.
标号为27的元素（实际上是第28个若从1开始数）
所在行row = 27 /6 = 4 (0, 1, 2, 3, 4)
所在列col = 27 % 6 = 3 (0,1,2 ,3)
*/
public class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int n = nums.length, m = nums[0].length;
        if (r*c != n*m) return nums;
        int[][] res = new int[r][c];
        for (int i=0;i<r*c;i++)
            res[i/c][i%c] = nums[i/m][i%m];
        return res;
	}
}
