package _3_Tree;

import ent.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTreeFromTopTraveser implements ITreeTraveser {

    @Override
    public void traverse(Tree leaf) {

        Queue<Tree> queue = new LinkedList<>();

        if(leaf == null)
            return;

        queue.add(leaf);

        while(! queue.isEmpty()) {
            Tree tree = queue.poll();
            System.out.print(tree.val + " ");

            if(tree.left != null)
                queue.add(tree.left);

            if(tree.right != null)
                queue.add(tree.right);
        }
    }

    public void traverse0(Tree root) {

        Queue<Tree> queue = new LinkedList<>();
        queue.add(root); // addLast
        while(queue.size() > 0) {
            Tree currentHead = queue.poll(); // removeFirst
            if(currentHead != null) {
                System.out.print(currentHead.val + " ");
                queue.add(currentHead.left);
                queue.add(currentHead.right);
            }

        }

    }
}
