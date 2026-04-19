
# Graph Algorithms and Hash Table Analysis (Java)

## Overview
This project implements two fundamental computer science algorithms:

1. **Closed Hash Table Implementation (Linear Probing)**
2. **Dijkstra’s Shortest Path Algorithm**

Together, these programs demonstrate core concepts in **data structures, algorithm design, and performance analysis**, including hashing behavior, clustering effects, and shortest path optimization in weighted graphs.

---

## Features

### Closed Hash Table Analysis
Implements a hash table using **closed hashing with linear probing** and evaluates structural properties of the resulting table.

Key functionality:
- Custom hash function based on classical algorithm design methodology
- Collision resolution via linear probing
- Statistical analysis of hash table structure
- Duplicate detection handling

Computed metrics include:
- Load factor of the table
- Longest contiguous sequence of empty slots
- Longest cluster of occupied slots
- Hash index containing the greatest number of distinct entries
- Maximum displacement distance from original hash index

These measurements provide insight into **hash distribution quality** and **collision behavior**.

---

### Dijkstra’s Shortest Path Algorithm
Implements Dijkstra’s algorithm to compute the shortest path between nodes in a weighted graph.

Key functionality:
- Computes minimum distance between two selected nodes
- Reconstructs optimal path sequence
- Uses adjacency matrix representation
- Demonstrates greedy algorithm optimization strategy

This implementation illustrates foundational graph traversal techniques widely used in:
- navigation systems
- network routing protocols
- pathfinding in games
- logistics optimization

---

## Algorithms and Concepts Demonstrated

### Data Structures
- Hash tables
- adjacency matrices
- sets and maps

### Algorithms
- linear probing collision resolution
- greedy shortest-path optimization
- hashing and key distribution
- graph traversal

### Performance Considerations
- clustering effects in hash tables
- load factor impact on lookup efficiency
- algorithmic complexity analysis
- tradeoffs between memory usage and runtime performance

---

## Project Structure

src/
├── ClosedHashing.java
├── DijkstraAlgorithm.java

---

## How to Run

### Compile
javac ClosedHashing.java
javac DijkstraAlgorithm.java

### Run Closed Hash Table Analysis
java ClosedHashing

Program inputs:
- text file containing words
- desired table size

Example outputs:
- load factor
- clustering statistics
- displacement analysis

---

### Run Dijkstra Shortest Path Solver
java DijkstraAlgorithm

Program inputs:
- start node
- destination node

Example outputs:
- shortest distance
- sequence of nodes in optimal path

---

## Example Applications

Hash table analysis:
- dictionary implementations
- indexing systems
- compiler symbol tables
- database lookup optimization

Shortest path algorithms:
- GPS routing
- network packet routing
- game AI navigation
- resource allocation optimization

---

## Technical Summary

| Component | Technique |
|----------|----------|
| Hash Table | closed hashing with linear probing |
| Collision Resolution | sequential probing |
| Graph Algorithm | Dijkstra’s shortest path |
| Graph Representation | adjacency matrix |
| Complexity | O(n) probing behavior, O(V²) shortest path |

---

## Author
Daniel Yin
