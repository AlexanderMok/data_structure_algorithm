package zuo.list;

/**
 * 输入： 環形單鏈表，報數值m
 * @author Alex
 *
 */
public class JosephusKill {
    /**
     * 
     * @param head 環形鏈表
     * @param m
     * @return     被刪除節點的值
     */
	public static Node kill1(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		//pointer last to reference the last element
		Node last = head;
		while(last.next != head){
			last = last.next;
		}
		
		int count = 0;
		while(head != last){
			if(++count == m){
				//刪除節點
				last.next = head.next;
				count = 0;
			} else {
				//原来的头元素放到尾部
				last = last.next;
			}
			//剩下的節點，連成環狀，更新頭節點
			head = last.next;
		}
		return head;
		
	}
	
	public static Node kill2(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		Node cur = head.next;
		int temp = 1;
		while (cur != head) {
			temp++;
			cur = cur.next;
		}
		temp = getLive(temp, m);
		while(--temp != 0) {
			head = head.next;
		}
		return head;
	}
	
	private static int getLive(int i, int m) {
		if (i == 1) {
			return 1;
		}
		return (getLive(i - 1, m) + m - 1) % i + 1;
	}
	
	public static void main(String[] args) {
		Node head = new Node(0)
				.setNext(new Node(1)
						.setNext(new Node(2)
								.setNext(new Node(3)
										.setNext(new Node(4)
												.setNext(new Node(5)
														.setNext(new Node(6)))))));
		// construct singly round linked list
		Node last = head;
		while(last.next != null) {
			last = last.next;
		}
		last.next = head;
	}
}
