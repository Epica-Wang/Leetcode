Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?


/*one pass解法：1->2->3->4->5->null
remove 倒数第n个 即remove正数第len-n+1个
即找到正数第len-n个 修改其指针
slow,fast开始都指向head  每次都走一步
fast pointer先一步步走到正数第n时，slow从头开始走
当fast走到尾的的时候，slow走了len-n步
此时可以删除
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode slow = dummy;
      ListNode fast = dummy;

      for(int i=0;i<n;i++){
        fast = fast.next;
      } //fast is at n

      while(fast.next!=null){
        slow = slow.next;
        fast = fast.next;
      }
      slow.next = slow.next.next;
      return dummy.next;
    }
}
