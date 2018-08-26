Given a binary tree and a sum, find all root-to-leaf paths where each paths sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Solution 1: backtracking
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
      List<List<Integer>> res = new ArrayList<List<Integer>>();
      if(root==null) return new ArrayList<List<Integer>>();
      dfs(root,0, sum, res, new ArrayList<Integer>());
      return res;
    }

    public void dfs(TreeNode root, int currentSum, int sum, List<List<Integer>> res, List<Integer> temp){
      //注意  如果在root==null的时候add temp to result是不对的
      //因为不保证这个节点的parent是leaf, 有可能只有左or右孩子
      // if(root==null){
      //   if(currentSum==sum){
      //     res.add(new ArrayList<Integer>(temp));
      //   }
      //   return;
      // }

      // it's parent only have left/right child
      if(root==null) return;
      currentSum+=root.val;
      temp.add(root.val);
      if(root.left==null && root.right==null){ //这个时候才能保证root是leaf   才可以add
        if(currentSum==sum){
          res.add(new ArrayList<Integer>(temp));
        }
      }
      dfs(root.left, currentSum, sum, res, temp);
      dfs(root.right, currentSum, sum, res, temp);
      temp.remove(temp.size()-1); //remove root
    }
}
