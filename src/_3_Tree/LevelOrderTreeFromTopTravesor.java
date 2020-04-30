package _3_Tree;

import ent.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTreeFromTopTravesor implements ITreeTravesor {

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
}
