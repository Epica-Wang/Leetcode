Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?


/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 /*
 思想： two pointer. fast & slow.
 如果no cycle: fast最终visit null; return false
 有cycle: 例子：(F->C)
    A->B->C->D->E
          \     |
              F
  cycle 开始的地方为C， 当slow进入cycle即visit到C时，fast肯定指向cycle中的某个结点。
  并且fast永远不会是null，slow也永远不会是null。此时变成了一个追及问题，slow和fast都在cycle
  里跑，fast总会追上slow。 到二者相遇时，退出 return 存在cycle
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
      if(head==null) return false;
      ListNode slow = head;
      ListNode fast = head;
      /* 注意while停止的条件 (没有cycle的时候)
      如果p!=null且p.next = null, 那访问p.next.next会throw exception.
      即使是想check p.next.next==null 也会报错
      所以不能只写while(fast.next.next!=null)
      不用管slow.next指向null的问题  因为no cycle时fast肯定比slow先到null
      */
      while(fast.next!=null && fast.next.next!=null){
        // 确保fast.next!=null的时候  才能check fast.next.next
        // 如果fast.next 已经是null  fast指向最后一个node了  可以直接退出了
        // 如果fast.next not null, fast.next.next =null, fast指向倒数第二个node，也可以直接退出了
        // 注意：只写while(fast.next!=null)也不行   在fast.next not null, fast.next.next =null, fast指向倒数第二个node
        // 的情况下，进入while后fast = fast.next.next 为null   下次判断while时，fast.next非法了
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast){
          return true;
        }
      }
      return false;
    }
}
