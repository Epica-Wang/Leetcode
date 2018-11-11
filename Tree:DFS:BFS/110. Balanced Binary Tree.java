/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
Time: O(N^2). for each root of subtree, need to go through all its children node to compute maxDepth
*/
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root!=null){
        int leftSubHeight = TreeHeight(root.left);
        int rightSubHeight = TreeHeight(root.right);
        if (Math.abs(leftSubHeight-rightSubHeight)<=1)
          return (isBalanced(root.left)&&isBalanced(root.right));
        else
          return false;
      }
        return true;
    }

    private int TreeHeight(TreeNode root){
      if(root==null)
        return 0;
      return Math.max(TreeHeight(root.left),TreeHeight(root.right))+1;
    }
}

/*Solution 2
 Time O(N)*/
public class Solution {
  private boolean result = true;

  public boolean isBalanced(TreeNode root) {
      maxDepth(root);
      return result;
  }

  public int maxDepth(TreeNode root) {
      if (root == null)
          return 0;
      int l = maxDepth(root.left);
      int r = maxDepth(root.right);
      if (Math.abs(l - r) > 1)
          result = false;
      return 1 + Math.max(l, r);
  }
}
/* My 1st solution. Top-down approach:the difference between the heights of the two sub trees are not bigger than 1,
 and both the left sub tree and right sub tree are also balanced.
 Time: O(n^2).For the current node root, calling TreeHeight for its left and right children actually has to access all of its children, thus the complexity is O(N).
 We do this for each node in the tree, so the overall complexity of isBalanced will be O(N^2).
class Solution {
    public boolean isBalanced(TreeNode root) {
      if(root!=null){
        int leftSubHeight = TreeHeight(root.left);
        int rightSubHeight = TreeHeight(root.right);
        if(Math.abs(leftSubHeight-rightSubHeight)<=1)
        //if(((leftSubHeight>=rightSubHeight) && (leftSubHeight-rightSubHeight <=1))||((rightSubHeight>=leftSubHeight)&&(rightSubHeight - leftSubHeight<=1)))
          return (isBalanced(root.left)&&isBalanced(root.right));
        else
          return false;
      }
      return true;
    }

    private int TreeHeight(TreeNode root){
      if(root==null)
        return 0;
      return max((TreeHeight(root.left),TreeHeight(root.right))+1;
    }
}
*/
