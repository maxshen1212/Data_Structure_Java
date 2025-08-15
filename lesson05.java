public class lesson05 {

  public static void main(String[] args) {

    // Example usage of ArrayQueue
    ArrayQueue<Integer> queue = new ArrayQueue<>(5);
    queue.offer(1);
    queue.offer(2);
    queue.offer(3);
    queue.offer(4);
    queue.offer(5);
    queue.poll();
    queue.poll();
    queue.poll();
    queue.poll();
    queue.poll();

    // Example usage of LinkedListQueue
    LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
    linkedListQueue.offer(1);
    linkedListQueue.offer(2);
    linkedListQueue.offer(3);
    linkedListQueue.poll();
    linkedListQueue.poll();
    linkedListQueue.poll();
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
      System.out.println(ArrayQueue.toString(data));
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
      System.out.println("size: " + size);
      System.out.println("poll value: " + item);
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

    private static class SNode<T> {
      T data;
      SNode<T> next;

      SNode(T data) {
        this.data = data;
        this.next = null;
      }
    }

    private SNode<T> head;
    private SNode<T> tail;

    public LinkedListQueue() {
      head = null;
      tail = null;
    }

    public boolean isEmpty() {
      return head == null;
    }

    public void offer(T item) {
      SNode<T> newNode = new SNode<>(item);
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
      System.out.println("poll value: " + item);
      return item;
    }
  }
}
