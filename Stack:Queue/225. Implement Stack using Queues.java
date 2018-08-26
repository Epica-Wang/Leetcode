/*
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
poll() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You must use only standard operations of a queue -- which means only push to back, peek/poll from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no poll or top operations will be called on an empty stack).
*/

/* Solution 1: when you push an element,
choose one empty queue(whichever when both are empty) to add this element,
and then push all elements of the other queue into the chosen queue.
After that, the newest element is at the head of the chosen queue
so that whenever you want to do poll() or top(), you always get the newest element.
每次push的时候选一个空queueA push 进去，把不空的queueB倒到queueA 里去.
每次push结束一定有一个queue空 一个queue不空
*/

class MyStack {

    /** Initialize your data structure here. */
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    /** Push element x onto stack. */
    public void push(int x) {
      if(queue1.isEmpty()){
        queue1.add(x);
        while(!queue2.isEmpty()){
          queue1.add(queue2.poll());
        }
      }
      else{// queue2 is empty
        queue2.add(x);
        while(!queue1.isEmpty()){
          queue2.add(queue1.poll());
        }
      }

    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
      if(queue1.isEmpty())
        return queue2.poll();
      else
        return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
      if(queue1.isEmpty())
        return queue2.peek();
      else
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
      return queue1.isEmpty() && queue2.isEmpty();
    }
}

/* Solution 2:
Use two queues. Maintain a queue that always has one element that should be top/poll.
make queue_A to be the queue that always has one element ready to top/poll.
push: If queue_A is not empty, poll that ele from A to B, then push new ele to A.
      If queue_A is empty(only happens when A and B are both empty), push directly to A.
pop: since problem says all operations are valid, so no pop or top operations will be called on an empty stack.
  So A is not empty and has the ele ready to be polled. We just poll that ele. Then update A to get the next ele
  should be polled.  First move all eles from B to A, then move all eles except the last one from A back to B.
  Now A has the new ele ready to be polled for the next time.
  After each pop, we do such update.
top: just return A.peek()

empty: just need to check if A is empty.
*/
class MyStack {

    /** Initialize your data structure here. */
    Queue<Integer> queue_A = new LinkedList<>();   // make queue_A to be the queue that always has one element ready to top/poll
    Queue<Integer> queue_B = new LinkedList<>();

    /** Push element x onto stack. */
    public void push(int x) {
      if(queue_A.isEmpty())
        queue_A.add(x);
      else{
        queue_B.add(queue_A.poll());
        queue_A.add(x);
      }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
      // queue_A always has one element. After each poll from queue_A, extract the
      // next element ready to be polled from queue_B
      int poll_element = queue_A.poll();
      while(!queue_B.isEmpty()){
        queue_A.add(queue_B.poll());
      }
      for(int i = 1; i<queue_A.size();i++)
        queue_B.add(queue_A.poll());

      return poll_element;
    }


    /** Get the top element. */
    public int top() {
      return queue_A.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
      return queue_A.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.poll();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
