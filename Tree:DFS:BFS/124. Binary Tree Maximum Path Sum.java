Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42, choose 20 15 7

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7
   /     \
  -6     -4
Output: 42, choose 20 15 7
/**
对于每个node有三种情况：
0. 最终的maximum path sum的path不包含该node （则也不可能包含任何它的child）
1. 最终的maximum path sum的path是以该node为root的。 它的左右子树可能同时在max path里，也可能只有左 or 右子树在max path里，也可能都不在
2. 最终的maximum path sum的path包含该node，但不以该node为root。 意味着它的左子树和右子树不可能同时在maximum path sum的path里

Recursive解法：
定义helper function dfs(TreeNode cur) {
  1. 计算若以当前node为root，能取到的maxi path sum （对应上述情况1）
  2. 计算若以当前node为top most，但该node不是root能取到的最大path sum：左/右子树最多只可能有一种被包含 （对应上述情况3)(也对应情况0， 子树可能被丢弃)
  return的是以当前node为最top的点（但不以该node为root），能取到的最大path sum
}

在traverse的途中keep一个变量maxPathSum记录最大值
helper function dfs(TreeNode cur) {
  1.int leftGain = dfs(cur.left);
    int rightGain = dfs(cur.right);
    leftGain = max(0, leftGain); // 负的话直接设为0
    rightGain = max(0, rightGain);
  2. maxSumWithCurAsRoot = cur.val + leftGain + rightGain;
  3. maxPathSum = max(sum, maxPathSum) //对应情况1

  return max(leftGain, rightGain) + cur.val; //对应情况3
}

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
     public int maxPathSum;
     public int maxPathSum(TreeNode root) {
       if(root == null) return 0;
       maxPathSum = root.val;
       dfs(root);
       return maxPathSum;
     }

     private int dfs(TreeNode cur) {
       if (cur == null) return 0;
       int leftGain = dfs(cur.left);
       int rightGain = dfs(cur.right);
       // System.out.format("cur node is %d, maxLeft is %d, maxRight is %d%n", cur.val, leftGain, rightGain);
       leftGain = Math.max(0, leftGain);
       rightGain = Math.max(0, rightGain);
       int maxSumWithCurAsRoot = cur.val + leftGain + rightGain;
       maxPathSum = Math.max(maxPathSum, maxSumWithCurAsRoot);
       // System.out.format("cur node is %d, maxSumWithCurAsRoot is %d, maxPathSum is %d%n", cur.val, maxSumWithCurAsRoot, maxPathSum);
       return Math.max(leftGain, rightGain) + cur.val;
     }
 }
