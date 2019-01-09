Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input:
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6

/*
当然可以traverse一遍，O(N)时间
如何利用complete的性质？
->找到从哪棵子树开始不是全部填满的了
注意：一棵complete tree，其左右子树也为complete
如果tree的高度为h, 则left subtree高度一定为h-1：
-> 若right subtree的高度也为h-1：则可知left subtree是perfect binary tree， 左子树有2^h个node 加入到count中
  right subtree的最后一层没有填满，将root置为root.right， recursive解决
-> 若right subtree高度为h-2：则可知right subtree是perfect， 右子树有2^(h-1)个node
  left subtree最后一层没填满，将root置为root.left
*/

/*
time complexity:
每次砍掉一半的树，countNodes一共被call(logN)次
每个countNodes里计算height 需要logN
total: O(logN ^2)
*/
class Solution {
    public int countNodes(TreeNode root) {
      if (root == null) return 0;
      int h = height(root);
      //int left = height(root.left); 并不需要计算
      int right = height(root.right);

      if (right == h-1) {
        return 1 + (int)Math.pow(2, h) - 1 + countNodes(root.right);
      }else {
        return 1 + (int)Math.pow(2, h-1) - 1 + countNodes(root.left);
      }
    }

    public int height(TreeNode root){
      if (root == null) return 0;
      int h = 0;
      while(root.left != null) {
        h++;
        root = root.left;
      }
      return h;
    }
}
