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
		//链表长度为0或1
		if (head == null || head.next == null) {
			return head;
		}
		//链表长度为2
		if (head.next.next == null) {
			return head.next;
		}
		//链表长度大于2
		Node pre = head;
		Node cur = head.next.next;
		//链表长度每增加2，要删除的节点后移1位
		while (cur.next != null && cur.next.next != null) {
			pre = pre.next;//需要指向要删除节点的前一个节点，所以移动一个单位
			cur = cur.next.next;//当前指针移动2个单位
		}
		//deletion
		pre.next = pre.next.next;
		return head;
	}
	
	/**
	 * 1->2->3->4->5 a/b的值为r
	 * r=0 不删除
	 * r属于(0,1/5] 删除1
	 * r属于(1/5,2/5] 删除2
	 * r属于(2/5,3/5] 删除3
	 * ..
	 * r > 1 不删除
	 * @param head
	 * @param a
	 * @param b
	 * @return
	 */
	public static Node removeMidByParam(Node head, int a, int b) {
		if (a < 1 || a > b) {
			return head;
		}
		//calc the length of the list
		int n = length(head);
		//this is the key
		n = (int) Math.ceil(a * n / b);
		if (n == 1) {
			head = head.next;
		}
		if (n > 1) {
			Node cur = head;
			while(--n != 1){
				cur = cur.next;
			}
			cur.next = cur.next.next;
		}
		return head;
	}
	
	public static int length(Node head) {
		int n = 0;
		Node cur = head;
		while (cur != null) {
			n++;
			cur = cur.next;
		}
		return n;
	}

	public static void main(String[] args) {
		Node head = new Node(2);
        Node node2 = new Node(4);
        head.setNext(node2);
        Node node3 = new Node(6);
        node2.setNext(node3);
        Node node4 = new Node(8);
        node3.setNext(node4);
        Node node5 = new Node(9);
        node4.setNext(node5);
        
        Node remove = removeMid(head);
        System.out.println(remove);
	}
}
