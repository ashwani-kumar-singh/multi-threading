package lock.semaphore;

import java.util.concurrent.Semaphore;

/**
 * A semaphore controls access to a shared resource through the use of a counter.
 * If the semaphore’s count is greater than zero, then the thread acquires a permit, which causes the semaphore’s
 * count to be decremented.
 * Otherwise, the thread will be blocked until a permit can be acquired.
 * When the thread no longer needs an access to the shared resource, it releases the permit, which causes the
 * semaphore’s count to be incremented.
 * If there is another thread waiting for a permit, then that thread will acquire a permit at that time.
 *
 * <a href="https://www.geeksforgeeks.org/mutex-vs-semaphore/">...</a>
 * <a href="https://www.geeksforgeeks.org/difference-between-binary-semaphore-and-mutex/">...</a>
 * <a href="https://www.geeksforgeeks.org/priority-inversion-what-the-heck/">...</a>
 * <a href="https://www.baeldung.com/java-mutex">...</a>
 * <a href="https://stackoverflow.com/questions/2332765/what-is-the-difference-between-lock-mutex-and-semaphore">...</a>
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Semaphore semaphore = new Semaphore(2);

        Thread thread1 = new Thread(()-> resource.increment(semaphore));
        Thread thread2 = new Thread(()-> resource.increment(semaphore));
        Thread thread3 = new Thread(()-> resource.increment(semaphore));
        Thread thread4 = new Thread(()-> resource.increment(semaphore));


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
