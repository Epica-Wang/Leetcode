Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

/*
对比一下698 很相似

Time Complexity:
O(M*N * 4^L) where M * N is the number of cells in the board and L is the length of the word to be matched.
-> For the backtracking function, its execution trace would be visualized as a 4-ary tree,
each of the branches represent a potential exploration in the corresponding direction.
Therefore, in the worst case, the total number of invocation would be the number of nodes in a full 4-nary tree, which is about 4^L.
-> We iterate through the board for backtracking, i.e. there could be NN times invocation for the backtracking function in the worst case.
As a result, overall the time complexity of the algorithm would be O(M*N * 4^L).

Space Complexity: O(L) where L is the length of the word to be matched.
The main consumption of the memory lies in the recursion call of the backtracking function.
The maximum length of the call stack would be the length of the word.
Therefore, the space complexity of the algorithm is O(L).

*/
class Solution {
    public boolean exist(char[][] board, String word) {
      if(word.equals("")) return true;
      int row = board.length;
      if(row==0) return false;
      int col = board[0].length;
      if(col==0) return false;

      boolean[][] visited = new boolean[row][col];
      for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){
          if(dfs(board,visited,word,0,i,j,row,col)){
            return true;
          }
        }
      }
      return false;
    }
    public boolean dfs(char[][] board, boolean[][] visited, String word, int index, int i, int j,int row, int col){
      if(index==word.length()) return true;
      //记得要先检查index是不是到了最后。不能先检查i，j是不是符合边界条件。
      //有可能index已经到最后了，可以return true 但此时i，j刚好也越界
      if(i<0||i>=row||j<0||j>=col) return false;
      if(board[i][j]!=word.charAt(index)||visited[i][j]) return false;

      //then board[i][j]==word.charAt(index) && visited[i][j]==false
      visited[i][j] = true;
      if(dfs(board,visited,word,index+1,i+1,j,row,col) ||
      dfs(board,visited,word,index+1,i-1,j,row,col) ||
      dfs(board,visited,word,index+1,i,j+1,row,col) ||
      dfs(board,visited,word,index+1,i,j-1,row,col)){
        return true;
      }else{
        visited[i][j] = false; //记得还原状态！
      }
      return false;
    }
}
