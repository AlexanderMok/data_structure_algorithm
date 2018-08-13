package alogorithm.structure.compound;

/**
 * lgN for union, find, connected
 * @author Alex
 *
 */
public class WeightedQuickUnionUF {
	/* parent[i]=parent components/sites of i */
	private int[] parent;
	/* size[i]=number of components/sites in subtree */
	private int[] size;
	/* number of root components */
	private int count;

	public WeightedQuickUnionUF(int n) {
		count = n;
		parent = new int[n];
		size = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1; // each tree has one site
		}
		parent[3] = 2;
		parent[4] = 2;
		parent[5] = 4;
	}

	public int count() {
		return this.count;
	}
    
	/**
	 *  p array accesses
	 *  
	 *  takes time proportional to depth of p
	 *  
	 * @param p
	 * @return
	 */
	public int find(int p) {
		validate(p);
		if (p == parent[p]) {
			return p;
		}
		return find(parent[p]);
	}

	public int find2(int p) {
		validate(p);
		while (p != parent[p]) {
			p = parent[p];
		}
		return p;
	}
	
	/**
	 * path halving
	 * @param p
	 * @return
	 */
	public int findWithPathCompression(int p ) {
		validate(p);
		while(p != parent[p]) {
			//link to grandparent
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;
	}

	private void validate(int p) {
		int n = this.parent.length;
		if (p < 0 || p > n) {
			throw new IndexOutOfBoundsException();
		}
	}

	public boolean connected(int p, int q) {
		return find2(p) == find2(q);
	}

	public void union(int p, int q) {
        int pRoot = find2(p);
        int qRoot = find2(q);
        if (pRoot == qRoot) {
        	return;
        }
        //link smaller tree to larger tree
        if(size[pRoot] > size[qRoot]){
        	parent[qRoot] = pRoot;
        	size[pRoot] += size[qRoot];
        } else {
        	parent[pRoot] = qRoot;
        	size[qRoot] += size[pRoot];
        }
        count--;
	}
	
	public static void main(String[] args) {
		
	}
}
