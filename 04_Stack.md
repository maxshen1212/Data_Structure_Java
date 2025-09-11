# Stack
Last In, First Out

Use Linked List(flexible capacity) / Array(know capacity) to implement

```java
public class Stack<T>{
  private ArrayList<T> list = new ArrayList<T>();

  public void push(T elem){
    list.addLast(elem);
  }
  public T pop(){
    return list.removeLast();
  }
  public T peek(){
    return list.peekLast();
  }
}
```