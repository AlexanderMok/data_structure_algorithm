package zuo.list;
/**
 * Define a list
 * @author Alex
 *
 */
public final class Node {
	public int value;
	public Node next;
	public Node(int data) {
		this.value = data;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", next=" + next + "]";
	}
}
