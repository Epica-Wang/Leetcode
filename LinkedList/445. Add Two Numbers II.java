You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*不reverse linkedlist的写法：
分别用两个stack存list
两个stack都不空时，pop元素相加，new一个新node
从后向前建result list，即new node插在上一个node之前
*/
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      if(l1==null && l2==null) return null;

      Stack<Integer> stack1 =  new Stack<>();
      Stack<Integer> stack2 = new Stack<>();

      while(l1!=null){
        stack1.push(l1.val);
        l1 = l1.next;
      }
      while(l2!=null){
        stack2.push(l2.val);
        l2 = l2.next;
      }
      ListNode later = null;
      boolean carry = false;
      int value;
      while(stack1.peek()!=null && stack2.peek()!=null){
        value = carry? stack1.peek()+stack2.peek()+1 : stack1.peek()+stack2.peek();
        carry = value/10 >=1 ? true : false;
        value = value%10;
        ListNode cur = new ListNode(value);
        cur.next = later;
        later = cur;

        stack1.pop();
        stack2.pop();
      }
      while(stack1.peek()!=null){
        value = carry? stack1.peek()+1 : stack1.peek();
        carry = value/10 >=1 ? true : false;
        value = value%10;
        ListNode cur = new ListNode(value);
        cur.next = later;
        later = cur;
        stack1.pop();
      }
      while(stack2.peek()!=null){
        value = carry? stack2.peek()+1 : stack2.peek();
        carry = value/10 >=1 ? true : false;
        value = value%10;
        ListNode cur = new ListNode(value);
        cur.next = later;
        later = cur;
        stack2.pop();
      }

      //处理最后一个node  判断是否要进位
      if(carry){
        ListNode cur = new ListNode(1);
        cur.next = later;
        later = cur;
      }
      return later;
    }
}
