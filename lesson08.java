import java.util.*;

public class lesson08 {
  public static void main(String[] args) {

    // Example usage of BinarySearchTree
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    bst.add(5);
    bst.add(3);
    bst.add(7);
    bst.add(2);
    bst.add(4);
    bst.add(6);
    bst.add(8);

    System.out.println("In-order traversal:");
    bst.traverse(BinarySearchTree.TraversalType.LEVEL_ORDER);
    System.out.println("Height of the tree: " + bst.height());
  }

  public static class BinarySearchTree<T extends Comparable<T>> {
    private class Node {
      T value;
      Node left;
      Node right;

      public Node(T value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
      }
    }

    private Node root;
    private int size;

    public boolean isEmpty() {
      return size == 0;
    }

    public int getSize() {
      return size;
    }

    public void clear() {
      root = null;
      size = 0;
    }

    public BinarySearchTree() {
      this.root = null;
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
        return new Node(value, null, null);
      }
      if (value.compareTo(node.value) < 0) {
        node.left = add(node.left, value);
      } else if (value.compareTo(node.value) > 0) {
        node.right = add(node.right, value);
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
      if (value.compareTo(node.value) < 0) {
        return contains(node.left, value);
      } else if (value.compareTo(node.value) > 0) {
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
      // The value is smaller than the current node's value
      if (value.compareTo(node.value) < 0) {
        node.left = remove(node.left, value);
      }
      // The value is greater than the current node's value
      else if (value.compareTo(node.value) > 0) {
        node.right = remove(node.right, value);
      }
      // The value is equal to the current node's value
      else {
        // Node with only one child or no child
        if (node.left == null) {
          return node.right;
        } else if (node.right == null) {
          return node.left;
        }
        // Node with two children:
        // Get the leftmost node in the right subtree (smallest in the right subtree)
        Node successor = getMinNode(node.right);
        // Swap the value
        node.value = successor.value;
        // Remove the successor in the right subtree
        node.right = remove(node.right, successor.value);

        // If u get the rightmost node in the left subtree (greatest in the left
        // subtree)
        // Node successor = getMaxNode(node.left);
        // node.value = successor.value;
        // node.left = remove(node.left, successor.value);

      }
      return node;
    }

    // The smallest value
    private Node getMinNode(Node node) {
      // Find the leftmost node (smallest value)
      while (node.left != null) {
        node = node.left;
      }
      return node;
    }

    // The greatest value
    private Node getMaxNode(Node node) {
      // Find the rightmost node (greatest value)
      while (node.right != null) {
        node = node.right;
      }
      return node;
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
        System.out.print(node.value + ", ");
        inOrderTraversal(node.right);
      }
    }

    private void preOrderTraversal(Node node) {
      if (node != null) {
        System.out.print(node.value + ", ");
        inOrderTraversal(node.left);
        inOrderTraversal(node.right);
      }
    }

    private void postOrderTraversal(Node node) {
      if (node != null) {
        inOrderTraversal(node.left);
        inOrderTraversal(node.right);
        System.out.print(node.value + ", ");
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
          System.out.print(current.value + " ");

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
