Given a binary search tree and the lowest and highest boundaries as L and R,
trim the tree so that all its elements lies in [L, R] (R >= L).
You might need to change the root of the tree,
so the result should 'return' the 'new' root of the trimmed binary search tree.

//讲解太好了！！： https://www.youtube.com/watch?v=L_t2x3nH61k
/*
Recursive 思路：
trimBST(root, L, R)method 返回 该子树修剪后的新root
三种情况：
1. root>R: root以及右子树可以全部剪掉了。 return trimBST(root.left, L, R)
2. root<L: root以及左子树可以全部剪掉。  return trimBST(root.right, L, R)
3. L<=root<=R: root保留，还是修剪后树的root。递归对其左右子树进行修剪
 root.left = trimBST(root.left, L, R)
 root.right = trimBST(root.right, L, R)
 return root;
*/
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
    public TreeNode trimBST(TreeNode root, int L, int R) {
      if(root==null) return null;
      if(root.val<L){
        return trimBST(root.right, L,R);
      }
      if(root.val>R){
        return trimBST(root.left, L, R);
      }
      root.left = trimBST(root.left, L, R);
      root.right = trimBST(root.right, L, R);
      return root;
    }
}
