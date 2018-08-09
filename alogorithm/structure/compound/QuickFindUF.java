package alogorithm.structure.compound;

/**
 * union is too expansive
 * 
 * @author Alex
 *
 */
public class QuickFindUF {
	private int[] id;

	/**
	 * Initialization. Each component is on its own position
	 * 
	 * N array accesses
	 *
	 * @param n
	 */
	public QuickFindUF(int n) {
		id = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}

	/**
	 * id[p] is the position/component where p is
	 * 
	 * or think id[p] as the parent id of p, then trees are flat
	 * 
	 * @param p
	 * @return
	 */
	public int find(int p) {
		return id[p];
	}

	/**
	 * at most 2N+2 array access to change all entries
	 * 
	 * N^2 array accesses for N operations on N objects
	 * 
	 * @param p
	 * @param q
	 */
	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		if (pid == qid) {
			return;
		}
		for (int i = 0; i < id.length; i++) {
			if (pid == id[i]) {
				id[i] = qid;
			}
		}
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
