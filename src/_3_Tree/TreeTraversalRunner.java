package _3_Tree;

import ent.Tree;
import util.AlgoUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TreeTraversalRunner {

    private static List<ITreeTravesor> traversorsList = Collections.unmodifiableList(Arrays.asList(
            new InorderTreeTravesorRecursive(),
            new InorderTreeTravesorIterative(),

            new PreOrderTreeTravesorRecursive(),
            new PreOrderTreeTravesorIterative(),

            new PostOrderTreeTravesorRecursive(),
            new PostOrderTreeTravesorIterative(),

            new LevelOrderTreeFromTopTravesor(),
            new LevelOrderTreeFromBottomTravesor()
    ));

    public static void main(String[] args) {
        Tree root = AlgoUtil.getInitialTree();
        for(ITreeTravesor traversor : traversorsList) {
            System.out.print(traversor.getClass().getSimpleName() + "\t\t ");
            traversor.traverse(root);
            System.out.println();
        }
    }
}
