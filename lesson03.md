# Linked List

Singly Linked List / Doubly Linked List


## Singly Linked List node
```java
public class SinglyLinkedList<T>{
  private int size = 0;
  private Node<T> headNode = null;
  private Node<T> tailNode = null;

  public class Node<T> {
      T data;
      Node next;
      public Node(T data, Node<T> next){
        this.data = data;
        this.next = data;
      }
  }
}
```

## Doubly Linked List node
```java
public class DoublyLinkedList<T>{
  private int size = 0;
  private Node<T> headNode = null;
  private Node<T> tailNode = null;

  public class Node<T> {
      T data;
      Node prev, next;
      public Node(T data, Node<T> prev, Node<T> next){
        this.data = data;
        this.prev = data;
        this.next = data;
      }
  }
}
```

操作	             Singly   Doubly
Access by index	   O(n)	    O(n)
Insert at head	   O(1)	    O(1)
Insert at tail	   O(1)	    O(1)
Delete head	       O(1)	    O(1)
Delete tail	       O(n)	    O(1)
Delete mid	       O(n)	    O(n)

* Double linked list's node has prev, so when it need to do delete at tail, it can directly change.
ex.
// delete
tail = tail.prev;
tail.next = null;
