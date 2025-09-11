# Unio Find (Disjoint Set)

Keeps track of elements which are split to one or more disjoint set.

Be used in:
1. Minimum spanning tree algorithm
2. Grid percolation
3. Network connectivity
4. Least common ancestor in trees
5. Image processing
6. Subset finding

## Use Kruskal's algroithm as an example:
Given a graph G = (V, E) we want to find a Minimum Spanning Tree in the graph (it may not be unique). A minimum spanning tree is a subset of the edges which connect all vertices in the graph with the minimal total edge cost.

## Steps:
1. sort edges by ascending edge weight.
2. walk through the sorted edges and look at the two nodes the edge belong to. if the nodes are already unified, we don't include the nodes, otherwise we unify the nodes.
3. terminate when every edge has been processed or all the vertices have been unfied.

## Create Union Find(array-based):
Condtruct a bijection between objects and integers [0,n]
1. Randonly assign a map between objects and integers
  map = {"A":0, "C:1", "B":2}
2. store union find info in an array
  arr=[0,1,2]
  Each index has a associated objedt(letter in this example)
3. Union
  Union(A,C)
  arr => [0,0,2]
  Union(C,B) (B is going to point to A because we know C is belong to A)
  arr => [0,0,0]

## Find Operation
To find which component a particular element belongs to find the root of that component by following the parent nodes until a self loop is reached (a node who's parent is itself)
## Union Operation
To unify two elements find which are the root nodes of each component and if the root nodes are different make one of the root nodes be the parent of the other (usualy will the big one considering about the height)
## Path Compression
initial:
7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1 -> 0

path compression:
7 -> 0
6 -> 0
5 -> 0
4 -> 0
3 -> 0
2 -> 0
1 -> 0