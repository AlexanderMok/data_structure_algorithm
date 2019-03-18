package algorithm.structure.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represent an undirected graph of vertices named 0 through V-1. Parallel edges
 * are not permitted.
 * <p>
 * This implementation uses an adjacency-matrix representation, which is a 2D
 * array.
 * <p>
 * 
 * @author Alex
 *
 */
public class UndirectedGraphMatrix {
	private int vertices;
	private int edges;
	private boolean[][] adj;

	public UndirectedGraphMatrix(int vertices) {
		this.vertices = vertices;
		this.edges = 0;
		adj = new boolean[vertices][vertices];
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
		if (!adj[vertex_1][vertex_2]) {
			edges++;
		}
		adj[vertex_1][vertex_2] = true;
		adj[vertex_2][vertex_1] = true;
	}

	/**
	 * Returns the degree of {@code vertex}
	 * 
	 * @param vertex
	 * @return
	 */
	public int degree(int vertex) {
		int deg = 0;
		for (int v = 0; v < vertices; v++) {
			if (adj[vertex][v]) {
				deg++;
			}
		}
		return deg;
	}

	/**
	 * Returns the vertices adjacent to a given {@code vertex}.
	 * 
	 * @param vertex
	 * @return
	 */
	public Iterable<Integer> adjacent(int vertex) {
		
		return new AdjIterator(vertex);
	}

	private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
		private int v;
		private int w = 0;

		public AdjIterator(int v) {
			this.v = v;
		}

		@Override
		public boolean hasNext() {
			while (w < vertices) {
				if (adj[v][w]) {
					return true;
				}
				w++;
			}
			return false;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return w++;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Iterator<Integer> iterator() {
			return this;
		}
	}

}
