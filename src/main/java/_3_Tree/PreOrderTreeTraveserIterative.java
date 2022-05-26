package _3_Tree;

import ent.Tree;

import java.util.Deque;
import java.util.LinkedList;

public class PreOrderTreeTraveserIterative implements ITreeTraveser {

    @Override
    public void traverse(Tree root) {
        // 4 + 5 => + 45 => Root, Left, Right
        /**
         *              1
         *         2          3
         *      4    5      6   7
         *
         *  1 2 4 5 3 6 7
         */

        Deque<Tree> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Tree curRoot = stack.pop();
            if(curRoot != null) {
                System.out.print(curRoot.val + " ");
                stack.push(curRoot.right);
                stack.push(curRoot.left);
            }
        }

    }
}
