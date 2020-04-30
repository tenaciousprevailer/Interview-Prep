package ent;

import java.util.LinkedList;

public class Graph {
	public int V;
	public LinkedList<Integer>[] adjListArray;

	// constructor
	public Graph(int V) {
		this.V = V;

		// define the size of array as
		// number of vertices
		adjListArray = new LinkedList[V];

		// Create a new list for each vertex
		// such that adjacent nodes can be stored
		for (int i = 0; i < V; i++) {
			adjListArray[i] = new LinkedList<>();
		}
	}
	
    // Adds an edge to an undirected graph
    public void addEdge(int src, int dest)
    {
        // Add an edge from src to dest. 
        adjListArray[src].add(dest);
    }
    
    // Adds an edge to an undirected graph
    public void addBiDirectedEdge(int src, int dest)
    {
        // Add an edge from src to dest. 
        adjListArray[src].addFirst(dest);
         
        // Since graph is undirected, add an edge from dest
        // to src also
        adjListArray[dest].addFirst(src);
    }
}