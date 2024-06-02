package lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    boolean isAvailable = false;

    public void producer(ReentrantLock lock) {
        while (!isAvailable) {
            boolean ans = lock.tryLock();
            // Returns True if lock is free
            if (ans) {
                System.out.println("lock is acquired by thread: " + Thread.currentThread().getName());
                isAvailable = true;
                System.out.println("Lock Hold Count - " + lock.getHoldCount());
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // this will make the read running endless due to while loop
                    //isAvailable = false;
                    lock.unlock();
                    System.out.println("lock is released by thread: " + Thread.currentThread().getName());
                }

            }
        }
    }

}
