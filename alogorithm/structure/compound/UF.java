package algorithm.structure.compound;

/**
 * The {@code UF} class represents a <em>union–find data type</em> (also known
 * as the <em>disjoint-sets data type</em>). It supports the <em>union</em> and
 * <em>find</em> operations, along with a <em>connected</em> operation for
 * determining whether two sites are in the same component and a <em>count</em>
 * operation that returns the total number of components.
 * <p>
 * 
 * 
 * @author Alex
 *
 */
public class UF {
	
	private int[] parent;
	private byte[] rank;
	/*number of component*/
	private int count;
	
	public UF(int n) {
		if(n < 0) throw new IllegalArgumentException();
		count = n;
		parent = new int[n];
		rank = new byte[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public void union(int p, int q) {
	}
    
	/**
	 * In which component is object p
	 * @param p
	 * @return
	 */
	public int find(int p) {
		validate(p);
		while (p != parent[p]) {
			
		}
		return 0;
	}
    
	/**
	 * If p and q are in the same component, they are connected
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	private void validate(int p) {
		int n = parent.length;
		if (p < 0 || p >=n ) {
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
		}
	}
}
