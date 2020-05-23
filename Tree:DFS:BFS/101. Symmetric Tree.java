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

/**
iterative:
除去最开始的root不用跟任何其他node比较value之外，任何时候我们都是在compare两个node。
如果当前比较的两个node A, B value相等，接下来需要比较的是(A.left 与 B.right), 以及(A.right与B.left)
*/
class Solution {
    public boolean isSymmetric(TreeNode root) {
      //单独处理root
        if (root == null) return true;
        if (root.left == null && root.right ==null) return true;
        if (root.left == null || root.right ==null) return false;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode n1 = q.poll();
            TreeNode n2 = q.poll();
            if (n1 == null && n2 == null) continue;
            if (n1 == null || n2 == null) return false;
            if (n1.val != n2.val) return false;
            q.add(n1.left);
            q.add(n2.right);

            q.add(n1.right);
            q.add(n2.left);
        }
        return true;
    }
}
