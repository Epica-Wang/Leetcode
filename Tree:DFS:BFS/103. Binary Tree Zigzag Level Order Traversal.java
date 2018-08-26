Given a binary tree, return the zigzag level order traversal of its nodes values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
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
/* 同102 level order 只是多一个boolean reverse判断要不要翻转*/
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
      List<List<Integer>> res = new ArrayList<>();
      Queue<TreeNode> queue = new LinkedList<>();
      boolean reverse = false;
      if(root==null) return res;
      queue.add(root);
      while(!queue.isEmpty()){
        int size = queue.size();
        List<Integer> level = new ArrayList<>();
        for(int i=0;i<size;i++){
          TreeNode cur = queue.poll();
          if(cur.left!=null) queue.add(cur.left);
          if(cur.right!=null) queue.add(cur.right);
          level.add(cur.val);
        }
        if(reverse){
          Collections.reverse(level);
        }
        res.add(level);

        reverse = !reverse;
      }
      return res;
    }
}
