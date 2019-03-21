package algorithm.structure.graph;

/**
 * Depth first search(DFS)
 * 
 * @author Alex
 *
 */
public class DepthFirstSearch {
	private boolean[] marked;
	private int count;

	public DepthFirstSearch(UndirectedGraph graph, int s) {
		marked = new boolean[graph.vertices()];
		dfs(graph, s);
	}

	private void dfs(UndirectedGraph graph, int vertex) {
		validateVertex(vertex);
		marked[vertex] = true;
		count++;
		for (int w : graph.adjacent(vertex)) {
			if (!marked[w]) {
				dfs(graph, w);
			}
		}
	}

	public boolean isMarked(int vertex) {
		validateVertex(vertex);
		return marked[vertex];
	}

	public int count() {
		return count;
	}

	private void validateVertex(int vertex) {
		int v = marked.length;
		if (vertex < 0 || vertex >= v) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (v - 1));
		}
	}

	public static void main(String[] args) {
		UndirectedGraph graph = new UndirectedGraph(3);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		DepthFirstSearch search = new DepthFirstSearch(graph, 2);
		for (int v = 0; v < graph.vertices(); v++) {
			if (search.isMarked(v)) {
				System.out.print(v + " ");
			}
		}
		if (search.count != graph.vertices()) {
			System.out.println("NOT connected graph");
		} else {
			System.out.println("Connected graph");
		}
	}
}
