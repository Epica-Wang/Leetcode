Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

/*
find pattern when changing direction.

Spiral order has direction cycle : right -> down -> left -> up
We just need to know which direction we're on and find what to change in each direction transition.

What to change in each direction transition?:
1. definitely index i, j
2. the limit reach of row and col for next direction

Use left, right, up, down to denote maximum 4 edges it can reach.
Initialize: left=0(first col), right = col-1, up = 0(first row), down = row-1
From right -> down: up+1
From down -> left: right-1
From left -> up: down-1
From up -> right: left+1

Along the process of changing direction, we're narrowing range of left, right, up and down
one direction pass can reach.
When left==right && up==down , means we arrive the last element.

*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
      List<Integer> res = new LinkedList<>();
      if(matrix.length==0) return res;
      int m = matrix.length;
      int n = matrix[0].length;
      //initialize limit one way can reach
      int left = 0;
      int right = n-1;
      int up = 0;
      int down = m-1;
      int direction = 0; //0:right 1:down 2:left 3:up
      //terminate condition is left=right, up=down. Done traverse all positions

      spiralTraverse(matrix, 0, 0, left, right, up, down, direction, res);
      return res;
    }

    public void spiralTraverse(int[][] matrix, int i, int j, int left, int right, int up, int down, int direction, List<Integer> res){
      if(left==right && up==down){
        res.add(matrix[i][j]);
        return;
      }
      if(i<up||i>down||j<left||j>right) return;
      if(direction==0){ // going right
        while(j<=right){
          res.add(matrix[i][j]);
          j++;
        }
        //should go down, up limit+1
        spiralTraverse(matrix, i+1, j-1, left, right, up+1, down, 1, res);
      }else if(direction==1){ // going down
        while(i<=down){
          res.add(matrix[i][j]);
          i++;
        }
        //should go left, right limit-1
        spiralTraverse(matrix, i-1, j-1, left, right-1, up, down, 2, res);
      }else if(direction==2){ //going left
        while(j>=left){
          res.add(matrix[i][j]);
          j--;
        }
        //should go up, down limit-1
        spiralTraverse(matrix, i-1, j+1, left, right, up, down-1, 3, res);
      }else{ // going up
        while(i>=up){
          res.add(matrix[i][j]);
          i--;
        }
        //should go right, left limit+1
        spiralTraverse(matrix, i+1, j+1, left+1, right, up, down, 0, res);
      }
    }
}
