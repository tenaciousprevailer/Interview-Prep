package _3_Tree;

import ent.Tree;
import util.AlgoUtil;

public class LowestCommonAncestor {

    public static void main(String[] args) {
        Tree root = AlgoUtil.getInitialTree();
        Tree lowestCommonAncestor = findLowestCommonAncestor(root, 8, 7);
        System.out.println(lowestCommonAncestor.val);
    }

    private static boolean foundFirst = false;
    private static boolean foundSecond = false;

    private static Tree findLowestCommonAncestor(Tree node, int n1, int n2) {
        if(node == null) return null;
        if(node.val == n1){
            foundFirst = true;
            return node;
        }
        if(node.val == n2){
            foundSecond = true;
            return node;
        }

        Tree leftNode = findLowestCommonAncestor(node.left, n1, n2);
        Tree rightNode = findLowestCommonAncestor(node.right, n1, n2);

        if(leftNode != null && rightNode != null) return node;
        return leftNode != null ? leftNode : rightNode;

    }

}
