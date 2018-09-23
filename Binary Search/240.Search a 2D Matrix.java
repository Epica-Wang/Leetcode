Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
/*
这一题与I不同， 不能用2D转换1D的方法 采取与Diagonal Binary Search(498)类似的方法，从右上或者左下开始
*/
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int x = rows - 1;
        int y = 0;
        // 从左下开始查找。 时间复杂度最差O(m+n)
        while(x >= 0 && y < cols){
            int cur = matrix[x][y];
            if(cur == target){
                return true;
            } else if(cur < target){
                y ++;
            } else {
                x --;
            }
        }

        return false;
    }
}
