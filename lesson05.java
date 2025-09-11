public class lesson05 {

  public static void main(String[] args) {

    // Example usage of ArrayQueue
    ArrayQueue<Integer> queue = new ArrayQueue<>(5);

    // Example usage of LinkedListQueue
    LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
  }

  public static class ArrayQueue<T> {
    private T[] data;
    private int front;
    private int rear;
    private int size;

    public ArrayQueue(int capacity) {
      data = (T[]) new Object[capacity];
      front = 0;
      rear = 0;
      size = 0;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public boolean isFull() {
      return size == data.length;
    }

    public void offer(T item) {
      if (isFull()) {
        throw new IllegalStateException("Queue is full");
      }
      data[rear] = item;
      rear = rear + 1;
      size++;
    }

    public T poll() {
      if (isEmpty()) {
        throw new IllegalStateException("Queue is empty");
      }
      T item = data[front];
      data[front] = null;
      front = front + 1;
      size--;
      return item;
    }

    public static String toString(Object[] data) {
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      for (int i = 0; i < data.length; i++) {
        sb.append(data[i]);
        if (i < data.length - 1) {
          sb.append(", ");
        }
      }
      sb.append("]");
      return sb.toString();
    }
  }

  public static class LinkedListQueue<T> {

    private static class Node<T> {
      T data;
      Node<T> next;

      Node(T data) {
        this.data = data;
        this.next = null;
      }
    }

    private Node<T> head;
    private Node<T> tail;

    public LinkedListQueue() {
      head = null;
      tail = null;
    }

    public boolean isEmpty() {
      return head == null;
    }

    public void offer(T item) {
      Node<T> newNode = new Node<>(item);
      if (tail != null) {
        tail.next = newNode;
      }
      tail = newNode;
      if (head == null) {
        head = newNode;
      }
    }

    public T poll() {
      if (isEmpty()) {
        throw new IllegalStateException("Queue is empty");
      }
      T item = head.data;
      head = head.next;
      if (head == null) {
        tail = null;
      }
      return item;
    }
  }
}
