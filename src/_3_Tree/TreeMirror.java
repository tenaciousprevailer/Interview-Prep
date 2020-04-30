package _3_Tree;

import ent.Tree;
import util.AlgoUtil;

import java.util.LinkedList;

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
            Tree right = new Tree(org.left.val);
            mirror.right = right;
            mirror(org.left, mirror.right);
        }

        if(org.right != null) {
            Tree left = new Tree(org.right.val);
            mirror.left = left;
            mirror(org.right, mirror.left);
        }

        return mirror;
    }

}
