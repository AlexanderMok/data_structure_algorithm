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
	
	public Node setNext(Node next) {
		this.next = next;
		return this;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", next=" + next + "]";
	}
}
