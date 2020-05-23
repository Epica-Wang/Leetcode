1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output: [1,2,3,7,8,11,12,9,10,4,5,6]
讲解不错 https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/solution/
本质上可以看成是tree DFS 的preorder traverse
child是left指针，next是right指针
如果一个node有left（child), 先访问left再访问right(next)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

/**
Solution 1: DFS Recursive
几个犯错的点:
-> while里的NULL pointer check
-> 不仅要修改next pointer，还要修改prev pointer
-> 不要忘记remove child pointer!
*/
class Solution {
    public Node flatten(Node head) {
      if (head == null) return head;
      Node cur = head;
      helper(cur);
      return head;
    }

    // return flatten后的the last node (not null) of current level （以head为头）
    public Node helper(Node head) {
      Node cur = head;
      Node last = new Node(0, null, cur, null);
      while (cur != null) {
        if (cur.child == null) {
          last = cur;
          cur = cur.next;
        } else { // cur.child != null 但可能 cur.next != null也可能cur.next == null
          Node originalNextOfCur = cur.next;
          Node lastNodeOfCurChildLevelList = helper(cur.child);
          // cur -> cur.child -> ... -> lastNodeOfCurChildLevelList -> originalNextOfCur
          cur.next = cur.child;
          cur.child.prev = cur;
          cur.child = null;
          lastNodeOfCurChildLevelList.next = originalNextOfCur;

          // 因为要访问originalNextOfCur的prev，所以得确保它不是null
          if (originalNextOfCur == null) {
            return lastNodeOfCurChildLevelList;
          }
          originalNextOfCur.prev = lastNodeOfCurChildLevelList;
          last = lastNodeOfCurChildLevelList;
          cur = originalNextOfCur;
        }
      }
      return last;
    }
}

/**
Solution 2: DFS Iteration using Stack
*/
class Solution {
    public Node flatten(Node head) {
      if (head == null) return head;
      Stack<Node> stack = new Stack<>();
      Node dummyHead = new Node(0, null, head, null);
      Node prev = dummyHead;
      Node cur = head;
      stack.push(cur);

      while(!stack.isEmpty()) {
        cur = stack.pop();
        if (cur.next != null) {
          stack.push(cur.next);
        }
        if (cur.child != null) {
          stack.push(cur.child);
          // DON'T FORGET TO REMOVE CHILD POINTER!!!
          cur.child = null;
        }
        prev.next = cur;
        cur.prev = prev;
        prev = cur;
      }
      // detach the dummy head from result
      head.prev = null;
      return head;
    }
}
