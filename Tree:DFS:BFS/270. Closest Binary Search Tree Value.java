Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
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
若root <target, 在root.right里找 (root的左子树里不会有比root更近的了)
若root> target, 在root.left里找 （root的右子树里不会有比root更近的了）
过程中维护closest的点
直到reach leaf为止
 */
class Solution {
    public int closestValue(TreeNode root, double target) {
      TreeNode closest = root;
      while(root!=null){
        if(root.val == target) return root.val;
        if(root.val>target){
          closest = Math.abs(closest.val-target) >= Math.abs(root.val-target) ? root:closest;
          root = root.left;
        }else if(root.val<target){
          closest = Math.abs(closest.val-target) >= Math.abs(root.val-target) ? root:closest;
          root = root.right;
        }
      }
      return closest.val;
    }
}
