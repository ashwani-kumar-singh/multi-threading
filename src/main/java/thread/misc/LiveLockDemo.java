package thread.misc;

import thread.producerconsumer.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Livelock occurs when two or more processes continually repeat the same interaction in response to changes
 * in the other processes without doing any useful work. These processes are not in the waiting state, and
 * they are running concurrently. This is different from a deadlock because in a deadlock all processes are in
 * the waiting state.
 *
 * A livelock is similar to a deadlock, except that the states of the processes involved in the livelock
 * constantly change with regard to one another, none progressing. Livelock is a special case of resource
 * starvation; the general definition states that a specific process is not progressing.
 *
 *  Thus Livelock is a special case of resource starvation
 *
 *  <a href="https://www.geeksforgeeks.org/deadlock-starvation-and-livelock/">...</a>
 */
public class LiveLockDemo {
        private static final Lock l1 = new ReentrantLock();
        private static final Lock l2 = new ReentrantLock();

        public static void main(String[] args) {
            new Thread(() -> {
                while (true) {
                    if (!l1.tryLock()) {
                        continue;
                    }
                    if (!l2.tryLock()) {
                        l1.unlock();
                        continue;
                    }
                    ThreadUtil.sleep(6000);
                    l2.unlock();
                    l1.unlock();
                }
            }).start();

            // Thread2
            new Thread(() -> {
                while (true) {
                    if (!l2.tryLock()) {
                        continue;
                    }
                    if (!l1.tryLock()) {
                        l2.unlock();
                        continue;
                    }
                    ThreadUtil.sleep(2000);
                    l1.unlock();
                    l2.unlock();
                }
            }).start();
        }

}
