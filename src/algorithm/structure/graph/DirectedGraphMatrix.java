package algorithm.structure.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class DirectedGraphMatrix {
	private int vertices;
	private int edges;
	private boolean[][] adj;

	public DirectedGraphMatrix(int vertices) {
		this.vertices = vertices;
		this.edges = 0;
		this.adj = new boolean[vertices][vertices];
	}

	public int vertices() {
		return vertices;
	}

	public int edges() {
		return edges;
	}

	// add directed edge v->w
	public void addEdge(int v, int w) {
		if (!adj[v][w]) {
			edges++;
		}
		adj[v][w] = true;
	}

	public int degree(int vertex) {
		int deg = 0;
		for (int v = 0; v < vertices; v++) {
			if (adj[vertex][v]) {
				deg++;
			}
		}
		return deg;
	}

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
