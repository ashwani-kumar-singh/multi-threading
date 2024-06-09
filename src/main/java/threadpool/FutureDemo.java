package threadpool;

import thread.producerconsumer.ThreadUtil;

import java.util.concurrent.*;

/**
 * Future, which represents the result of an asynchronous computation. A Future object can be used to check if
 * the computation is complete, retrieve the result of the computation, or wait for the computation to complete.
 * Future as an object that holds the result – it may not hold it right now, but it will do so in the future
 * (once the Callable returns). Thus, a Future is basically one way the main thread can keep track of the progress
 * and result from other threads.
 * <p>
 * Future is a generic interface that represents the result of an asynchronous computation. It has the following methods:
 * <p>
 * boolean cancel(boolean mayInterruptIfRunning): Attempts to cancel the computation. If the computation has already
 * completed or cannot be cancelled, this method returns false. Otherwise, it returns true.
 * cancelling a task does not guarantee that it will stop immediately. It is up to the implementation of the
 * You can use the cancel() method of the Future interface to attempt to cancel the execution of a task.
 *
 * boolean isCancelled(): Returns true if the computation was cancelled before it completed normally.
 *
 * boolean isDone(): Returns true if the computation has completed, whether it completed normally, was cancelled,
 * or terminated due to an exception.
 *
 * V get() throws InterruptedException, ExecutionException: Waits if necessary for the computation to complete,
 * and then retrieves its result. If the computation was cancelled, this method throws a CancellationException.
 * If the computation completed due to an exception, this method throws an ExecutionException.
 * If the current thread was interrupted while waiting for the result, this method throws an InterruptedException.
 *
 * V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException: Waits the
 * specified amount of time for the computation to complete, and then retrieves its result. If the computation has
 * not completed within the specified time, this method throws a TimeoutException.
 * Otherwise, it behaves the same as the get() method.
 *
 * <a href="https://connect2grp.medium.com/understating-java-future-and-callable-features-aec70d2aef6">...</a>
 */
public class FutureDemo {

    public static void main(String[] args) {
        getMethod();
        getMethodWithTimeOut();
        cancelMethod();
    }

    private static void getMethod() {
        System.out.println("******** get method ************");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> futureSum = executor.submit(() -> {
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                ThreadUtil.sleep(10);
                sum += i;
            }
            return sum;
        });
        System.out.println("Waiting for the result...");
        try {
            int result = futureSum.get();
            System.out.println("The sum of first 100 numbers is: " + result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        executor.shutdown();
    }

    private static void getMethodWithTimeOut() {
        System.out.println("*********** get method with timeout **************");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> futureSum = executor.submit(() -> {
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                ThreadUtil.sleep(50);
                sum += i;
            }
            return sum;
        });
        System.out.println("Waiting for the result...");
        try {
            int result = futureSum.get(2, TimeUnit.SECONDS);
            System.out.println("The sum of first 100 numbers is: " + result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println("Timeout");
        }
        executor.shutdown();
    }

    private static void cancelMethod() throws RuntimeException {
        System.out.println("*********** cancel method  **************");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> futureSum = executor.submit(() -> {
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        });
        System.out.println("Waiting for the result...");
        futureSum.cancel(true);
        if (futureSum.isCancelled()) {
            System.out.println("Task was cancelled");
        } else {
            try {
                System.out.println(futureSum.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
    }


}
