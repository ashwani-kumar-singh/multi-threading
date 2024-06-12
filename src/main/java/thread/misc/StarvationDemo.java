package thread.misc;

import thread.producerconsumer.ThreadUtil;

import java.util.Arrays;

/**
 * In Starvation, threads are also waiting for each other. But here waiting time is not infinite after some
 * interval of time, waiting thread always gets the resources whatever is required to execute thread run() method.
 *
 * Starvation happens when “greedy” threads make shared resources unavailable for long periods. For instance,
 * suppose an object provides a synchronized method that often takes a long time to return. If one thread invokes
 * this method frequently, other threads that also need frequent synchronized access to the same object will
 * often be blocked.
 *
 * <a href="https://www.geeksforgeeks.org/starvation-and-aging-in-operating-systems/">...</a>
 */
public class StarvationDemo extends Thread {

    static int threadcount = 1;

    @Override
    public void run() {
        System.out.println("Child-" + threadcount++ + " Thread execution starts");
        System.out.println("Child thread execution completes");
        threadcount++;
    }

    public static void main(String[] args) {
        Thread thread1 = new StarvationDemo();
        thread1.setPriority(10);
        Thread thread2 = new StarvationDemo();
        thread1.setPriority(9);
        Thread thread3 = new StarvationDemo();
        thread1.setPriority(8);
        Thread thread4 = new StarvationDemo();
        thread1.setPriority(6);
        Thread thread5 = new StarvationDemo();
        thread1.setPriority(5);
        Thread thread6 = new StarvationDemo();
        thread1.setPriority(1);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        // Here thread6 have to wait because of the other thread. But after waiting for some
        // interval, thread5 will get the chance of execution. It is known as Starvation
        thread6.start();

        ThreadUtil.waitForAllThreadsToComplete(Arrays.asList(thread1, thread2, thread3, thread4,
                thread5, thread6));

        System.out.println("Main thread execution completes");
    }
}
