package _3_Tree;

import ent.Tree;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class InorderTreeTraveserIterative implements ITreeTraveser {

    //    @Override
    public void traverse0(Tree root) {
        System.out.println();
        Stack<Tree> stack = new Stack<Tree>();
        stack.push(root);
        HashSet<Tree> hashset = new HashSet<>();
        while (stack.size() > 0) {
            Tree current = stack.pop();
            if (current != null) {
                // leaf node
                if (current.left == null && current.right == null) {
                    System.out.print(current.val + " ");
                } else if (hashset.contains(current)) {
                    // if current has been processed already
                    System.out.print(current.val + " ");
                } else {
                    // else new node
                    hashset.add(current);
                    stack.push(current.right);
                    stack.push(current);
                    stack.push(current.left);
                }
            }
        }
    }

    @Override
    public void traverse(Tree root) {

        // 4 + 5
        // Left Root Right


        /**
         *              1
         *       2               3
         *    4     5         6     7
         *
         *   4 2 5 1 6 3 7
         *
         */

        Deque<Tree> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Tree curHead = stack.pop();
            if(curHead != null) {
                if (curHead.left == null && curHead.right == null) // leaf node
                    System.out.print(curHead.val + " ");
                else if (stack.size() > 0 && curHead.right == stack.peek()) // already processed root
                    System.out.print(curHead.val + " ");
                else {
                    stack.push(curHead.right);
                    stack.push(curHead);
                    stack.push(curHead.left);
                }
            }
        }
    }

}
