public class lesson07 {
  public static void main(String[] args) {
    // assume we have 5 elements: 0, 1, 2, 3, 4
    // initially, they are all in different trees
    // 0, 1, 2, 3, 4 are their own parents
    UnionFind uf = new UnionFind(5);

    uf.union(0, 1);
    uf.union(1, 2);
    System.out.println(uf.isConnected(0, 2));

    uf.union(3, 4);
    System.out.println(uf.isConnected(0, 3));

    uf.union(2, 4);
    System.out.println(uf.isConnected(0, 4));

  }

  public static class UnionFind {
    // for union-find, we need two arrays:
    // 1. parent: to track the parent of each node
    private int[] parent;
    // 2. size: to track the size of each tree (for optimization)
    private int[] size;

    // initialization: create a union-find structure with n elements
    public UnionFind(int n) {
      parent = new int[n];
      size = new int[n];
      for (int i = 0; i < n; i++) {
        // each element is its own parent
        parent[i] = i;
        // the size of each tree is 1
        size[i] = 1;
      }
    }

    // find the root of the tree with path compression for efficiency
    public int find(int x) {
      if (parent[x] != x) {
        // path compression
        parent[x] = find(parent[x]);
      }
      return parent[x];
    }

    // union two trees
    public void union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);

      if (rootX != rootY) {
        // union by size, attach the smaller tree to the larger tree
        if (size[rootX] < size[rootY]) {
          parent[rootX] = rootY;
        } else if (size[rootX] > size[rootY]) {
          parent[rootY] = rootX;
        } else {
          parent[rootY] = rootX;
          // increase the size of the rootX tree
          size[rootX] += 1;
        }
      }
    }

    // check if two elements are in the same tree
    public boolean isConnected(int x, int y) {
      return find(x) == find(y);
    }
  }
}
