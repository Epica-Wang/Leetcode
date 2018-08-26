/*
考点只有一个：2D array 降维成 1D array 的 index trick.
*/
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;
        while(left<=right){
            int mid = left + (right - left) / 2;
            int row = mid / cols;
            int col = mid % cols;

            if(matrix[row][col] == target){
                return true;
            } else if(matrix[row][col] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
