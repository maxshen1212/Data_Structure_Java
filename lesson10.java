import java.util.*;

public class lesson10 {

  public static void main(String[] args) {
    FennwickTree fw = new FennwickTree(new long[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });
  }

  public static class FennwickTree {
    private long[] tree;

    public FennwickTree(int size) {
      // Step 1. 初始化 Fennwick Tree array
      // 1-base
      this.tree = new long[size + 1];
    }

    public FennwickTree(long[] values) {
      // Step 1. 初始化 Fennwick Tree array
      // 1-base
      this.tree = values.clone();
      for (int i = 1; i < tree.length; i++) {
        int j = i + lsb(i);
        if (j < tree.length) {
          tree[j] += tree[i];
        }
      }
    }

    private int lsb(int i) {
      return i & -i;
    }

    public long prefixSum(int i) {
      long sum = 0;
      while (i != 0) {
        sum += tree[i];
        i -= lsb(i);
        // i &= ~lsb(i);
      }
      return sum;
    }

    public long sum(int i, int j) {
      if (i > j) {
        throw new IllegalArgumentException("j need to be greater than i");
      }
      return prefixSum(j) - prefixSum(i - 1);
    }

    public void add(int i, long k) {
      while (i < tree.length) {
        tree[i] += k;
        i += lsb(i);
      }
    }

    public void set(int i, long k) {
      // get the value of i
      long value = sum(i, i);
      // add the difference between k and value
      add(i, k - value);
    }
  }
}