package _3_Tree;

import ent.Tree;
import util.AlgoUtil;

import java.util.LinkedList;

public class NodePathFromRootPrinter {

    public static void main(String[] args) {
        Tree root = AlgoUtil.getInitialTree();
        LinkedList<Integer> pathList = new LinkedList<>();
        printPathToNode(root, 8, pathList);
        System.out.println(pathList);
    }

    private static boolean printPathToNode(Tree node, int nodeVal, LinkedList<Integer> path) {
        if(node == null) return false;

        if(node.val == nodeVal ||
                printPathToNode(node.left, nodeVal, path) ||
                printPathToNode(node.right, nodeVal, path)) {

            path.addLast(node.val);
            return true;
        }
        return false;
    }

}
