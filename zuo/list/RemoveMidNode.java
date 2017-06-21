package zuo.list;
/**
 * Remove the middle node of a list
 * @author Alex
 *
 */
public class RemoveMidNode {
	
	/**
	 * 不删除任何节点
	 * 1->2 删除1
	 * 1->2->3 删除2
	 * 1->2->3->4删除2
	 * 1->2->3->4->5删除3
	 * 
	 * @param head
	 * @return
	 */
	public static Node removeMid(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		if (head.next.next == null) {
			return head.next;
		}
		Node pre = head;
		Node cur = head.next.next;
		while (cur.next != null && cur.next.next != null) {
			pre = pre.next;
			cur = cur.next.next;
		}
		pre.next = pre.next.next;
		return head;
	}
	
	/**
	 * 
	 * @param head
	 * @param a
	 * @param b
	 * @return
	 */
	public static Node removeMidParam(Node head, int a, int b) {
		return null;
	}

	public static void main(String[] args) {
		Node node1 = new Node(2);
        Node node2 = new Node(4);
        node1.setNext(node2);
        Node node3 = new Node(6);
        node2.setNext(node3);
        Node node4 = new Node(8);
        node3.setNext(node4);
        
        Node remove = removeMid(node1);
        System.out.println(remove);
	}
}
