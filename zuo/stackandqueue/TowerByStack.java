package zuo.stackandqueue;

import java.util.Stack;

/**
 * Tower problem
 * 
 * <pre>
 * def tower(size, from, to, spare):
 *     if size==1:
 *         print 'move from {} to {}.'.format(from, to)
 *     else
 *         tower(size-1,from, spare, to)
 *         tower(1, from, to, spare)
 *         tower(size-1,spare, to, from)
 * </pre>
 * 
 * Now if mid is a must position when move from left to right or right to left.
 * Use stack to solve this problem
 * 
 * 汉诺威塔问题，修改规则：不可直接从最左移动到最右，反过来亦然，必须经过中间 求有N层时，打印最优移动路径，和最优移动步数
 * 
 * @author Alex
 *
 */
public class TowerByStack {

	static int count = 0;

	/**
	 * Traditional tower
	 * 
	 * @param size
	 * @param from
	 * @param to
	 * @param spare
	 */
	public static void traditionalTower(int size, String from, String to, String spare) {
		count++;
		if (size == 1) {
			System.out.printf("move disk from %s to %s. count %d \n", from, to, count);
		} else {
			traditionalTower(size - 1, from, spare, to);
			traditionalTower(1, from, to, spare);
			traditionalTower(size - 1, spare, to, from);
		}
	}

	static class TowerByStackRecursive {

		public static int towerByStackRecursive(int size, String left, String mid, String right) {
			if (size < 1) {
				return 0;
			}
			return process(size, left, mid, right, left, right);
		}

		private static int process(int size, String left, String mid, String right, String from, String to) {
			// 1 layer left and this is the base case
			if (size == 1) {
				/* left->mid right->mid mid->left mid->right */
				if (from.equals(mid) || to.equals(mid)) {
					System.out.printf("move 1 from %s to %s \n", from, to);
					return 1;
				} else {
					/* every move has to pass through mid */
					System.out.printf("move 1 from %s to %s \n", from, mid);
					System.out.printf("move 1 from %s to %s \n", mid, to);
					return 2;
				}
			}
			// N-1 layer left
			if (from.equals(mid) || to.equals(mid)) {
				/*  */
				String another = (from.equals(left) || to.equals(left)) ? right : left;
				int part1 = process(size - 1, left, mid, right, from, another);
				int part2 = 1;
				System.out.printf("move %d from %s to %s \n", size, from, to);
				int part3 = process(size, left, mid, right, another, to);
				return part1 + part2 + part3;
			} else {
				int part1 = process(size - 1, left, mid, right, from, to);
				int part2 = 1;
				System.out.printf("move %d from %s to %s \n", size, from, mid);
				int part3 = process(size - 1, left, mid, right, to, from);
				int part4 = 1;
				System.out.printf("move %d from %s to %s \n", size, mid, to);
				int part5 = process(size - 1, left, mid, right, from, to);
				return part1 + part2 + part3 + part4 + part5;
			}
		}
	}
	
	
	static class TowerByStackNonRecur {
		public enum Action {
			/*still, left to mid, mid to left, mid to right, right to mid*/
			No, L2M, M2L, M2R, R2M
		}
		
		public static int towerByStack(int size, String left, String mid, String right) {
			Stack<Integer> lStack = new Stack<>();
			Stack<Integer> mStack = new Stack<>();
			Stack<Integer> rStack = new Stack<>();
			return 0;
		}
		
		
	}

	public static void main(String[] args) {
		// TowerByStack.traditionalTower(2, "left", "mid", "right");
		TowerByStack.TowerByStackRecursive.towerByStackRecursive(2, "left", "mid", "right");
	}

}
