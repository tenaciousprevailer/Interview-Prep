package _3_Tree;

import ent.Tree;

public class PostOrderTreeTravesorRecursive implements ITreeTravesor {

    @Override
    public void traverse(Tree leaf) {
        if(leaf == null)
            return;
        traverse(leaf.left);
        traverse(leaf.right);
        System.out.print(leaf.val + " ");
    }

}
