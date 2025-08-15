# Array

Static array / Dynamic array

## static array
  => fixed capacity
ex. int[] staticArray = new int[3];

### Time Complaxity
Access by index O( 1 ) // address is continuos
Search by value O( n )
insert x
delete x

## dynamic array
  => appendable capacity (double the capacity)
  => usually implement based on static array
ex. Arraylist<Integer> dynamicArray = new Arraylist<>();

### Time Complaxity
Access by index O( 1 ) // address is continuos
Search by value O( n )
insert O( n ) => move others
delete O( n ) => move others

```java
public class DynamicArray<T> {
  private T[] arr;
  private int length = 0;
  private int capacity = 0;

  public DynamicArray(int capacity) {
    this.capacity = capacity;
    this.arr = (T[]) new Object[capacity];
  }
}
```