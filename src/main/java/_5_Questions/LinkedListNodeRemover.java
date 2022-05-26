package _5_Questions;

import org.junit.jupiter.api.Test;

public class LinkedListNodeRemover {
    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    interface ILinkedListNodeRemover {
        Node removeNode(Node head, Node nodeToRemove);
    }

    static class LinkedListNodeRemoverImpl implements ILinkedListNodeRemover {


        /**
         * removes the node, and return the head of linked list
         * @param head, the head of the linked list
         * @param nodeToRemove, the node that needs to be removed
         */
        @Override
        public Node removeNode(Node head, Node nodeToRemove) {
            if(nodeToRemove != null && nodeToRemove.next != null) {
                removeNode(nodeToRemove);
            }

            return head;
        }

        private void removeNode(Node nodeToRemove) {
            nodeToRemove.val = nodeToRemove.next.val;
            nodeToRemove.next = nodeToRemove.next.next;
        }
    }

}

class LinkedListNodeRemoverTest {

    /**
     1. when linked list is null
     2. when linked list has only 1 element, and the head needs to be removed
     3. when linked list has only 2 element, and the head needs to be removed
     4. when linked list has only 2 element, and the tail needs to be removed
     5. when linked list has only n > 2 element, and the head needs to be removed
     6. when linked list has only n > 2 element, and the middle element needs to be removed
     7. when linked list has only n > 2 element, and the tail needs to be removed
     */

    @Test
    public void test1() {
        LinkedListNodeRemover.ILinkedListNodeRemover linkedListNodeRemover = new LinkedListNodeRemover.LinkedListNodeRemoverImpl();
        LinkedListNodeRemover.Node node1 = new LinkedListNodeRemover.Node(1);
        LinkedListNodeRemover.Node node2 = new LinkedListNodeRemover.Node(2);
        LinkedListNodeRemover.Node node3 = new LinkedListNodeRemover.Node(3);
        LinkedListNodeRemover.Node node4 = new LinkedListNodeRemover.Node(4);
        LinkedListNodeRemover.Node node5 = new LinkedListNodeRemover.Node(5);
        LinkedListNodeRemover.Node node6 = new LinkedListNodeRemover.Node(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        printLinkedList(node1);

        LinkedListNodeRemover.Node cur = linkedListNodeRemover.removeNode(node1, node2);
        printLinkedList(cur);
    }

    private void printLinkedList(LinkedListNodeRemover.Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
