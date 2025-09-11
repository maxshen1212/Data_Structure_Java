import java.util.*;

public class lesson02 {
  public static void main(String[] args) {
    DynamicArray<Integer> arr = new DynamicArray<>();
  }

  public static class DynamicArray<T> {
    private T[] arr;
    private int length = 0;
    private int capacity = 0;

    public DynamicArray() {
      this(16);
    }

    public DynamicArray(int capacity) {
      this.capacity = capacity;
      arr = (T[]) new Object[capacity];
    }

    public int size() {
      return length;
    }

    public boolean isEmpty() {
      return size() == 0;
    }

    public void add(T elem) {
      if (length + 1 >= capacity) {
        if (capacity == 0) {
          capacity = 1;
        } else {
          capacity *= 2;
        }
        T[] newArr = (T[]) new Object[capacity];
        for (int i = 0; i < length; i++) {
          newArr[i] = arr[i];
        }
        arr = newArr;
      }
      arr[length++] = elem;
    }

    public T remove(int index) {
      if (index >= length && index < 0) {
        throw new IndexOutOfBoundsException();
      }
      T data = arr[index];
      T[] newArr = (T[]) new Object[capacity];
      for (int i = 0, j = 0; i < length; i++, j++) {
        if (i == index) {
          j--;
          continue;
        }
        newArr[j] = arr[i];
      }
      arr = newArr;
      length--;
      return data;
    }

    public boolean remove(Object obj) {
      for (int i = 0; i < length; i++) {
        if (arr[i].equals(obj)) {
          remove(i);
          return true;
        }
      }
      return false;
    }

    public int indexOf(Object obj) {
      for (int i = 0; i < length; i++) {
        if (arr[i].equals(obj)) {
          return i;
        }
      }
      return -1;
    }

    public boolean contains(Object obj) {
      int i = indexOf(obj);
      if (i == -1) {
        return false;
      }
      return true;
    }

    public String toString() {
      if (length == 0) {
        return "[]";
      }
      StringBuilder sb = new StringBuilder(length);
      sb.append("[");
      for (int i = 0; i < length; i++) {
        sb.append(arr[i] + ", ");
      }
      sb.append("]");
      return sb.toString();
    }
  }
}
