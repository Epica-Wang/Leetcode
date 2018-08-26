Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
1->2->3->4->5->NULL, m = 2, n = 4
m到n的反转就是普通的reverse linked list，同时记录长度就可以了
要保留原list里m的prev 和n的next
m~n反转完之后要把原m的prev.next 指向反转完的头；反转完的尾指向原n的next
*/
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
      if(head==null) return null;
      if(m==n) return head; // don't need to reverse
      //造一个dummy head 就不怕m=1的时候从头开始reverse，mPrev=null 不能写mPrev.next的情况了
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode mPrev = null;
      ListNode nNext = null;
      int count=0;
      //find m-1, make it mPrev
      if(m>=2){
        count++;
        while(count<m-1){
          head = head.next;
          count++;
        }
        mPrev=head;
      }else{ // m=1时，mPrev = dummy
        mPrev = dummy;
      }
      // reverse m to n
      ListNode mNode = mPrev.next; //mNode，是反转后的尾
      ListNode prev = mNode;
      count++; //count=m
      ListNode cur = prev.next;
      count++; // count = m+1
      while(count<=n){ //start count=m+1
        ListNode nextTemp = cur.next;
        cur.next = prev;
        prev = cur;
        cur = nextTemp;
        count++;
      }
      //after while, prev是反转后的头，mNode是反转后的尾
      nNext = cur;
      mPrev.next = prev;
      mNode.next = nNext;
      return dummy.next;
    }
}
