Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?

/** https://zhengyang2015.gitbooks.io/lintcode/linked_list_cycle_ii_103.html
1.use two pointer in 141 to determine if exists a cycle.

dummy head->A->B->C->D->E
                  \     |
                      F
cycle 开始的地方为C

2. 把slow放回dummy head。slow,fast同时出发都一步一步走，再次相遇的点就是cycle的start。
Proof:
->设一共x个node不在环中，即第x+1个node为cycle start。设环共有m个node。
->slow，fast都从dummy出发，设t步后相遇在cycle中的第K个node。 则有：

  slow走了：t = x + c1*m + k
  fast走了：2t = x + c2*m + k （c1，c2为unkown constant，并不确定走了cycle多少圈）
则可以得到： x + k = (c2-2c1)*m。 x+k为cycle长度的整数倍。
说明slow，fast相遇后，fast/slow再连续的（不跳next）走x步就走到环的起始点了。
如何get到x步？
把slow放回dummy head。slow,fast同时出发都一步一步走，再次相遇的点就是cycle的start（都走了x步）。

 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
      // first see if there is a cycle
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode slow = dummy;
      ListNode fast = dummy;

      while(fast.next!=null && fast.next.next!=null){
        slow = slow.next;
        fast = fast.next.next;
        if(slow==fast) break;
      }

      if(fast.next==null||fast.next.next==null) return null;

      slow = dummy;
      while(slow!=fast){
        slow = slow.next;
        fast = fast.next;
      }
      return slow;
    }
}
