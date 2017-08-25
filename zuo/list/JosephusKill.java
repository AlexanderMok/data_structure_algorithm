package zuo.list;

/**
 * 输入： 環形單鏈表，報數值m
 * @author Alex
 *
 */
public class JosephusKill {
    
	public static Node kill1(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		
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
				last = last.next;
			}
			//剩下的節點，連成環狀
			head = last.next;
		}
		return head;
		
	}
	
	public static void main(String[] args) {
		
	}
}
