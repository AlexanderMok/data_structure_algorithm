package zuo.stackandqueue;
/**
 * Tower problem
 * <pre>
 * def tower(size, from, to, spare):
 *     if size==1:
 *         print 'move from {} to {}.'.format(from, to)
 *     else
 *         tower(size-1,from, spare, to)
 *         tower(1, from, to, spare)
 *         tower(size-1,spare, to, from)    
 * </pre>
 * Use stack to solve this problem
 * 汉诺威塔问题，修改规则：不可直接从最左移动到最右，反过来亦然，必须经过中间
 * 求有N层时，打印最优移动路径，和最优移动步数
 * @author Alex
 *
 */
public class TowerByStack {
	
	static int count = 0;
	
	public static void towerByStack(){
		
	}
	
	public static void tower(int size, String from, String to, String spare) {
		count++;
		if (size == 1) {
			System.out.printf("move disk from %s to %s. count %d \n", from, to, count);
		} else {
            tower(size - 1, from, spare, to);
            tower(1, from, to, spare);
            tower(size - 1, spare, to, from);
		}
	}
	
    
	public static void main(String[] args) {
		tower(2, "left", "mid", "right");
	}

}
