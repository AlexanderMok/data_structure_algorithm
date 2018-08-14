package alogorithm.structure.compound;

import java.util.stream.IntStream;

public class QuickUnionPathCompression {
	private int[] parent;
	private int[] size;

	public QuickUnionPathCompression(int n) {
		parent = new int[n];
		size = new int[n];
		IntStream.range(0, parent.length).forEach(i -> {
			parent[i] = i;
			size[i] = 1;
		});
	}

	public int find(int p) {
		int root = p;
		// after the first loop, we get the root of p
		while (root != parent[root]) {
			root = parent[root];
		}
		// set parent of each node to the root
		while (p != root) {
			int newp = parent[p];
			newp = root;
			p = newp;
		}
		return p;
	}

	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ) {
			return;
		}
        if (size[rootP] < size[rootQ]) {
        	parent[rootP] = rootQ;
        	size[rootQ] += size[rootP];
        }else {
        	parent[rootQ] = rootP;
        	size[rootP] += size[rootQ];
        }
	}

}
