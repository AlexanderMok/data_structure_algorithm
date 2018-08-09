package alogorithm.structure.compound;

/**
 * find/connected could be expensive N array accesses Trees can get tall
 * 
 * @author Alex
 *
 */
public class QuickUnionUF {
	private int[] id;

	/**
	 * 0,1,2,3,4,5
	 * 
	 * @param n
	 */
	public QuickUnionUF(int n) {
		id = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
		id[3] = 2;
		id[4] = 2;
		id[5] = 4;
	}

	/**
	 * id[p] is the parent id of p, if value of id[p] equals p, then it is root
	 * 
	 * find the root of p
	 * 
	 * p array accesses, worst N array accesses
	 * 
	 * @param p
	 * @return
	 */
	public int find(int p) {
		if (p == id[p]) {
			return p;
		}
		return find(id[p]);
	}

	public int find2(int p) {
		while (p != id[p]) {
			p = id[p];
		}
		return p;
	}

	/**
	 * change root of p ot point to root of q
	 * 
	 * @param p
	 * @param q
	 */
	public void union(int p, int q) {
		int p_root = find(p);
		int q_root = find(q);
		if (p_root == q_root) {
			return;
		}
		id[p_root] = q_root;
	}

	/**
	 * p and q array accesses
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public static void main(String[] args) {
		QuickUnionUF quickUnion = new QuickUnionUF(8);
		int root = quickUnion.find(4);
		System.out.println(root);
		System.out.println(quickUnion.connected(5, 3));
		System.out.println(quickUnion.connected(5, 1));
		System.out.println(quickUnion.find(5));
		quickUnion.union(5, 6);
		System.out.println(quickUnion.find(5));
	}

}
