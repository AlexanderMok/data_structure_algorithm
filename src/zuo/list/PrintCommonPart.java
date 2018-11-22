package zuo.list;


/**
 * Print common part of two sorted list, given two heads of the list
 * 打印两个有序链表的公共部分
 * @author Alex
 *
 */
public class PrintCommonPart {
	
	public static void printCommonPart(Node head1, Node head2){
		System.out.println("Common part -> ");
		while(head1 != null && head2 != null){
			if (head1.value < head2.value) {
				head1 = head1.next;
			} else if(head1.value > head2.value) {
                head2 = head2.next;
			} else {
				System.out.print(head1.value + " ");
				head1 = head1.next;
				head2 = head2.next;
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
        Node node1 = new Node(2);
        Node node2 = new Node(4);
        node1.setNext(node2);
        Node node3 = new Node(6);
        node2.setNext(node3);
        Node node4 = new Node(8);
        node3.setNext(node4);
        
        Node node5 = new Node(1);
        Node node6 = new Node(3);
        node5.setNext(node6);
        Node node7 = new Node(4);
        node6.setNext(node7);
        Node node8 = new Node(8);
        node7.setNext(node8);
        Node node9 = new Node(9);
        node8.setNext(node9);
        
        PrintCommonPart.printCommonPart(node1, node5);
	}
}
