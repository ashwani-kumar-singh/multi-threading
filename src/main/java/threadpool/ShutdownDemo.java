package threadpool;

import thread.producerconsumer.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * When using an Executor, we can shut it down by calling the shutdown() or shutdownNow() methods. Although, it
 * won’t wait until all threads stop executing.
 *
 * awaitTermination() -> Waiting for existing threads to complete their execution can be achieved by using the
 * awaitTermination() method.
 *
 * shutdown() -> method of ThreadPoolExecutor class initiates an orderly shutdown in which already submitted
 * task is accepted, but no new task is accepted.
 *
 * shutdownNow() -> method of ThreadPoolExecutor class attempts to stop all the tasks which are executing
 * actively, halts the processing of waiting tasks and returns a list of the tasks that were awaiting execution.
 *
 * <a href="https://www.baeldung.com/java-executor-wait-for-threads">...</a>
 */
public class ShutdownDemo {
    public static void main(String[] args) {
        shutdownExample();
        awaitTerminationExample();
        shutdownNowExample();
        System.out.println("Main thread is completed");

    }

    private static void awaitTerminationExample() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(()-> {
            ThreadUtil.sleep(3000);
            System.out.println("Task completed");
        });
        try {
            boolean isTerminated = executorService.awaitTermination(2, TimeUnit.SECONDS);
            System.out.println("isTerminated: " + isTerminated);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static ExecutorService shutdownExample() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(()-> {
            ThreadUtil.sleep(3000);
            System.out.println("Shutdown - Task completed");
        });
        executorService.shutdown();
        return executorService;
    }

    private static ExecutorService shutdownNowExample() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(()-> {
            ThreadUtil.sleep(1500);
            System.out.println("ShutdownNow - Task completed");
        });
        executorService.shutdownNow();
        return executorService;
    }
}
