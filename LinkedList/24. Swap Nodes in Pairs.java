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

/*
11.12重写练习  这版本思路清晰一点 每次while只用current node 是否null来判断
cur.next cur.next.next是不是null丢进while里
*/
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur1 = head;

        while (cur1 != null) {
            if (cur1.next != null) {
                ListNode cur2 = cur1.next; // it's not null
                ListNode nextCur1 = cur2.next; // may be null

                // now swap cur1 and cur2
                prev.next = cur2;
                cur2.next = cur1;
                cur1.next = nextCur1;

                // now move to next cur node
                // remember to first set prev to cur1, then update cur1 to nextCur1
                prev = cur1;
                cur1 = nextCur1;

            } else {
                // means cur1.next == null. odd number of nodes
                break;
            }
        }
        return dummy.next;
    }
}

// old version
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
