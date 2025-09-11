import java.util.*;

public class lesson08 {
  public static void main(String[] args) {
    // Example usage of BinarySearchTree
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
  }

  public static class BinarySearchTree<T extends Comparable<T>> {
    private class Node {
      T data;
      Node left, right;

      public Node(T data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
      }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
      this.root = null;
      this.size = 0;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public boolean add(T value) {
      if (contains(value)) {
        return false;
      }
      root = add(root, value);
      size++;
      return true;
    }

    private Node add(Node node, T value) {
      if (node == null) {
        node = new Node(value, null, null);
      } else {
        if (value.compareTo(node.data) < 0) {
          node.left = add(node.left, value);
        } else if (value.compareTo(node.data) > 0) {
          node.right = add(node.right, value);
        }
      }
      return node;
    }

    public boolean contains(T value) {
      return contains(root, value);
    }

    private boolean contains(Node node, T value) {
      if (node == null) {
        return false;
      }
      if (value.compareTo(node.data) < 0) {
        return contains(node.left, value);
      } else if (value.compareTo(node.data) > 0) {
        return contains(node.right, value);
      } else {
        return true;
      }
    }

    public boolean remove(T value) {
      if (!contains(value)) {
        return false;
      }
      root = remove(root, value);
      size--;
      return true;
    }

    private Node remove(Node node, T value) {
      if (node == null) {
        return null;
      }
      int cmp = value.compareTo(node.data);
      // The value is smaller than the current node's value
      if (cmp < 0) {
        node.left = remove(node.left, value);
      }
      // The value is greater than the current node's value
      else if (cmp > 0) {
        node.right = remove(node.right, value);
      }
      // The value is equal to the current node's value
      else {
        // Node with only one child or no child
        if (node.left == null) {
          Node rightChild = node.right;
          node.data = null;
          node = null;
          return rightChild;
        } else if (node.right == null) {
          Node leftChild = node.left;
          node.data = null;
          node = null;
          return leftChild;
        } else {
          // Node with two children:
          // Find the leftmost node in the right subtree (smallest in the right subtree)
          Node successor = getMinNode(node.right);
          // Swap the value
          node.data = successor.data;
          // Remove the successor in the right subtree
          node.right = remove(node.right, successor.data);

          // If u get the rightmost node in the left subtree (greatest in the left
          // subtree)
          // Node successor = getMaxNode(node.left);
          // node.data = successor.data;
          // node.left = remove(node.left, successor.data);
        }
      }
      return node;
    }

    // The smallest value
    private Node getMinNode(Node node) {
      // Find the leftmost node (smallest value)
      Node cur = node;
      while (cur.left != null) {
        cur = cur.left;
      }
      return cur;
    }

    // The greatest value
    private Node getMaxNode(Node node) {
      // Find the rightmost node (greatest value)
      Node cur = node;
      while (cur.right != null) {
        cur = cur.right;
      }
      return cur;
    }

    public int height() {
      return height(root);
    }

    private int height(Node node) {
      // If the node is null, return -1 (height of an empty tree)
      if (node == null) {
        return -1;
      }
      int leftHeight = height(node.left);
      int rightHeight = height(node.right);
      return Math.max(leftHeight, rightHeight) + 1;
    }

    private enum TraversalType {
      IN_ORDER,
      PRE_ORDER,
      POST_ORDER,
      LEVEL_ORDER
    }

    // Inorder traversal will print the values in ascending order
    private void inOrderTraversal(Node node) {
      if (node != null) {
        inOrderTraversal(node.left);
        System.out.print(node.data + ", ");
        inOrderTraversal(node.right);
      }
    }

    private void preOrderTraversal(Node node) {
      if (node != null) {
        System.out.print(node.data + ", ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
      }
    }

    private void postOrderTraversal(Node node) {
      if (node != null) {
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.data + ", ");
      }
    }

    private void levelOrderTraversal(Node node) {
      if (root == null) {
        return;
      }
      Queue<Node> queue = new LinkedList<>();
      queue.add(root);
      while (!queue.isEmpty()) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
          Node current = queue.poll();
          System.out.print(current.data + " ");

          if (current.left != null) {
            queue.add(current.left);
          }
          if (current.right != null) {
            queue.add(current.right);
          }
        }
        System.out.println();
      }
    }

    public void traverse(TraversalType type) {
      switch (type) {
        case IN_ORDER:
          inOrderTraversal(root);
          break;
        case PRE_ORDER:
          preOrderTraversal(root);
          break;
        case POST_ORDER:
          postOrderTraversal(root);
          break;
        case LEVEL_ORDER:
          levelOrderTraversal(root);
          break;
        default:
          throw new IllegalArgumentException("Unknown traversal type: " + type);
      }
    }

  }
}
