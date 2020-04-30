package _3_Tree;

import ent.Tree;

public class InorderTreeTravesorRecursive implements ITreeTravesor {

    @Override
    public void traverse(Tree leaf) {
        if(leaf == null)
            return;
        traverse(leaf.left);
        System.out.print(leaf.val + " ");
        traverse(leaf.right);
    }

}
