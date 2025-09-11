# Linked List

Singly Linked List / Doubly Linked List
Head: the first node
Tail: the last node
Pointer: reference to others
Node: contains data

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
        this.prev = prev;
        this.next = next;
      }
  }
}
```

操作	             Singly   Doubly
Search	           O(n)	    O(n)
Insert at head	   O(1)	    O(1)
Insert at tail	   O(1)	    O(1)
Remove head	       O(1)	    O(1)
Remove tail	       O(n)	    O(1)
Remove mid	       O(n)	    O(n)

* Doubly linked list can access prev, so when it need to do delete at tail, it can directly change.

```java
// Remove
Node tail = tail.prev;
tail.next = null;
```