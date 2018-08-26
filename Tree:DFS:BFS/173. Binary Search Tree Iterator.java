Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
in-order traversal
next(), hasNext() in O(1) and use O(h) memory: use stack to store O(h) nodes
solution: 将in-order traversal的两个while拆开
intialize时, root push to stack, root = root.left. until reach the left most node.

next(): pop node, 同时检查node是否有right， 如果有 递归处理node.right的左子树入栈
1. node = stack.pop();
2. see if node.right!=null,
    if not : push(node.right),
            while(node.left!=null) keep push node.left;

hasNext(): return stack.isEmpty();

*/
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
      if(root!=null){
        stack.push(root);
        while(root.left!=null){
          stack.push(root.left);
          root = root.left;
        }
      }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
      TreeNode node = stack.pop();
      if(node.right!=null){
        stack.push(node.right);
        TreeNode cur = node.right;
        while(cur.left!=null){
          stack.push(cur.left);
          cur = cur.left;
        }
      }
      return node.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
