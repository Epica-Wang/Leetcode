Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

For example,

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
You can return this binary search tree:

         4
       /   \
      2     7
     / \   /
    1   3 5
This tree is also valid:

         5
       /   \
      2     7
     / \
    1   3
         \
          4

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
  public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);
    helper(root, val);
    return root;
  }

  public void helper(TreeNode root, int val) {
    // insert new node at leaf level
    if (root.val > val) {
      // can insert directly to root.left
      if (root.left == null) {
        root.left = new TreeNode(val);
      }else{ // insert in root left sub tree
        helper(root.left, val);
      }
    }else{
      // can insert directly to root.right
      if (root.right == null){
        root.right = new TreeNode(val);
      }else{
        helper(root.right, val);
      }
    }
  }
}
