package _3_Tree;

import ent.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LevelOrderTreeFromBottomTravesor implements ITreeTravesor {

    @Override
    public void traverse(Tree leaf) {

        Queue<Tree> queue = new LinkedList<>();

        if(leaf == null)
            return;

        queue.add(leaf);

        Stack<Tree> stack = new Stack<>();

        while(!queue.isEmpty()) {

            Tree n = queue.poll();
            stack.push(n);

            if(n.left != null)
                queue.add(n.left);

            if(n.right != null)
                queue.add(n.right);
        }

        while(stack.size() > 0) {
            System.out.print(stack.pop().val + " ");
        }
    }
}
