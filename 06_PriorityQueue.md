# Priority Queue

Output by priority

PQ is a ADS implementing by Min heap / Max heap

# Heap
Usually "heap" will be "Binary Heap", which is easy to use

Using hash map can help insert

```java
class heap<T extends Comparable<T>>{
  private final ArrayList<T> array = new ArrayList<>();

  public void offer(){
    bubbleUP();
  }

  public T poll(){
    swap(top,bottom);
    remove(bottom);
    sinkDown();
    return top;
  }

  public T peek(){
    return top;
  }

}
```