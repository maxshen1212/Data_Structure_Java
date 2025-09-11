import java.util.*;

public class lesson06 {
    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();
    }

    public static class MinHeap<T extends Comparable<T>> {
        private final ArrayList<T> array = new ArrayList<>();

        public MinHeap() {
        }

        // add at the bottom + bubble up → O(log n)
        public void offer(T x) {
            array.add(x);
            heapifyUp(array.size() - 1);
        }

        // peek the minimum value → O(1)
        public T peek() {
            if (array.isEmpty())
                throw new NoSuchElementException();
            return array.get(0);
        }

        // remove the minimum value and return it → O(log n)
        public T poll() {
            if (array.isEmpty())
                throw new NoSuchElementException();
            T top = array.get(0);
            // swap the top with the last element
            swap(0, array.size() - 1);
            // remove the last element (which was the top)
            array.remove(array.size() - 1);
            if (!array.isEmpty())
                // sink down the new top to its right place
                heapifyDown(0);
            return top;
        }

        // get the index of parent
        private static int parent(int i) {
            return (i - 1) / 2;
        }

        // get the index of left child
        private static int left(int i) {
            return 2 * i + 1;
        }

        // get the index of right child
        private static int right(int i) {
            return 2 * i + 2;
        }

        // bubble up, when adding a new element is less than its parent
        private void heapifyUp(int i) {
            // if is not the root
            while (i > 0) {
                // get the index of parent
                int parent = parent(i);
                // if child >= parent, it is in the right place and break
                if (array.get(i).compareTo(array.get(parent)) >= 0)
                    break;
                // else swap child and parent
                swap(i, parent);
                // i = parent and continue to bubble up till find the right place
                i = parent;
            }
        }

        // sink down, when the parent is greater than its children
        private void heapifyDown(int i) {
            int size = array.size();
            while (true) {
                // get the indexes of left and right children
                int l = left(i), r = right(i), smallest = i;
                // if lef is less than smallest, set smallest to left
                if (l < size && array.get(l).compareTo(array.get(smallest)) < 0)
                    smallest = l;
                System.out.println("l: " + l);
                // if right is less than smallest, set smallest to right
                if (r < size && array.get(r).compareTo(array.get(smallest)) < 0)
                    smallest = r;
                System.out.println("r: " + r);

                // if smallest is still i, it is in the right place
                if (smallest == i) {
                    System.out.println("smallest i: " + i);
                    break;
                }
                // else swap i and smallest
                swap(i, smallest);

                i = smallest;
                System.out.println("smallest: " + i);

            }
        }

        // change the position of two elements in the array
        private void swap(int i, int j) {
            T tmp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, tmp);
        }
    }
}