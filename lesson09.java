import java.util.ArrayList;
import java.util.LinkedList;

public class lesson09 {

  public static void main(String[] args) {
  }

  public static class SeperateChainingMap {
    private int capacity;

    SeperateChainingMap(int capacity) {
      this.capacity = capacity;
    }

    public void add(String key, int val) {
    }

    public void find() {
    }

    public void delete() {
    }

    private int hashFunc(String key, int sizeOfMap) {

      // Calculate hash value based on the user's attributes
      int sum = 0;
      for (int i = 0; i < key.length(); i++) {
        sum += key.charAt(i);
      }
      int hash = sum % sizeOfMap;

      return hash;
    }
  }

}
