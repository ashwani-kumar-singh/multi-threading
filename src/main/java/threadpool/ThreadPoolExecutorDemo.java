package threadpool;

import thread.producerconsumer.ThreadUtil;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Executor: Executor interface has a single execute method to submit Runnable instances for execution.
 *
 * ExecutorService: ExecutorService interface contains a large number of methods to control the progress of the tasks and manage the
 * termination of the service. Using this interface, we can submit the tasks for execution and also control their execution using the returned Future instance.
 *
 * ExecutorService submit a task and then use the returned Future‘s get method to wait until the
 * submitted task finishes and the value is returned.
 * Runnable‘s single method does not throw an exception and does not return a value. The Callable interface may
 * be more convenient, as it allows us to throw an exception and return a value.
 *
 * ThreadPoolExecutor: ThreadPoolExecutor is an extensible thread pool implementation with lots of parameters and hooks
 * for fine-tuning.
 * The main configuration parameters that we’ll discuss here are corePoolSize, maximumPoolSize and keepAliveTime.
 * The pool consists of a fixed number of core threads that are kept inside all the time. It also consists of some
 * excessive threads that may be spawned and then terminated when they are no longer needed.
 *
 * The corePoolSize parameter is the number of core threads that will be instantiated and kept in the pool. When a new
 * task comes in, if all core threads are busy and the internal queue is full, the pool is allowed to grow up to
 * maximumPoolSize.
 * The keepAliveTime parameter is the interval of time for which the excessive threads (instantiated in excess of the
 * corePoolSize) are allowed to exist in the idle state. By default, the ThreadPoolExecutor only considers non-core
 * threads for removal. In order to apply the same removal policy to core threads, we can use the
 * allowCoreThreadTimeOut(true) method.
 *
 * newFixedThreadPool:  method creates a ThreadPoolExecutor with
 * a. equal corePoolSize and maximumPoolSize parameter values.
 * b. zero keepAliveTime.
 *
 * This means that the number of threads in this thread pool is always the same
 *
 *  newCachedThreadPool: This method does not receive a number of threads at all. We set the corePoolSize to 0 and
 *  set the maximumPoolSize to Integer.MAX_VALUE. Finally, the keepAliveTime is 60 seconds. The cached thread pool
 *  may grow without bounds to accommodate any number of submitted tasks.
 *
 *  newSingleThreadExecutor: It creates another typical form of ThreadPoolExecutor containing a single thread. The
 *  single thread executor is ideal for creating an event loop. The corePoolSize and maximumPoolSize parameters
 *  are equal to 1, and the keepAliveTime is 0.
 *
 * ScheduledThreadPoolExecutor:  ScheduledThreadPoolExecutor extends the ThreadPoolExecutor class and also implements
 * the ScheduledExecutorService interface with several additional methods:
 *
 * schedule: schedule method allows us to run a task once after a specified delay.
 * scheduleAtFixedRate:  method allows us to run a task after a specified initial delay and then run it repeatedly
 * with a certain period.
 * period: The period argument is the time measured between the starting times of the tasks, so the execution rate is fixed.
 *
 * scheduleWithFixedDelay: method is similar to scheduleAtFixedRate in that it repeatedly runs the given task, but
 * the specified delay is measured between the end of the previous task and the start of the next. The execution
 * rate may vary depending on the time it takes to run any given task.
 *
 * newScheduledThreadPool() method to create a ScheduledThreadPoolExecutor with a given corePoolSize,
 * unbounded maximumPoolSize and 0 keepAliveTime.
 *
 * <a href="https://www.geeksforgeeks.org/thread-pools-java/"></a>
 * <a href="https://www.baeldung.com/thread-pool-java-and-guava">...</a>
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        // Executor with runnable functional interface
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("Executor - Hello World"));


        // ExecutorService with callable functional interface
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future = executorService.submit(() -> "ExecutorService - Hello World");
        ThreadUtil.sleep(1000);
        try {
            if(future.isDone())
                System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(10), new CustomThreadFactor(), new CustomRejectedHandler());

        poolExecutor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 25; i++) {
            poolExecutor.submit(() -> {
                try {
                    Thread.sleep(5000);
                    System.out.println("Thread name: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            poolExecutor.shutdown();
        }

        // ThreadPoolExecutor with fixed thread pool
        ThreadPoolExecutor fixedThreadPool =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        fixedThreadPool.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        fixedThreadPool.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        fixedThreadPool.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        fixedThreadPool.shutdown();
        System.out.println("fixedThreadPool - pool size: " + fixedThreadPool.getPoolSize());
        System.out.println("fixedThreadPool - queue size: " + fixedThreadPool.getPoolSize());
        System.out.println("fixedThreadPool - keep alive time: " + fixedThreadPool.getKeepAliveTime(TimeUnit.MILLISECONDS));


        // newSingleThreadExecutor with runnable functional interface
        int k = 0;
        AtomicInteger counter = new AtomicInteger(k);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (; k <= 10; k++) {
            int finalI = k;
            singleThreadExecutor.submit(() -> counter.set(finalI));
            singleThreadExecutor.submit(() -> {
                counter.compareAndSet(finalI, finalI + 1);
            });
        }

        // ThreadPoolExecutor with cached thread pool
        ThreadPoolExecutor cachedThreadPool =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();
        cachedThreadPool.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        cachedThreadPool.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        cachedThreadPool.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        singleThreadExecutor.shutdown();
        System.out.println("cachedThreadPool - pool size: " + cachedThreadPool.getPoolSize());
        System.out.println("cachedThreadPool - queue size: " + cachedThreadPool.getPoolSize());
        System.out.println("cachedThreadPool - keep alive time: " + cachedThreadPool.getKeepAliveTime(TimeUnit.MILLISECONDS));


        // Create a ScheduledThreadPoolExecutor with a given corePoolSize, unbounded maximumPoolSize and
        // zero keepAliveTime. Schedule a task for execution in 500 milliseconds.
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);
        scheduledExecutor.schedule(() -> System.out.println("newScheduledThreadPool - Hello World"), 500, TimeUnit.MILLISECONDS);
        scheduledExecutor.shutdown();

        //Run a task after 500 milliseconds delay and then repeat it every 100 milliseconds. After scheduling the
        // task, we wait until it fires three times using the CountDownLatch lock. Then we cancel it using the
        // Future.cancel() method
        CountDownLatch lock = new CountDownLatch(3);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("scheduleAtFixedRate - Hello World");
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);
        try {
            lock.await(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
        }
        try {
            if (scheduledFuture.isDone())
                System.out.println("scheduledFuture- " + scheduledFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        scheduledFuture.cancel(true);
    }

}

class CustomRejectedHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task denied: " + r.toString());
    }
}

class CustomThreadFactor implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread th = new Thread(r);
        th.setName("custom-name");
        return th;
    }
}
