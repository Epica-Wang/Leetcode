Given a linked list, swap every two adjacent nodes and return its head.

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
Note:

Your algorithm should use only constant extra space.
You may not modify the values in the lists nodes, only nodes itself may be changed.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
      if(head==null || head.next==null) return head;
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode prev = dummy;
      ListNode cur1 = head;
      ListNode cur2 = null;
      ListNode cur2Next = null;
      while(cur1!=null && cur1.next!=null){
        cur2 = cur1.next;
        cur2Next = cur2.next;
        cur2.next = cur1;
        prev.next = cur2;
        cur1.next = cur2Next;
        prev = cur1;
        cur1 = cur2Next;
      }
      return dummy.next;
    }
}
