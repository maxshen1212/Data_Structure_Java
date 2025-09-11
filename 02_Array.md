# Array
Memory address is continuos

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
  public T get(int index){
    return this.arr[index];
  }
  public void set(int index, T val){
    this.arr[index] = val;
  }
  public T remove(){
    
  }
  public void add(T elem){
    if(length+1>=capacity){
      if(capacity==0){
        capacity = 1;
      }else{
        capacity *=2;
      }
    };
    T[] newArr = (T[]) new Object[capacity];
    // ...copy the val from old to new one
    this.arr = newArr;
    this.arr[len++]=elem;
  }
}
```