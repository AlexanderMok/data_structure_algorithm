package algorithm.structure.graph;

import java.util.LinkedList;
import java.util.stream.IntStream;

import algorithm.structure.list.Bags;
import algorithm.structure.stack.Stack;

/**
 * The {@code DirectedGraph} class represents a directed graph of vertices named
 * 0 through <em>V</em> - 1.
 * <p>
 * This implementation uses an adjacency-lists representation, which is a
 * vertex-indexed array of {@link LinkedList} objects.
 * <p>
 * All operations take constant time (in the worst case) except iterating over
 * the vertices adjacent from a given vertex, which takes time proportional to
 * the number of such vertices.
 * 
 * @author Alex
 *
 */
public class DirectedGraph {
	// number of vertices
	private int vertices;
	// number of edges
	private int edges;
	// adjacent list
	private Bags<Integer>[] adjacent;
	// indegree of vertex
	private int[] indegree;

	public DirectedGraph(int vertices) {
		if (vertices < 0) {
			throw new IllegalArgumentException();
		}
		this.vertices = vertices;
		this.edges = 0;
		indegree = new int[vertices];
		adjacent = (Bags<Integer>[]) new Bags[vertices];
		IntStream.range(0, vertices).forEach(i -> {
			adjacent[i] = new Bags<Integer>();
		});
	}

	public DirectedGraph(DirectedGraph graph) {
		this(graph.vertices());
		this.edges = graph.edges;
		IntStream.range(0, graph.vertices()).forEach(v -> {
			this.indegree[v] = graph.indegree(v);
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<>();
			graph.adjacent[v].forEach(reverse::push);
			reverse.forEach(this.adjacent[v]::add);
		});
	}

	public int indegree(int vertex) {
		validateVertex(vertex);
		return indegree[vertex];
	}

	/**
	 * outdegree of a given vertex, which is the length of adjacent list
	 * 
	 * @param vertex
	 * @return
	 */
	public int outdegree(int vertex) {
		validateVertex(vertex);
		return adjacent[vertex].size();
	}

	/**
	 * return number of vertices
	 * 
	 * @return
	 */
	public int vertices() {
		return vertices;
	}

	/**
	 * return number of edges
	 * 
	 * @return
	 */
	public int edges() {
		return edges;
	}

	private void validateVertex(int vertex) {
		if (vertex < 0 || vertex >= vertices) {
			throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (vertices - 1));
		}
	}

	/**
	 * add a directed edge vertex_1 -> vertex_2
	 * 
	 * @param vertex_1
	 * @param vertex_2
	 */
	public void addEdge(int vertex_1, int vertex_2) {
		validateVertex(vertex_1);
		validateVertex(vertex_2);
		// directed graph, thus no vertex_2.add(vertex_1) like undirected graph
		adjacent[vertex_1].add(vertex_2);
		indegree[vertex_2]++;
		edges++;
	}

	/**
	 * return adjacent list of a given vertex {@code vertex}
	 * 
	 * @param vertex
	 * @return
	 */
	public Iterable<Integer> adjacent(int vertex) {
		return adjacent[vertex];
	}

	/**
	 * return a reversed version of the directed graph
	 * 
	 * @return
	 */
	public DirectedGraph reverse() {
		DirectedGraph reverse = new DirectedGraph(this.vertices);
		IntStream.range(0, this.vertices).forEach(v -> {
			adjacent(v).forEach(w -> {
				reverse.addEdge(w, v);
			});
		});
		return reverse;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(vertices + " vertices, " + edges + " edges. \n");
		for (int i = 0; i < vertices; i++) {
			s.append(i + " -> ");
			for (int w : adjacent[i]) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(8);
        System.out.println(graph);
		graph.addEdge(0, 3);
		graph.addEdge(0, 2);
		graph.addEdge(0, 7);
		graph.addEdge(1, 6);
		graph.addEdge(5, 7);
		System.out.println(graph);
		System.out.println(graph.reverse());
		
	}
}
