public class lesson09 {

  public static void main(String[] args) {
    SCMap<String, Integer> map = new SCMap<>(5);
    map.add("a", 1);
    map.add("b", 2);
    map.add("c", 3);
    map.add("d", 4);
    map.add("e", 4);
    // System.out.println(map.find("d"));
    // System.out.println(map.find("c"));
    // System.out.println(map.find("b"));
    // System.out.println(map.find("a"));
    // map.add("e", 5);
    // map.add("f", 6);
    // System.out.println(map.find("e"));
    // System.out.println(map.find("f"));
    map.delete("a");
    map.delete("a");
    // System.out.println(map.find("a"));

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
        next = null;
      }
    }

    int capacity;
    Node<K, V>[] buckets;

    SCMap(int capacity) {
      this.capacity = capacity;
      this.buckets = (Node<K, V>[]) new Node[capacity];
    }

    public void add(K key, V val) {
      int index = hashFunc(key, capacity);
      Node<K, V> cur = buckets[index];

      if (key == null) {
        return;
      }
      // if key is existed, change the value
      else if (cur != null) {
        while (cur.next != null) {
          if (cur.key == key || cur.key.equals(key)) {
            cur.value = val;
            return;
          }
          cur = cur.next;
        }
      }

      Node<K, V> newNode = new Node<>(key, val);
      newNode.next = buckets[index];
      buckets[index] = newNode;
    }

    public V find(K key) {

      int index = hashFunc(key, capacity);

      for (Node<K, V> cur = buckets[index]; cur != null; cur = cur.next) {
        if ((key == cur.key) || (key != null && key.equals(cur.key)))
          return cur.value;
      }
      return null;
    }

    public void delete(K key) {
      int index = hashFunc(key, capacity);
      Node<K, V> cur = buckets[index];

      if (key == null || cur == null)
        return;

      do {
        if (cur.key == key || key.equals(cur.key)) {
          cur.value = null;
          cur.key = null;
          Node<K, V> tmp = cur.next;
          cur = null;
          cur = tmp;
          return;
        }
      } while (cur.next != null);
    }

    public int hashFunc(K key, int sizeOfMap) {
      String keyStr = (String) key;
      // Calculate hash value based on the user's attributes
      int sum = 0;
      for (int i = 0; i < keyStr.length(); i++) {
        sum += keyStr.charAt(i);
      }
      int hash = sum % sizeOfMap;

      return hash;
    }

  }
}
