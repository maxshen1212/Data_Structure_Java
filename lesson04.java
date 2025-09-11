import java.util.ArrayList;

public class lesson04 {
  public static void main(String[] args) {
    // Example usage of Linked List Stack (need space for linked list pointer)
    LinkedListStack<Integer> stack = new LinkedListStack<>();

    // Example usage of Dynamic Array Stack (don't need space for linked list
    // pointer)
    ArrayListStack<Integer> arrayListStack = new ArrayListStack<>(3);
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
      size--;
      return arr.remove(size);
    }

    public T peek() {
      return arr.get(size - 1);
    }
  }

  public static class LinkedListStack<T> {
    private Node<T> head;

    private static class Node<T> {
      T data;
      Node<T> next;

      Node(T data) {
        this.data = data;
        this.next = null;
      }
    }

    public LinkedListStack() {
      head = null;
    }

    public void push(T elem) {
      Node<T> newNode = new Node<>(elem);
      newNode.next = head;
      head = newNode;
    }

    public T pop() {
      if (head == null) {
        throw new IllegalStateException("Stack is empty");
      }
      T item = head.data;
      head = head.next;
      return item;
    }

    public T peek() {
      return head.data;
    }
  }
}
