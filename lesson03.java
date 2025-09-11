import java.util.*;

public class lesson03 {
  public static void main(String[] args) {
    DoublyLinkedList<Integer> dl = new DoublyLinkedList<>();
  }

  public static class DoublyLinkedList<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private static class Node<T> {
      T data;
      Node<T> prev, next;

      public Node(T data, Node<T> prev, Node<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
      }
    }

    public int size() {
      return size;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public void add(T elem) {
      addLast(elem);
    }

    public void addFirst(T elem) {
      if (isEmpty()) {
        head = new Node(elem, null, null);
        tail = head;
      } else {
        head.prev = new Node(elem, null, head);
        head = head.prev;
      }
      size++;
    }

    public void addLast(T elem) {
      if (isEmpty()) {
        head = new Node(elem, null, null);
        tail = head;
      } else {
        tail.next = new Node(elem, tail, null);
        tail = tail.next;
      }
      size++;
    }

    public T peekFirst() {
      if (head == null) {
        return null;
      }
      return head.data;
    }

    public T peekLast() {
      if (tail == null) {
        return null;
      }
      return tail.data;
    }

    public T removeFirst() {
      if (isEmpty()) {
        return null;
      }
      T data = head.data;
      head = head.next;
      if (isEmpty()) {
        tail = null;
      }
      head.prev = null;
      size--;
      return data;
    }

    public T removeLast() {
      if (isEmpty()) {
        return null;
      }
      T data = tail.data;
      tail = tail.prev;
      if (isEmpty()) {
        head = null;
      }
      tail.next = null;
      size--;
      return data;
    }

    private T remove(Node<T> node) {
      if (node.prev == null) {
        return removeFirst();
      }
      if (node.next == null) {
        return removeLast();
      }
      T data = node.data;
      node.next.prev = node.prev;
      node.prev.next = node.next;

      node.data = null;
      node = node.prev = node.next = null;
      size--;
      return data;
    }

    public T ramoveAt(int index) {
      Node<T> trav;
      int i;
      if (index < size / 2) {
        for (i = 0, trav = head; i != index; i++) {
          trav = trav.next;
        }
      } else {
        for (i = size - 1, trav = tail; i != index; i--) {
          trav = trav.prev;
        }
      }
      return remove(trav);
    }

    public boolean remove(Object obj) {
      Node<T> trav;
      if (obj == null) {
        for (trav = head; trav != null; trav = trav.next) {
          if (trav.data == null) {
            remove(trav);
            return true;
          }
        }
      } else {
        for (trav = head; trav != null; trav = trav.next) {
          if (trav.data.equals(obj)) {
            remove(trav);
            return true;
          }
        }
      }
      return false;
    }

    public int indexOf(Object obj) {
      int index = 0;
      Node<T> trav;
      if (obj == null) {
        for (trav = head; trav != null; trav = trav.next) {
          if (trav.data == null) {
            return index;
          }
          index++;
        }
      } else {
        for (trav = head; trav != null; trav = trav.next) {
          if (trav.data.equals(obj)) {
            return index;
          }
          index++;
        }
      }
      return -1;
    }

    public boolean contains(Object obj) {
      return indexOf(obj) != -1;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      Node<T> trav = head;
      while (trav != null) {
        sb.append(trav.data + ", ");
        trav = trav.next;
      }
      sb.append("]");
      return sb.toString();
    }
  }
}
