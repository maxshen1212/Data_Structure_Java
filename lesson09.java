public class lesson09 {

  public static void main(String[] args) {
    // Example usage of Seperate Chaining
    SCMap<String, Integer> scmap = new SCMap<>(10);

    // Example usage of Open Addressing
    OAMap<String, Integer> oamap = new OAMap<>(16);

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
    static final double THRESHOLD_FACTOR = 0.7;
    int size = 0;

    public SCMap(int capacity) {
      this.capacity = capacity;
      this.buckets = (Node<K, V>[]) new Node[capacity];
    }

    public void add(K key, V val) {
      // judge whether size will be larger than threshold
      if ((double) (size + 1) / capacity >= THRESHOLD_FACTOR) {
        resize();
      }
      if (key == null) {
        return;
      }

      int index = hashFunc(key);
      Node<K, V> cur = buckets[index];

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

  // this is a open addressing map
  public static class OAMap<K, V> {
    int capacity = 16;
    int size = 0;
    static final double THRESHOLD_FACTOR = 0.5;
    K[] keyArr;
    V[] valArr;
    final K tombstone = (K) new Object();

    public OAMap(int capacity) {
      this.capacity = Math.max(this.capacity, capacity);
      keyArr = (K[]) new Object[this.capacity];
      valArr = (V[]) new Object[this.capacity];
    }

    private int P(int x) {
      return (x * x + x) / 2;
    }

    public void put(K key, V val) {
      if (key == null || val == null) {
        throw new Error("Key and Value cannot be null");
      }
      if ((double) size / capacity >= THRESHOLD_FACTOR) {
        resize();
      }

      int x = 0;
      // & 0x7FFFFFFF 轉負數為正數
      int index = (key.hashCode() & 0x7FFFFFFF) % capacity;
      while (true) {
        if (keyArr[index] == null) {
          keyArr[index] = key;
          valArr[index] = val;
          size++;
          return;
        }
        if (keyArr[index] == tombstone) {
          keyArr[index] = key;
          valArr[index] = val;
          size++;
          return;
        } else if (keyArr[index].equals(key)) {
          valArr[index] = val;
          return;
        }
        x++;
        index = (index + P(x)) % capacity;
      }
    }

    public V get(K key) {
      int x = 0, tombIndex = -1;
      if (key == null) {
        throw new Error("Key cannot be null");
      }
      int index = (key.hashCode() & 0x7FFFFFFF) % capacity;
      while (keyArr[index] != null) {
        if (keyArr[index] == tombstone && tombIndex == -1) {
          tombIndex = index;
        } else if (keyArr[index].equals(key) && tombIndex != -1) {
          V oldVal = valArr[index];
          keyArr[tombIndex] = keyArr[index];
          valArr[tombIndex] = oldVal;
          keyArr[index] = tombstone;
          valArr[index] = null;

          return oldVal;
        } else if (keyArr[index].equals(key)) {
          return valArr[index];
        }

        x++;
        index = ((index + P(x)) % capacity);
      }
      System.out.println("This key is not existed.");
      return null;
    }

    public void remove(K key) {
      if (key == null) {
        throw new Error("Key cannot be null");
      }
      int x = 0;
      int index = (key.hashCode() & 0x7FFFFFFF) % capacity;
      while (keyArr[index] != null) {
        if (keyArr[index].equals(key)) {
          keyArr[index] = tombstone;
          valArr[index] = null;
          size--;
          return;
        }
        x++;
        index = ((index + P(x)) % capacity);
      }
      System.out.println("This key is not existed.");
    }

    public void resize() {
      capacity *= 2;
      size = 0;
      K[] newKeyArr = (K[]) new Object[capacity];
      V[] newValArr = (V[]) new Object[capacity];
      K[] oldKeyArr = keyArr;
      V[] oldValArr = valArr;
      keyArr = newKeyArr;
      valArr = newValArr;

      for (int i = 0; i < oldKeyArr.length; i++) {
        if (oldKeyArr[i] != null && oldKeyArr[i] != tombstone) {
          put(oldKeyArr[i], oldValArr[i]);
        }
        oldKeyArr[i] = null;
        oldValArr[i] = null;
      }
    }
  }
}
