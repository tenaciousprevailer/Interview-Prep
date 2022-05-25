package _3_Tree;

import ent.Tree;

public class PreOrderTreeTraveserRecursive implements ITreeTraveser {

    @Override
    public void traverse(Tree leaf) {
        if(leaf == null)
            return;
        System.out.print(leaf.val + " ");
        traverse(leaf.left);
        traverse(leaf.right);
    }

}
