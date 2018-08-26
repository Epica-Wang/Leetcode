实现操作：push(); pop(); size(); isEmpty();
/*
1. 用LinkedList
*/
The following Stack interface can be assigned any object that
implements this interface no matter the underlying implementation
uses linked list or array based implementation of stack in Java.
Create a text file Stack.java, insert the following code in that and save it.

/* Stack.java */
public interface Stack <Item>{
  Item pop(); // return the top item and removes it from stack
  void push(Item item); // adds an item to the stack
  boolean isEmpty(); // returns true if stack is empty, false otherwise
  int size();  // returns the number of items in stack right now
}
/* LinkedStack.java */
class LinkedStack <Item> implements Stack <Item>{
  private Node head; //the first node
  private int size; // number of items

  //nested class to define node
  private class Node{
    Item item;
    Node next;
  }

  //Zero argument constructor
  public LinkedStack(){
    head = null;
    size = 0;
  }

  public boolean isEmpty(){
    return (size == 0);
  }

  //Remove item from the beginning of the list.
  public Item pop(){
    Item item = head.item;
    head = head.next;
    size--;
    return item;
  }

  //Add item to the beginning of the list.
  public void push(Item item){
    Node oldHead = head;
    head = new Node();
    head.item = item;
    head.next = oldHead;
    size++;
  }

  public int size(){
    return size;
  }
}
