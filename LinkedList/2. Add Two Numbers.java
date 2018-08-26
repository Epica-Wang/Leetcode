/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      // 需要两个node：第一个node sumTwo 固定指向头部  第二个prev 随着iteration移动。 初始时prev指向sumTwo
      // sumTwo.next 实际上才是第一个valid node
      ListNode sumTwo = new ListNode(0);   //sumTwo is actually a dummyhead.
      ListNode prev = sumTwo;
      int carry = 0;
      while(l1!=null || l2 !=null){
        ListNode current = new ListNode(0);
        int x = (l1 == null)? 0:l1.val;
        int y = (l2 == null)? 0:l2.val;
        int sum = carry + x + y;
        carry = sum / 10;
        int valueOnThisDigit = sum %10;
        current.val = valueOnThisDigit;
        prev.next = current;
        prev = current;

        l1 = (l1 == null)? l1:l1.next;
        l2 = (l2 == null)? l2:l2.next;
      }
      if(carry !=0){  // extra carry at end may remain
        ListNode last = new ListNode(carry)
        prev.next = last;
      }
      return sumTwo.next;
      }
    }
