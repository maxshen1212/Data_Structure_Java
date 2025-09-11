# Queue
First In, First Out

Use Linked List(don't know capacity and need to be flexible) / Circular Array(know the need of capacity) to implement

```java
public class Queue<T>{
  private java.util.LinkedList <T> list = new java.util.LinkedList<T>();

  public void offer(T elem){
    list.addLast(elem);
  }
  public T poll(){
    return list.removeFirst();
  }
  public T peek(){
    return list.peekFirst();
  }
}
```