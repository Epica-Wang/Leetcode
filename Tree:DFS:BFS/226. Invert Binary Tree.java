Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/* recursive 1*/
class Solution {
    public TreeNode invertTree(TreeNode root) {
      if(root==null) return root;
      TreeNode originLeft = root.left;
      root.left = invertTree(root.right);
      root.right = invertTree(originLeft);
    }
}

/* recursive 2*/
class Solution {
    public TreeNode invertTree(TreeNode root) {
      if(root==null) return root;
      invertTreeHelper(root);
      return root;
    }

    public void invertTreeHelper(TreeNode root){
      if(root==null) return;
      TreeNode originLeft = root.left;
      root.left = root.right;
      root.right = originLeft;
      invertTreeHelper(root.left);
      invertTreeHelper(root.right);
    }
}
