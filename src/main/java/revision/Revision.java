package revision;

import ent.Node;
import util.AlgoUtil;

public class Revision {

    public static void main(String[] args) {
        Node _1 = new Node(1);
        Node _2 = new Node(2);
        Node _3 = new Node(3);
        Node _4 = new Node(4);
        Node _x = new Node(11);
        Node _y = new Node(12);

        Node _5 = new Node(5);
        Node _6 = new Node(6);
        Node _7 = new Node(7);
        Node _8 = new Node(8);

        _1.next = _2;
        _2.next = _3;
        _3.next = _4;
        _4.next = _x;
        _x.next = _y;

//        _4.next = _5;
        _5.next = _6;
        _6.next = _7;
        _7.next = _8;

//        Node node = CFG.deleteLastOccurrence(_1, 4);
//        AlgoUtil.printLinkedList(node);
//
//        Deque<Integer> dq = new LinkedList<>();
//        dq.poll()

        MergeList.mergeAlt( _5, _1);
        AlgoUtil.printLinkedList(_5);
    }
}

class MergeList {
    public static void mergeAlt(Node head1, Node head2){
        if(head1 == null || head2 == null) return;

        System.out.println("head1:" + head1.val);
        System.out.println("head2:" + head2.val);

        Node n1Next = head1.next;
        Node n2Next = head2.next;


        head1.next = head2;
        head2.next = n1Next;

        mergeAlt(n1Next, n2Next);

        if(n1Next == null && n2Next != null) { // second node is bigger
            // append remaining of n2
            head2.next = n2Next;
        }

    }
}

class CFG {
    public static Node deleteLastOccurrence(Node node, int k) {

        Node prev = null;
        Node current = node;
        Node nodeToDeleteFrom = null;

        while(current != null) {
            if(current.val == k) {
                nodeToDeleteFrom = prev;
            }

            prev = current;
            current = current.next;
        }

        if(nodeToDeleteFrom != null) {
            nodeToDeleteFrom.next = nodeToDeleteFrom.next.next;
        }

        return node;
    }
}

class Solution {
    // 1 2 3 4 5 6 7 8
    // 3 2 1 6 5 4 8 7
    public static Node reverse(final Node node, final int k) { // node->4
        if (node == null) return null;
        Node end = getNthOrLastNode(node, k); // 6
        Node nextReversed = reverse(end.next, k); // nextReversed -> 8
        end.next = null;
        final Node curReversed = reverse(node); // curReversed -> 6
        node.next = nextReversed;
        return curReversed; // 8
    }

    // returns nth node or the last node
    // will never return null
    private static Node getNthOrLastNode(final Node node, final int k) {
        Node end = node;
        for (int i = 0; i < k-1 && end.next != null; i++) {
            end = end.next;
        }
        return end;
    }


    // 1 2 3 -> 3 2 1 ->. return 3(head)
    private static Node reverse(Node start) {

        Node prev = null;
        Node current = start;
        Node next = null;

        while (current != null) {
            next = current.next; // 3
            current.next = prev; // 1 -> null
            prev = current; // 1
            current = next; // 2
        }

        return prev;
    }
}