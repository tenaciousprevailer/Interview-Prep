package _3_Tree;

import ent.Tree;
import util.AlgoUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TreeTraversalRunner {

    private static List<ITreeTraveser> traversorsList = Collections.unmodifiableList(Arrays.asList(
//            new InorderTreeTravesorRecursive(),
            new InorderTreeTraveserIterative()

/*            new PreOrderTreeTravesorRecursive(),
            new PreOrderTreeTravesorIterative(),

            new PostOrderTreeTravesorRecursive(),
            new PostOrderTreeTravesorIterative(),

            new LevelOrderTreeFromTopTravesor(),
            new LevelOrderTreeFromBottomTravesor()*/
    ));

    public static void main(String[] args) {
//        Tree root = AlgoUtil.getInitialTree();
//        for(ITreeTravesor traversor : traversorsList) {
//            System.out.print(traversor.getClass().getSimpleName() + "\t\t ");
//            traversor.traverse(root);
//            System.out.println();
//        }

        Tree root = AlgoUtil.getInitialTree();
        new LevelOrderTreeFromBottomTraveser().traverse(root);
        System.out.println();
//        new LevelOrderTreeFromBottomTravesor().traverse0(root);
        System.out.println();

    }
}
