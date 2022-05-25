package _3_Tree;

import ent.Tree;
import util.AlgoUtil;

public class TreeMirror {

    public static void main(String[] args) {
        Tree root = AlgoUtil.getInitialTree();
        Tree newTree = mirror(root);
        System.out.println(newTree);
    }

    private static Tree mirror(Tree org) {
        return mirror(org, new Tree(org.val));
    }

    private static Tree mirror(Tree org, Tree mirror) {
        if(org == null) return org;

        if(org.left != null) {
            mirror.right = new Tree(org.left.val);
            mirror(org.left, mirror.right);
        }

        if(org.right != null) {
            mirror.left = new Tree(org.right.val);
            mirror(org.right, mirror.left);
        }

        return mirror;
    }

}
