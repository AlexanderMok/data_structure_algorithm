package alogorithm.structure.compound;

import java.util.stream.IntStream;

public class WeightedQuickUnionByHeightUF {
	private int[] parent;
	private int[] height;

	public WeightedQuickUnionByHeightUF(int n) {
		parent = new int[n];
		height = new int[n];
		IntStream.of(0, n).forEach(i -> {
			parent[i] = i;
			height[i] = 0;
		});
	}

	public int find(int p) {
		int root = p;
		while (root != parent[root]) {
			root = parent[root];
		}
		return root;
	}
	
	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP == rootQ) {
			return;
		}
		// make shorter root point to taller one
		if(height[rootP] < height[rootQ]) {
			parent[rootP] = rootQ;
		} else if (height[rootP] > height[rootQ]){
			parent[rootQ] = rootP;
		} else {//increase the height Noteby one (if the two tree are the same height)
			parent[rootP] = rootQ;
			height[rootQ]++;
		}
	}

}
