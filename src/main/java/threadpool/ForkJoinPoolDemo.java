package threadpool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool is the central part of the fork/join framework introduced in Java 7. It solves a common
 * problem of spawning multiple tasks in recursive algorithms. We’ll run out of threads quickly by using
 * a simple ThreadPoolExecutor, as every task or subtask requires its own thread to run.
 *
 * In a fork/join framework, any task can spawn (fork) a number of subtasks and wait for their completion
 * using the join method. The benefit of the fork/join framework is that it does not create a new thread
 * for each task or subtask, instead implementing the work-stealing algorithm.
 */
public class ForkJoinPoolDemo {
    public static void main(String[] args) {
        TreeNode tree = new TreeNode(5,
                new TreeNode(3), new TreeNode(2,
                new TreeNode(2), new TreeNode(8)));

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int sum = forkJoinPool.invoke(new CountingTask(tree));
        System.out.println("sum of tree node: " + sum);
    }

}


class CountingTask extends RecursiveTask<Integer> {
    private final TreeNode node;

    public CountingTask(TreeNode node) {
        this.node = node;
    }
    @Override
    protected Integer compute() {
        return node.value + node.children.stream()
                .map(childNode -> new CountingTask(childNode).fork())
                .mapToInt(ForkJoinTask::join).sum();
    }
}

class TreeNode {
    int value;
    Set<TreeNode> children;
    TreeNode(int value, TreeNode... children) {
        this.value = value;
        this.children = new HashSet<>(Arrays.asList(children));
    }
}