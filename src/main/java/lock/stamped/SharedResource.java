package lock.stamped;

import thread.producerconsumer.ThreadUtil;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    int a = 10;

    public void increment(StampedLock lock) {
        long stamp = lock.tryOptimisticRead();
        a = 11;
        try {
            Thread.sleep(6000);
            if (lock.validate(stamp)) {
                System.out.println("updated record successfully");
            } else {
                System.out.println("Another thread updated value, rollback !");
                a = 10;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlockRead(stamp);
            System.out.println("read lock released by thread: " + Thread.currentThread().getName());
        }

    }

    public void decrement(StampedLock lock) {
        long stamp = lock.writeLock();
        try {
            System.out.println("write lock acquired by thread: " + Thread.currentThread().getName());
            a = 9;
            System.out.println();
        } finally {
            System.out.println("write lock released by: " + Thread.currentThread().getName());
        }
    }
}
