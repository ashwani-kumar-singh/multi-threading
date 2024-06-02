package lock.readwrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Having a pair of read-write lock allows for a greater level of concurrency in accessing shared data than
 * that permitted by a mutual exclusion lock. It exploits the fact that while only a single thread at
 * a time (a writer thread) can modify the shared data, in many cases any number of threads can
 * concurrently read the data (hence reader threads).
 *
 * A read-write lock will improve performance over the use of a mutual exclusion lock if the frequency
 * of reads is more than writes, duration of the read operations is more than the duration of the
 * writes. It also depends on the contention for the data – that is, the number of threads that will
 * try to read or write the data at the same time.
 *
 * @link <a href="https://www.geeksforgeeks.org/reentrantreadwritelock-class-in-java/">...</a>
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        SharedResource resource1 = new SharedResource();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        Thread producer1 = new Thread(()-> resource1.produce(readWriteLock));
        Thread producer2 = new Thread(()-> resource1.produce(readWriteLock));

        SharedResource resource2 = new SharedResource();
        Thread consumer1 = new Thread(()-> resource2.consume(readWriteLock));
        Thread consumer2 = new Thread(()-> resource2.consume(readWriteLock));

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
