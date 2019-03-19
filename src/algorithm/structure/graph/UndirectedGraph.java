package algorithm.structure.graph;

import algorithm.structure.list.Bags;
import algorithm.structure.stack.Stack;

/**
 * Represent an undirected graph of vertices named 0 through V-1. Parallel edges
 * and self-loops are permitted.
 * <p>
 * This implementation uses an adjacency-lists representation, which is a
 * vertex-index array of list.
 * <p>
 * All operations take constant time (in the worst case) except iterating over
 * the vertices adjacent to a given vertex, which takes time proportional to the
 * number of such vertices.
 * 
 * @author Alex
 *
 */
public class UndirectedGraph {
	private int vertices;
	private int edges;
	private Bags<Integer>[] adj;

	public UndirectedGraph(int vertices) {
		this.vertices = vertices;
		this.edges = 0;
		adj = (Bags<Integer>[]) new Bags[vertices];
		for (int i = 0; i < vertices; i++) {
			adj[i] = new Bags<>();
		}
	}

	/**
	 * Initializes a new graph that is a deep copy of {@code undirectedGraph}.
	 * 
	 * @param undirectedGraph
	 */
	public UndirectedGraph(UndirectedGraph undirectedGraph) {
		this(undirectedGraph.vertices);
		this.edges = undirectedGraph.edges;
		for (int v = 0; v < undirectedGraph.vertices; v++) {
			Stack<Integer> reverse = new Stack<>();
			for (int w : undirectedGraph.adj[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				this.adj[w].add(w);
			}
		}
	}

	private void validateVertex(int vertex) {
		if (vertex < 0 || vertex >= vertices) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * number of vertices
	 * 
	 * @return number of vertices
	 */
	public int vertices() {
		return vertices;
	}

	/**
	 * number of edges
	 * 
	 * @return number of edges
	 */
	public int edges() {
		return edges;
	}

	/**
	 * Adds the undirected edge vertex_1-vertex_2 to this graph.
	 * 
	 * @param vertex_1
	 * @param vertex_2
	 */
	public void addEdge(int vertex_1, int vertex_2) {
		validateVertex(vertex_1);
		validateVertex(vertex_2);
		adj[vertex_1].add(vertex_2);
		adj[vertex_2].add(vertex_1);
		edges++;
	}

	/**
	 * Returns the vertices adjacent to a given {@code vertex}.
	 * 
	 * @param vertex
	 * @return
	 */
	public Iterable<Integer> adjacent(int vertex) {
		validateVertex(vertex);
		return adj[vertex];
	}

	/**
	 * Returns the degree of {@code vertex}
	 * 
	 * @param vertex
	 * @return
	 */
	public int degree(int vertex) {
		validateVertex(vertex);
		return adj[vertex].size();
	}

	public int maxDegree(UndirectedGraph graph) {
		int max = 0;
		for (int i = 0; i < graph.vertices(); i++) {
			int deg = degree(i);
			if (deg > max) {
				max = deg;
			}
		}
		return max;
	}

	public int avgDegree(UndirectedGraph undirectedGraph) {
		return 2 * undirectedGraph.edges() / undirectedGraph.vertices();
	}
	
	public int uumberOfSelfLoops(UndirectedGraph undirectedGraph) {
		int count = 0;
		for (int i = 0; i < undirectedGraph.vertices();i++) {
			for (int j : undirectedGraph.adjacent(i)) {
				if (i == j) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(vertices + " vertices, " + edges + " edges. \n");
		for (int i = 0; i < vertices; i++) {
			s.append(i + " -> ");
			for (int w : adj[i]) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {
		UndirectedGraph graph = new UndirectedGraph(8);
		System.out.println(graph);
		graph.addEdge(0, 3);
		graph.addEdge(0, 2);
		graph.addEdge(0, 7);
		graph.addEdge(1, 6);
		graph.addEdge(5, 7);
		System.out.println(graph);

	}
}
