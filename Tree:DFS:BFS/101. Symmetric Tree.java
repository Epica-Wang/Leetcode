/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
      if(root==null) return true;
      return isSymmetricHelp(root.left, root.right);
    }
    private boolean isSymmetricHelp(TreeNode left, TreeNode right){
      /* too trivial
      if(left==right==null)
        return true;
      if()(left!=null&&right==null)||(left==null&&right!=null))
        return false;
      */
      if(left==null || right ==null)
        return left==right;
      if(left.val!=right.val)
        return false;
      else
        return (isSymmetricHelp(left.right, right.left) && (isSymmetricHelp(left.left,right.right)));
    }
}
