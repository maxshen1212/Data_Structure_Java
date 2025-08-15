import java.util.ArrayList;

public class lesson04 {
  public static void main(String[] args) {
    // Example usage of Linked List Stack (need space for linked list pointer)
    LinkedListStack<Integer> stack = new LinkedListStack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.pop();
    stack.pop();
    stack.pop();

    // Example usage of Dynamic Array Stack (don't need space for linked list
    // pointer)
    ArrayListStack<Integer> arrayListStack = new ArrayListStack<>(3);
    arrayListStack.push(1);
    arrayListStack.push(2);
    arrayListStack.push(3);
    arrayListStack.pop();
    arrayListStack.pop();
    arrayListStack.pop();

  }

  public static class ArrayListStack<T> {
    private ArrayList<T> arr = new ArrayList<T>();
    private int size = 0;

    public ArrayListStack(int capacity) {
      arr = new ArrayList<>(capacity);
    }

    public ArrayListStack() {
      arr.add(null);
    }

    public void push(T elem) {
      arr.add(elem);
      size++;
    }

    public T pop() {
      System.out.println("pop value: " + arr.get(size - 1));
      size--;
      return arr.remove(size);
    }

    public T peek() {
      return arr.get(size - 1);
    }
  }

  public static class LinkedListStack<T> {
    private SNode<T> head;

    private static class SNode<T> {
      T data;
      SNode<T> next;

      SNode(T data) {
        this.data = data;
        this.next = null;
      }
    }

    public LinkedListStack() {
      head = null;
    }

    public void push(T elem) {
      SNode<T> newNode = new SNode<>(elem);
      newNode.next = head;
      head = newNode;
    }

    public T pop() {
      if (head == null) {
        throw new IllegalStateException("Stack is empty");
      }
      T item = head.data;
      head = head.next;
      System.out.println("pop value: " + item);
      return item;
    }
  }
}
