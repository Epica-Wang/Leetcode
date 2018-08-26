Given a binary tree, return the postorder traversal of its nodes values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Follow up: Recursive solution is trivial, could you do it iteratively?

/* iterative*/
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
    public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> res = new LinkedList<>();
      Stack<TreeNode> stack = new Stack<>();
      if(root==null) return res;
      stack.push(root);
      while(!stack.isEmpty()){
        TreeNode cur = stack.peek();
        if(cur.left==null && cur.right==null){
          res.add(cur.val);
          stack.pop();
        }
        if(cur.right!=null){
          stack.push(cur.right);
          cur.right=null;
        }
        if(cur.left!=null){
          stack.push(cur.left);
          cur.left = null;
        }
      }
      return res;

    }
}
