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
		marked[vertex] = true;
		count++;
		for (int w : graph.adjacent(vertex)) {
			if (!marked[w]) {
				dfs(graph, w);
			}
		}
	}

	public boolean isMarked(int vertex) {
		return marked[vertex];
	}

	public int count() {
		return count;
	}
}
