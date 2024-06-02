package lock.readwrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedResource {
    boolean isAvailable = false;

    public void produce(ReadWriteLock lock) {
        try {
            lock.readLock().lock();
            System.out.println("Read lock acquired by thread: " + Thread.currentThread().getName());

            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
            System.out.println("Read lock released by thread: " + Thread.currentThread().getName());
        }
    }

    public void consume(ReadWriteLock lock) {
        try {
            lock.writeLock().lock();
            System.out.println("Write lock acquired by thread: " + Thread.currentThread().getName());
            isAvailable = false;
        } finally {
            lock.writeLock().unlock();
            System.out.println("Write lock released by thread: " + Thread.currentThread().getName());
        }

    }
}
