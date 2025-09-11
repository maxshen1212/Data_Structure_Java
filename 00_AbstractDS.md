# Introduciton
Data Structures / Abstract Data Structures

## Data Structures

### Array (Foundations)
Fixed size (Static)
Resizable (Dynamic, e.g., Java ArrayList)

### Linked List (Foundations)
Singly Linked List/Doubly Linked List

* These two are the foundation. Almost all advanced data structures are built on top of them.

### Tree (Advanced)
Binary Tree, BST, AVL, Heap
Usually implemented with Linked Nodes
Heap is implemented with Array (because it’s a Complete Binary Tree)

### Graph (Advanced)
Adajacent list, matrix

### Hash Table (Advanced)
Essentially Array + Hash Function
Collision handling: Linked List (chaining) or Array (probing)

## Abstract Data Structures

### List
index to point the value
add(), remove(), get(), isEmpty(), size()

### Stack
LIFO (Last In, First Out)
push(), pop(), peek()

### Queue
FIFO (First In, First Out)
offer(), poll(), peek()

### Deque (Double-Ended Queue)
Insert/remove from both ends
offerFirst(), offerLast(), pollFirst(), pollLast(), peek()

### Set
each value is unique
add(), remove(), contains()

### Map
key:val pair
put(), remove(), containsKey(), entrySey()

## Summary
Abstract Data Types
 ├── List
 ├── Stack, Queue, Deque
 ├── Set, Map
Concrete Data Structures
 ├── Array
 ├── Linked List
 ├── Tree (Binary Tree, Heap, Trie)
 ├── Graph
 └── Hash Table