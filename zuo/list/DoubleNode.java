package zuo.list;
/**
 * Define a double list
 * @author Alex
 *
 */
public class DoubleNode {
	public int value;
	public DoubleNode next;
	public DoubleNode last;
	public DoubleNode(int data) {
		this.value = data;
	}
	
	public void setNext(DoubleNode next) {
		this.next = next;
	}
	
	public void setLast(DoubleNode last) {
		this.last = last;
	}

	@Override
	public String toString() {
		return "DoubleNode [value=" + value + ", next=" + next + ", last=" + last + "]";
	}
	
}
