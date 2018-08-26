Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

Given the following binary tree,

     1
   /  \
  2    3
 / \    \
4   5    7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL


public class Solution {
  public void connect(TreeLinkNode root) {
      if (root == null) return;
      TreeLinkNode curP = root;  //current parent
      TreeLinkNode nextDummyHead = new TreeLinkNode(0);  // next level dummy head.
      TreeLinkNode p = nextDummyHead;
      while (curP != null) {
          if (curP.left != null) {
              p.next = curP.left;
              p = p.next;
          }
          if (curP.right != null) {
              p.next = curP.right;
              p = p.next;
          }
          if (curP.next != null) {
              curP = curP.next;
          }
          else { //curP is null. should move to next leve. reset nextDummyHead.next and p
              curP = nextDummyHead.next;
              nextDummyHead.next = null;
              p = nextDummyHead;
          }
      }
  }
}


/*
相比116，是普通的树，只能用iterative写法
level order traversal,利用上一层已经建好的next指针 来给current level建立next。
root为current的parent，root=root.next;
要注意的是这一层finish之后，怎么找下一层开始的node在哪儿，即下一层最左边的node在哪儿。
搞个dummy，使得dummy.next指向下一层开始的node
每当开始新一层traversal的时候，头一件事就是先找到它下一层的left most
*/

public class Solution {
    public void connect(TreeLinkNode root) {
      /*
      TreeLinkNode dummy = new TreeLinkNode(0);
      TreeLinkNode cur = dummy;
      */
      while(root!=null){  //here root means leftMost node of this level
        /*
        应该把dummy和cur 的创建写在第一个while里.
        否则当reach leaf节点的时候,即第二个while里root没有左右孩子的时候
        cur.next 即dummy.next一直不会被更新，下一次root=dummy.next还是指向这一层的leftmost而不是下一层 死循环
        */
        TreeLinkNode dummy = new TreeLinkNode(0);
        TreeLinkNode cur = dummy;
        while(root!=null){ // start traverse this level(this level is the next level of root)
          if(root.left!=null){
            cur.next = root.left;
            cur = cur.next;  //move cur to root.left
          }
          if(root.right!=null){
            cur.next = root.right;
            cur = cur.next;  //move cur to root.right
          }
          root =  root.next; //move root to next of this level
        }
        root = dummy.next; // this level finish. move root to leftMost of next level
      }
    }
}
