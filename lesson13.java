import java.util.*;

public class lesson13 {
  public static void main(String[] args) {
    SCHashSet<Integer> scSet = new SCHashSet<>();
  }

  public static class SCHashSet<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private List<E>[] buckets;
    private int size;

    public SCHashSet() {
      this.buckets = (List<E>[]) new LinkedList[DEFAULT_CAPACITY];
      this.size = 0;
    }

    private int getIndex(Object key) {
      return (key.hashCode() & 0x7fffffff) % buckets.length;
    }

    public void add(E val) {
      if (contains(val))
        return;
      int index = getIndex(val);
      if (buckets[index] == null) {
        buckets[index] = new LinkedList<>();
      }
      buckets[index].add(val);
      size++;

      if ((double) size / buckets.length >= LOAD_FACTOR) {
        resize();
      }
    }

    public void remove(E val) {
      int index = getIndex(val);
      if (buckets[index] == null)
        return;
      if (buckets[index].remove(val)) {
        size--;
      }
    }

    public boolean contains(E val) {
      int index = getIndex(val);
      if (buckets[index] == null)
        return false;
      return buckets[index].contains(val);
    }

    public int size() {
      return size;
    }

    private void resize() {
      List<E>[] oldBuckets = buckets;
      buckets = (List<E>[]) new LinkedList[oldBuckets.length * 2];
      size = 0;

      for (List<E> bucket : oldBuckets) {
        if (bucket != null) {
          for (E val : bucket) {
            add(val);
          }
        }
      }
    }
  }
}
