package lock.reentrant;

/**
 * The traditional way to achieve thread synchronization in Java  a thread can take a lock only once.
 * Synchronized blocks don’t offer any mechanism of a waiting queue and after the exit of one thread,
 * any thread can take the lock. This could lead to starvation of resources for some other thread for
 * a very long period of time.
 * Reentrant Locks are provided in Java to provide synchronization with greater flexibility.
 *
 * As the name says, ReentrantLock allows threads to enter into the lock on a resource more than once.
 * When the thread first enters into the lock, a hold count is set to one. Before unlocking the thread
 * can re-enter into lock again and every time hold count is incremented by one. For every unlocks
 * request, hold count is decremented by one and when hold count is 0, the resource is unlocked.
 *
 * @link https://www.geeksforgeeks.org/reentrant-lock-java/
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class Worker implements Runnable {
    String workerName;
    ReentrantLock re;

    public Worker(ReentrantLock lock, String workerName) {
        this.re = lock;
        this.workerName = workerName;
    }

    public void run() {
        boolean done = false;
        while (!done) {
            // Getting Outer Lock
            boolean ans = re.tryLock();

            // Returns True if lock is free
            if (ans) {
                try {
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    System.out.println("task name - " + workerName + " outer lock acquired at " + ft.format(new Date()) + " Doing outer work");
                    Thread.sleep(1500);

                    // Getting Inner Lock
                    re.lock();
                    try {
                        ft = new SimpleDateFormat("hh:mm:ss");
                        System.out.println("task name - " + workerName + " inner lock acquired at " + ft.format(new Date()) + " Doing inner work");
                        System.out.println("Lock Hold Count - " + re.getHoldCount());
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // Inner lock release
                        System.out.println("task name - " + workerName + " releasing inner lock");
                        re.unlock();
                    }
                    System.out.println("Lock Hold Count - " + re.getHoldCount());
                    System.out.println("task name - " + workerName + " work done");
                    done = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Outer lock release
                    System.out.println("task name - " + workerName + " releasing outer lock");
                    re.unlock();
                    System.out.println("Lock Hold Count - " + re.getHoldCount());
                }
            } else {
                System.out.println("task name - " + workerName + " waiting for lock");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class ReentrantLockDemo2 {
    static final int MAX_T = 2;

    public static void main(String[] args) {
        ReentrantLock rel = new ReentrantLock(true);
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        Runnable w1 = new Worker(rel, "Job1");
        Runnable w2 = new Worker(rel, "Job2");
        Runnable w3 = new Worker(rel, "Job3");
        Runnable w4 = new Worker(rel, "Job4");
        pool.execute(w1);
        pool.execute(w2);
        pool.execute(w3);
        pool.execute(w4);
        pool.shutdown();
    }
}

