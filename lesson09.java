public class lesson09 {

  public static void main(String[] args) {
    SCMap<String, Integer> map = new SCMap<>(10);
    // System.out.println("add a");
    // map.add("a", 1);

    // System.out.println("add b");
    // map.add("b", 2);

    // System.out.println("find a");
    // System.out.println(map.find("a"));

    // System.out.println("delete a");
    // map.delete("a");

    for (int i = 0; i < 10; i++) {
      map.add("" + i, i);
      System.out.println(map.size);
      System.out.println(map.capacity);
    }

  }

  // this is a seperate chaning map
  public static class SCMap<K, V> {
    public class Node<K, V> {
      K key;
      V value;
      Node<K, V> next;

      Node(K key, V value) {
        this.value = value;
        this.key = key;
      }
    }

    int capacity;
    Node<K, V>[] buckets;
    double thresholdFactor = 0.7;
    int size = 0;

    SCMap(int capacity) {
      this.capacity = capacity;
      this.buckets = (Node<K, V>[]) new Node[capacity];
    }

    public void add(K key, V val) {
      // judge whether size will be larger than threshold
      if ((size + 1) / capacity >= thresholdFactor) {
        resize();
      }

      int index = hashFunc(key);
      Node<K, V> cur = buckets[index];

      if (key == null) {
        return;
      }

      // if key is existed, change the value
      while (cur != null) {
        if ((key == cur.key) || key.equals(cur.key)) {
          cur.value = val;
          return;
        }
        cur = cur.next;
      }

      Node<K, V> newNode = new Node<>(key, val);
      newNode.next = buckets[index];
      buckets[index] = newNode;
      size++;

    }

    public V find(K key) {

      int index = hashFunc(key);
      Node<K, V> cur = buckets[index];

      if (key == null || cur == null)
        return null;

      while (cur != null) {
        if (cur.key == key || key.equals(cur.key)) {
          return cur.value;
        }
        cur = cur.next;
      }

      return null;
    }

    public void delete(K key) {
      int index = hashFunc(key);
      Node<K, V> cur = buckets[index];
      Node<K, V> prev = null;
      System.out.println("delete step 1");

      if (key == null || cur == null)
        return;
      System.out.println("delete step 2");

      while (cur != null) {
        if (cur.key == key || key.equals(cur.key)) {
          if (prev == null) {
            cur.value = null;
            cur.key = null;
            buckets[index] = null;
            System.out.println("delete step 3.1");
            System.out.println(buckets[index]);

          } else {
            prev.next = cur.next;
            cur.value = null;
            cur.key = null;
            cur.next = null;
            cur = null;
            System.out.println("delete step 3.2");
            System.out.println(buckets[index]);
          }
          System.out.println("delete step 4");

          return;
        }
        prev = cur;
        cur = cur.next;
      }

    }

    public int hashFunc(K key) {
      String keyStr = (String) key;
      // Calculate hash value based on the user's attributes
      int sum = 0;
      for (int i = 0; i < keyStr.length(); i++) {
        sum += keyStr.charAt(i);
      }
      int hash = sum % capacity;

      return hash;
    }

    public void resize() {
      // size init
      size = 0;

      // capacity double
      capacity = capacity * 2;

      Node<K, V>[] newBuckets = (Node<K, V>[]) new Node[capacity];

      // rearrange all the position
      for (Node<K, V> elem : buckets) {
        while (elem != null) {
          int index = hashFunc(elem.key);
          elem.next = newBuckets[index];
          newBuckets[index] = elem;
          size++;
          elem = elem.next;
        }
      }

      // replace with new buckets;
      buckets = newBuckets;
    }
  }
}
