package algorithm.structure.graph;

public class DfsConnected {
	private boolean[] marked;
	// id of connected component containing v
	private int[] id;
    //number of vertices, index of this array represents connected components
	private int[] size;
	// number of connected components(In {a-b-c,d-e-f}, there are two)
	private int count;

	public DfsConnected(UndirectedGraph graph) {
		marked = new boolean[graph.vertices()];
		id = new int[graph.vertices()];
		size = new int[graph.vertices()];
		for (int v = 0; v < graph.vertices(); v++) {
			if (!marked[v]) {
				dfs(graph, v);
				count++;
			}
		}
	}

	private void dfs(UndirectedGraph graph, int v) {
		marked[v] = true;
		id[v] = count;
		size[count]++;
		for (int w : graph.adjacent(v)) {
			if (!marked[w]) {
				dfs(graph, w);
			}
		}
	}

	public boolean isConnected(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		return id[v] == id[w];
	}

	public int id(int v) {
		return id[v];
	}

	public int count() {
		return count;
	}

	private void validateVertex(int vertex) {
		int n = marked.length;
		if (vertex < 0 || vertex >= n) {
			throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (n - 1));
		}
	}

	public static void main(String[] args) {
		UndirectedGraph graph = new UndirectedGraph(8);
		graph.addEdge(0, 3);
		graph.addEdge(0, 2);
		graph.addEdge(0, 7);
		graph.addEdge(1, 3);
		graph.addEdge(3, 6);
		graph.addEdge(5, 7);

		DfsConnected connected = new DfsConnected(graph);
		System.out.println("Number of connected component: " + connected.count);
		for (int i = 0; i < connected.id.length; i++) {
			System.out.print(connected.id[i] + " ");
		}
		System.out.println(connected.isConnected(1, 4));
		for (int i = 0; i < connected.size.length; i++) {
			System.out.print(connected.size[i] + " ");
		}
	}

}
