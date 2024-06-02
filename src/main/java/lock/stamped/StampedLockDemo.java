package lock.stamped;

import java.util.concurrent.locks.StampedLock;

/**
 * ReentrantReadWriteLock has some severe issues with starvation if not handled properly (using fairness may help, but
 * it may be an overhead and compromise throughput). For example, a number of reads but very few writes can cause the
 * writer thread to fall into starvation.
 *
 * StampedLock is made of a stamp and mode, where your lock acquisition method returns a stamp, which is a long value
 * used for unlocking within the finally block. If the stamp is ever zero, that means there's been a failure to
 * acquire access. StampedLock is all about giving us a possibility to perform optimistic reads.
 *
 * Keep one thing in mind: StampedLock is not reentrant, so each call to acquire the lock always returns a new stamp
 * and blocks if there's no lock available, even if the same thread already holds a lock, which may lead to deadlock.
 *
 * StampedLock has three modes of access:
 * a. Reading b. Writing c. Optimistic reading
 *
 * There could be a situation when you acquired the write lock and written something and you wanted to read in the same
 * critical section. So, as to not break the potential concurrent access, we can use the 'tryConvertToReadLock(long stamp)'
 * method to acquire read access.
 *
 * Now suppose you acquired the read lock, and after a successful read, you wanted to change the value. To do so, you
 * need a write lock, which you can acquire using the 'tryConvertToWriteLock(long stamp)' method.
 */
public class StampedLockDemo {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        StampedLock lock = new StampedLock();

        Thread increment = new Thread(() -> resource.increment(lock));
        increment.start();

        Thread decrement = new Thread(() -> resource.decrement(lock));
        decrement.start();

    }
}
