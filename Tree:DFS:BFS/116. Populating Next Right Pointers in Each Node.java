Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

1.You may only use constant extra space.
2.Recursive approach is fine, implicit stack space does not count as extra space for this problem.
3.You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
Example:

Given the following perfect binary tree,

     1
   /  \
  2    3
 / \  / \
4  5  6  7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL

/* 要求constant space。 tree是perfect.即除了leaf每个node都有左右孩子
难点在于要求constant space. 不然可以直接level order traversal的同时记录prev 修改next指针就行了
对每个root,做两件事：1.连接r.left和r.right  2. iteratively 连接r.left.right和r.right.left 
*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
      if(root==null) return; //meet leaf的下一层
      if(root.left==null|| root.right==null) return; // meet leaf

      //对每个root,做两件事：1.连接r.left和r.right  2. iteratively 连接r.left.right和r.right.left
      root.left.next = root.right;
      TreeLinkNode nodeL = root.left;
      TreeLinkNode nodeR = root.right;
      while(nodeL.right!=null && nodeR.left!=null){
        nodeL.right.next = nodeR.left;
        nodeL = nodeL.right;
        nodeR = nodeR.left;
      }
      connect(root.left);
      connect(root.right);
    }
}
