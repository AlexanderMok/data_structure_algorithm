package algorithm.structure.compound;

/**
 * The {@code UF} class represents a <em>union–find data type</em> (also known
 * as the <em>disjoint-sets data type</em>). It supports the <em>union</em> and
 * <em>find</em> operations, along with a <em>connected</em> operation for
 * determining whether two sites are in the same component and a <em>count</em>
 * operation that returns the total number of components.
 * <p>
 * 
 * <p>
 * For additional documentation, see
 * <a href="http://algs4.cs.princeton.edu/15uf">Section 1.5</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Alex
 *
 */
public class UF {
	/* parent[i] = parent of i, the component containing i */
	private int[] parent;
	/* rank[i] = rank/size of subtree rooted at i */
	private byte[] rank;
	/* number of component */
	private int count;

	public UF(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		count = n;
		parent = new int[n];
		rank = new byte[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	/**
	 * In which component is object p
	 * 
	 * @param p
	 * @return
	 */
	public int find(int p) {
		validate(p);
		int root = p;
		while (root != parent[root]) {
			root = parent[parent[root]];
		}
		while (p != root) {
			int newp = parent[p];
			parent[p] = root;
			p = newp;
		}
		return root;
	}

	public int find2(int p) {
		validate(p);
		while (p != parent[p]) {
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;
	}

	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ) {
			return;
		}
		
		if(rank[rootP] > rank[rootQ]){
			parent[rootQ] = rootP;
		} else if (rank[rootP] < rank[rootQ]) {
			parent[rootP] = rootQ;
		} else {
			parent[rootP] = rootQ;
			rank[rootQ]++;
		}
		count--;
	}
	
	/**
	 * number of components
	 * @return
	 */
	public int count() {
        return count;
    }

	/**
	 * If p and q are in the same component, they are connected
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	private void validate(int p) {
		int n = parent.length;
		if (p < 0 || p >= n) {
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
		}
	}
}
