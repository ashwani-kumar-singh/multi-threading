package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Work stealing was introduced in Java with the aim of reducing contention in multi-threaded applications. This is
 * done using the fork/join framework.
 * In the fork/join framework, problems or tasks are recursively broken down into sub-tasks. The sub-tasks are then
 * solved individually, with the sub-results combined to form the result.
 *
 * The broken-down task is solved with the help of worker threads provided by a thread pool. Each worker thread will
 * have sub-tasks it’s responsible for. These are stored in double-ended queues (deques).
 * Each worker thread gets sub-tasks from its deque by continuously popping a sub-task off the top of the deque.
 * When a worker thread’s deque is empty and submission queue is empty, it means that all the task and sub-tasks
 * have been popped off and completed.
 *
 * ForkJoinPool.commonPool uses a last-in, first-out (LIFO) queue configuration, whereas
 * Executors.newWorkStealingPool uses first-in, first-out approach (FIFO)
 * the FIFO approach has these advantages over LIFO:
 *
 * It reduces contention by having stealers operate on the opposite side of the deque as owners
 * It exploits the property of recursive divide−and−conquer algorithms of generating “large” tasks early.
 *
 * At this point, the worker thread randomly selects a peer thread-pool thread it can “steal” work from.
 * It then uses the first-in, first-out approach (FIFO) to take sub-tasks from the tail end of the victim’s deque.
 *
 * With a synchronous thread pool, ForkJoinPool.commonPool puts threads in the pool as long as the task is still in
 * progress. As a result, the level of work stealing is not dependent on the level of task granularity.
 *
 * The asynchronous Executors.newWorkStealingPool is more managed, allowing the level of work stealing to be
 * dependent on the level of task granularity.
 *
 * <a href="https://www.baeldung.com/java-work-stealing">...</a>
 */
public class WorkStealingPoolDemo {

    public static void main(String[] args) {
        int parallelism = ForkJoinPool.getCommonPoolParallelism();
        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool(parallelism);
        int sum = forkJoinPool.invoke(new BinarySearchTask(1, 100));
        System.out.println("sum of numbers: " + sum);
        forkJoinPool.shutdown();
    }
}

class BinarySearchTask extends RecursiveTask<Integer> {

    private final int start;
    private final int end;
    public BinarySearchTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int leftResult, rightResult;
        int totalSum = 0;
        if (end - start <= 4) {
            for (int i = start; i <= end; i++) {
                totalSum += i;
            }
            return totalSum;
        } else {
            int mid = (end - start) / 2;
            BinarySearchTask leftTask = new BinarySearchTask(start, mid);
            BinarySearchTask rightTask = new BinarySearchTask(mid + 1, end);
            leftTask.fork();
            rightTask.fork();

            leftResult = leftTask.join();
            rightResult = rightTask.join();
            return leftResult + rightResult;
        }
    }
}
