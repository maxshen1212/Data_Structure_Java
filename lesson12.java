public class lesson12 {
  public static void main(String[] args) {
    AVLTree<Integer> avl = new AVLTree<>();
  }

  public static class AVLTree<T extends Comparable<T>> {
    public class Node<T> {
      T value;
      int bf; // is short for balance factor
      int height;
      Node<T> left;
      Node<T> right;

      public Node(T value) {
        this.value = value;
      }
    }

    private Node<T> root;
    private int nodeCount = 0;

    public int height() {
      return root.height;
    }

    public boolean isEmpty() {
      return nodeCount == 0;
    }

    public boolean contains(T value) {
      return contains(root, value);
    }

    private boolean contains(Node<T> node, T value) {
      if (node == null) {
        return false;
      }
      int cmp = value.compareTo(node.value);
      if (cmp < 0) {
        return contains(node.left, value);
      } else if (cmp > 0) {
        return contains(node.right, value);
      }
      // means cmp == 0 & node.value == value
      return true;
    }

    public boolean insert(T value) {
      if (value == null) {
        return false;
      }
      if (!contains(value)) {
        root = insert(root, value);
        nodeCount++;
        return true;
      }
      return false;
    }

    private Node<T> insert(Node<T> node, T value) {
      if (node == null) {
        return new Node<T>(value);
      }
      int cmp = value.compareTo(node.value);
      if (cmp < 0) {
        node.left = insert(node.left, value);
      } else if (cmp > 0) {
        node.right = insert(node.right, value);
      }

      // for AVL tree
      // update bf & height
      update(node);
      // re-balance tree
      return balance(node);
    }

    public boolean remove(T value) {
      if (value == null) {
        return false;
      }
      if (!contains(value)) {
        root = remove(root, value);
        nodeCount--;
        return true;
      }
      return false;
    }

    private Node<T> remove(Node<T> node, T value) {
      if (node == null) {
        return null;
      }
      int cmp = value.compareTo(node.value);
      if (cmp < 0) {
        node.left = remove(node.left, value);
      } else if (cmp > 0) {
        node.right = remove(node.right, value);
      } else {
        if (node.left == null) {
          return node.right;
        } else if (node.right == null) {
          return node.left;
        } else {
          if (node.left.height > node.right.height) {
            T successorVal = findMax(node.left);
            node.value = successorVal;
            node.left = remove(node.left, successorVal);
          } else {
            T successorVal = findMin(node.right);
            node.value = successorVal;
            node.right = remove(node.right, successorVal);
          }
        }
      }
      update(node);
      return balance(node);
    }

    // updates nodes' height and bf
    public void update(Node<T> node) {
      int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
      int rightNodeHeight = (node.right == null) ? -1 : node.right.height;
      node.height = Math.max(leftNodeHeight, rightNodeHeight) + 1;
      node.bf = rightNodeHeight - leftNodeHeight;
    }

    // adjust the BBST tree
    public Node<T> balance(Node<T> node) {
      if (node.bf == -2) {
        if (node.left.bf <= 0) {
          return leftRightCase(node);
        } else {
          return leftLeftCase(node);
        }
      }
      if (node.bf == 2) {
        if (node.right.bf <= 0) {
          return rightLeftCase(node);
        } else {
          return rightRightCase(node);
        }
      }
      // 0, +1 and -1 are fine
      return node;
    }

    public Node<T> leftLeftCase(Node<T> node) {
      return rightRotation(node);
    }

    public Node<T> leftRightCase(Node<T> node) {
      node.left = leftRotation(node);
      return rightRotation(node);
    }

    public Node<T> rightRightCase(Node<T> node) {
      return leftRotation(node);
    }

    public Node<T> rightLeftCase(Node<T> node) {
      node.right = rightRotation(node);
      return leftRotation(node);
    }

    public Node<T> leftRotation(Node<T> node) {
      Node<T> newParent = node.right;
      node.right = newParent.left;
      newParent.left = node;
      update(node);
      update(newParent);
      return newParent;
    }

    public Node<T> rightRotation(Node<T> node) {
      Node<T> newParent = node.left;
      node.left = newParent.right;
      newParent.right = node;
      update(node);
      update(newParent);
      return newParent;
    }

    private T findMin(Node<T> node) {
      if (node.left == null) {
        return node.value;
      }
      return findMin(node.left);
      // while (node.left != null) {
      // node = node.left;
      // }
      // return node.value;
    }

    private T findMax(Node<T> node) {
      if (node.right == null) {
        return node.value;
      }
      return findMin(node.right);
      // while (node.right != null) {
      // node = node.right;
      // }
      // return node.value;
    }
  }
}
