Given a binary tree, return the inorder traversal of its nodes values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

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
一种错误写法：会不停的重复访问已访问过的left:
例子:
     1
    /
  2
 /
6
会output: 6 2 6 1.
原因在于line46 pop了root之后，line48只在root.right！=null的时候修改了下一轮进入while的root，
而如果root.right = null的话下一轮进入while的还是line46 pop的root
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
      Stack<TreeNode> stack = new Stack<>();
      List<Integer> res = new LinkedList<>();
      if(root==null) return true;
      stack.push(root);
      while(!stack.isEmpty()){
        while(root.left!=null){
          stack.push(root.left);
          root = root.left;
        }
        root = stack.pop();
        res.add(root.val);
        if(root.right!=null){
          stack.push(root.right);
          root = root.right;
        }
      }
      return res;
    }
}
*/
 /*iterative 写法1：请对比写法2和上面的错误写法  思考这种为啥不会重复访问left指针*/
 class Solution {
     public List<Integer> inorderTraversal(TreeNode root) {
       List<Integer> res = new LinkedList<>();
       Stack<TreeNode> stack = new Stack<>();
       if(root==null) return res;
       while(root!=null||!stack.isEmpty()){
         while(root!=null){
           stack.push(root);
           root = root.left;
         }
         TreeNode cur = stack.pop();
         res.add(cur.val);
         root = cur.right;
         //新root设成cur.right重新进入while
         //若刚pop出去的cur右边是空的  就继续pop
         // 为啥不会重复访问已访问过的left指针？ ->会出现这种情况是因为root被赋值成了之前访问过的node
         // 因为有root=cur.right  再进入while的都是之前没访问过的node

       }
       return res;
     }
 }
 /*iterative第二种写法  把访问过的left指针设成null*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> res = new LinkedList<>();
      Stack<TreeNode> stack = new Stack<>();
      if(root==null) return res;
      stack.push(root);
      while(!stack.isEmpty()){
        TreeNode cur = stack.peek();
        if(cur.left!=null){
          stack.push(cur.left);
          cur.left=null;
          continue;
        }
        //若是到这个if的话说明cur.left已经是null了
        //这时可以pop current，同时记得把cur.right 入栈
        res.add(cur.val);
        stack.pop();
        if(cur.right!=null){
          stack.push(cur.right);
          cur.right = null;
        }
      }
    return res;
    }
}


/*recursive*/
class Solution {
    private List<Integer> res = new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
      helper(root, res);
      return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
      if(root == null) return;
      helper(root.left, res);
      res.add(root.val);
      helper(root.right, res);
    }
}
