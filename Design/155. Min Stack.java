Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

/*
solution1：https://leetcode.com/problems/min-stack/discuss/49010/Clean-6ms-Java-solution
如果interviewer不让用java的stack。自己用linkedlist写stack
思路跟solution2还是一样。
建一个node class储存：
 0. val 自己的value
 1. next node （实现正常stack的功能）
 2. min value （记录该node及已经入栈的node中的最小值）

整个linkedlist维护一个head，表示栈顶元素
push(x):
  1. 新建一个node：node.next =head；
  2. node.min = Min(head.min,自己的value）
  3. node = head

pop():
  1. 删除head就行 head.next成为新的head。 其他啥都不用干

top(): return head.value
getMin():return head.min
*/
class MinStack {

    private class Node{
      int val;
      int min;
      Node next;

      public Node(int val, int min, Node next){
        this.val = val;
        this.min = min;
        this.next = next;
      }
    }

    Node head;
    /** initialize your data structure here. */
    public MinStack() {
      head = null;
    }

    public void push(int x) {
      if(head==null){
        head = new Node(x, x, null);
      }else{
        head = new Node(x, Math.min(head.min, x), head);
      }
    }

    public void pop() {
      head = head.next;
    }

    public int top() {
      return head.val;
    }

    public int getMin() {
      return head.min;
    }
}


/* solution2：
用两个stack
一个像正常的stack一样
另一个keep track of the minimum
只有当 新push的number <= 现在的min时，才会push到这个stack上
只有当 pop的number == 现在的min, 才会从这个stack上pop
Test Cases 和 注意点
当一开始为空时的push
每一个push与现有min相等时也要push到stack2上
*/
class MinStack {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    /** initialize your data structure here. */
    public MinStack() {
      stack1 = new Stack<>();
      stack2 = new Stack<>();
    }

    public void push(int x) {
      stack1.push(x);
      if(stack2.isEmpty()){
        stack2.push(x);
      }else if(x<=stack2.peek()){
        stack2.push(x);
      }
    }

    public void pop() {
      //!!!大坑！！！！！
      //if(stack1.peek() == stack2.peek())  stack里是integer  不能用==
      if(stack1.peek().equals(stack2.peek())){
        stack2.pop();
      }
      stack1.pop();
    }

    public int top() {
      return stack1.peek();
    }

    public int getMin() {
      return stack2.isEmpty()? Integer.MAX_VALUE:stack2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
