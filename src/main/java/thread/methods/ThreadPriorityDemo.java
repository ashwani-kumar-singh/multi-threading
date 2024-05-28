package thread.methods;

import thread.producerconsumer.ThreadUtil;

import java.util.Arrays;

/**
 * Thread Priority {1, 2, ,3 ...10} it provides hint to thread scheduler which thread to execute next,
 * it is not guaranteed to follow thread priority.
 * When a new thread is created, it inherits the priority of its parent.
 */
public class ThreadPriorityDemo {
    public static void main(String[] args) {
        System.out.println("Main method start | thread name: " + Thread.currentThread().getName());

        Thread thread1 = new Thread(() -> System.out.println("Executed by thread: " + Thread.currentThread().getName()));
        thread1.start();

        Thread thread2 = new Thread(() -> System.out.println("Executed by thread: " + Thread.currentThread().getName()));
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread2.start();

        Thread thread3 = new Thread(() -> System.out.println("Executed by thread: " + Thread.currentThread().getName()));
        thread3.setPriority(Thread.MIN_PRIORITY);
        thread3.start();

        ThreadUtil.waitForAllThreadsToComplete(Arrays.asList(thread1, thread2, thread3));

        System.out.println("Priority of Thread1: " + thread1.getPriority());
        System.out.println("Priority of Thread2: " + thread2.getPriority());
        System.out.println("Priority of Thread3: " + thread3.getPriority());
        System.out.println("Priority of Main Thread: " + Thread.currentThread().getPriority());

        System.out.println("Main method end | thread name: " + Thread.currentThread().getName());

    }
}
