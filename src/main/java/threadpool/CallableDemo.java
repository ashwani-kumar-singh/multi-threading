package threadpool;

/**
 * Callable is an interface that represents a task that can be executed concurrently and returns a result. It is
 * similar to the java.lang.Runnable interface, but it can return a value and throw a checked exception.
 * <p>
 * For implementing Runnable, the run() method needs to be implemented which does not return anything,
 * while for a Callable, the call() method needs to be implemented which returns a result on completion.
 * Note that a thread can’t be created with a Callable, it can only be created with a Runnable.
 * Another difference is that the call() method can throw checked exception whereas run() cannot.
 * <p>
 * Future as an object that holds the result – it may not hold it right now, but it will do so in the future
 * (once the Callable returns). Thus, a Future is basically one way the main thread can keep track of the progress
 * and result from other threads.
 */

import java.util.Random;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("Main thread started it's execution");
        // FutureTask is a concrete class that implements both Runnable and Future
        FutureTask[] randomNumberTasks = new FutureTask[5];

        for (int i = 0; i < 5; i++) {
            // Create the FutureTask with Callable
            randomNumberTasks[i] = new FutureTask(() -> {
                Random generator = new Random();
                int randomNumber = generator.nextInt(5);
                Thread.sleep(randomNumber * 1000);
                return randomNumber;
            });
            // As it implements Runnable, create Thread with FutureTask
            Thread t = new Thread(randomNumberTasks[i]);
            t.start();
            System.out.println("executing thread: " + t.getName());
        }

        for (int i = 0; i < 5; i++) {
            // As it implements Future, we can call get()
            System.out.println(randomNumberTasks[i].get());
            // This method blocks till the result is obtained
            // The get method can throw checked exceptions like when it is interrupted. This is the reason
        }
        System.out.println("Main thread finished it's execution");
    }
}

