/*
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).

*/

/* Solution: use 2 stacks.
stack1 only save pushed elements.
stack2 saves whole elements.
But stack2 only get updated (i.e stack1 pop all its elements to stack2) when stack2
is empty and pop() or peek()is called.
pop() and peek() will pop up all ele in stack1 and push them to stack2.

check isEmpty: check both stack1 and stack2, may exists new elements in stack1
but haven't called pop() or peek()
*/

class MyQueue {

    /** Initialize your data structure here. */

    Stack<Integer> stack1  = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();


    /** Push element x to the back of queue. */
    public void push(int x) {
      stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
      if(stack2.isEmpty()){
        while(!stack1.isEmpty()){
          stack2.push(stack1.pop());
        }
    }
      return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
      if(stack2.isEmpty()){
        while(!stack1.isEmpty()){
          stack2.push(stack1.pop());
        }
    }
      return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
      return stack1.isEmpty()&&stack2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
