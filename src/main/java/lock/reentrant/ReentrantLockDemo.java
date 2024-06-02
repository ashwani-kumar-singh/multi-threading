package lock.reentrant;

import thread.producerconsumer.ThreadUtil;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        SharedResource resource1 = new SharedResource();
        Thread thread1 = new Thread(()-> resource1.producer(reentrantLock));
        thread1.start();

        SharedResource resource2 = new SharedResource();
        Thread thread2 = new Thread(()-> resource2.producer(reentrantLock));
        ThreadUtil.sleep(1000);
        System.out.println("Lock Queue Length - " + reentrantLock.getQueueLength());
        System.out.println("Lock has queue threads - " + reentrantLock.hasQueuedThreads());
        thread2.start();
    }

}
