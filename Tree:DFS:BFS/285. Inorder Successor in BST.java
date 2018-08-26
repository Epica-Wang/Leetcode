Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.

Example 1:

Input: root = [2,1,3], p = 1

  2
 / \
1   3

Output: 2
Example 2:

Input: root = [5,3,6,2,4,null,null,1], p = 6

      5
     / \
    3   6
   / \
  2   4
 /
1

Output: null
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
BST 里面，任意位置，任意楼层，都可以通过 value 的比较确定相对位置，这是 BST 一个最好用的性质。
因此在 BST 里面，确定起来就很简单了，从 root 往下走，
每次往左拐的时候，存一下，记录着最近一个看到的比 p.val 大的 node 就行了。
*/
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
      TreeNode res = null;
      while(root!=null){
        if(root.val>p.val){
          res = root;
          root = root.left;
        }else if(root.val<=p.val){
          root = root.right;
        }
      }
      return res;
    }
}
