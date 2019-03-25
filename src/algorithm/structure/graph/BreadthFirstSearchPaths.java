package algorithm.structure.graph;

import java.util.stream.IntStream;

import algorithm.structure.queue.Queue;
import algorithm.structure.stack.Stack;

/**
 * The {@code BreadthFirstSearchPaths} class represents a data type for finding
 * shortest paths (number of edges) from a source vertex <em>s</em> (or a set of
 * source vertices) to every other vertex in an undirected graph.
 * <p>
 * 
 * @author Alex
 *
 */
public class BreadthFirstSearchPaths {
	private boolean[] visisted;
	private int[] edgeTo;
	private int[] distTo;

	public BreadthFirstSearchPaths(UndirectedGraph graph, int src) {
		visisted = new boolean[graph.vertices()];
		edgeTo = new int[graph.vertices()];
		distTo = new int[graph.vertices()];
		validateVertex(src);
		bfs(graph, src);
	}

	public BreadthFirstSearchPaths(UndirectedGraph graph, Iterable<Integer> sources) {
		visisted = new boolean[graph.vertices()];
		edgeTo = new int[graph.vertices()];
		IntStream.range(0, graph.vertices()).forEach(v -> {
			distTo[v] = -1;
		});
	}
	

	/**
	 * breadth-first search from a single source
	 * 
	 * @param graph
	 * @param src
	 */
	private void bfs(UndirectedGraph graph, int src) {
		Queue<Integer> queue = new Queue<>();
		distTo[src] = 0;
		visisted[src] = true;
		queue.enqueue(src);
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			//visisted[v] = true;
			for (int w : graph.adjacent(v)) {
				if (!visisted[w]) {
					// record this adjacent vertex has edge with v
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					// search all at the same time, thus visiting in a loop
					// could visit outside the loop
					visisted[w] = true;
					queue.enqueue(w);
				}
			}
		}
	}

	/**
	 * breadth-first search from multiple sources
	 * 
	 * @param graph
	 * @param sources
	 */
	private void bfs(UndirectedGraph graph, Iterable<Integer> sources) {
		Queue<Integer> queue = new Queue<>();
		sources.forEach(src -> {
			visisted[src] = true;
			distTo[src] = 0;
			queue.enqueue(src);
		});

		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			graph.adjacent(v).forEach(w -> {
				if (!visisted[w]) {
					visisted[w] = true;
					edgeTo[w] = v;
					distTo[v] = distTo[v] + 1;
					queue.enqueue(w);
				}
			});
		}
	}

	private void validateVertex(int vertex) {
		int n = visisted.length;
		if (vertex < 0 || vertex >= n) {
			throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (n - 1));
		}
	}

	public boolean hasPathTo(int vertex) {
		validateVertex(vertex);
		return visisted[vertex];
	}

	public int distTo(int vertex) {
		validateVertex(vertex);
		return distTo(vertex);
	}

	public Iterable<Integer> shortestPathTo(int vertex) {
		validateVertex(vertex);
		if (!hasPathTo(vertex)) {
			return null;
		}
		Stack<Integer> path = new Stack<>();
		int x;
		for (x = vertex; distTo[x] != 0; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(x);
		return path;
	}

	public static void main(String[] args) {
		UndirectedGraph graph = new UndirectedGraph(8);
		graph.addEdge(0, 3);
		graph.addEdge(0, 2);
		graph.addEdge(0, 7);
		graph.addEdge(1, 3);
		graph.addEdge(3, 6);
		graph.addEdge(5, 7);
		BreadthFirstSearchPaths bfs = new BreadthFirstSearchPaths(graph, 6);
		boolean b = bfs.hasPathTo(1);
		System.out.println(b);
		System.out.println(bfs.shortestPathTo(5));
		for (int i = 0; i < bfs.distTo.length; i++) {
			System.out.print(bfs.distTo[i] + " ");
		}

	}

}
