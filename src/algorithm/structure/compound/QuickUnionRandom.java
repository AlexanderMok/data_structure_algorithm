package algorithm.structure.compound;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class QuickUnionRandom {

	private int[] parent;
	private int[] rank;
	private int count;

	public QuickUnionRandom(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		parent = new int[n];
		rank = new int[n];
		Random random = new Random();
		IntStream.range(0, n).forEach(i -> {
			parent[i] = random.nextInt(n - 1);
			rank[i] = 0;
		});
		print();
	}
	
	public void print() {
		Arrays.stream(parent).forEach(e -> System.out.print(e + " "));
		System.out.println();
	}

	public int find(int p) {
		validate(p);
		int root = p;
		while (root != parent[root]) {
			parent[root] = root;
		}
		while (p != root) {
			int newp = parent[p];
			parent[p] = root;
			p = newp;
		}
		return root;
	}

	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ) {
			return;
		}
		if (rank[p] < rank[q]) {
			parent[p] = rootQ;
		} else if (rank[p] > rank[q]) {
			parent[q] = rootP;
		} else {
			parent[p] = rootQ;
			rank[rootQ]++;
		}
		count--;
	}

	public boolean connected(int p, int q) {
		validate(p);
		validate(q);
		return find(p) == find(q);
	}
	
	public int count() {
		return count;
	}

	private void validate(int p) {
		int n = parent.length;
		if (p < 0 || p >= n) {
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
		}
	}
	
	public static void main(String[] args) {
		QuickUnionRandom qur = new QuickUnionRandom(10);
		qur.find(0);
		qur.print();
		qur.find(5);
		qur.print();
	}

}
