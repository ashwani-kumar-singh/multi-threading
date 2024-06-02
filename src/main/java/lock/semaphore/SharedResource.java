package lock.semaphore;

import java.util.concurrent.Semaphore;

public class SharedResource {
    int count = 0;

    public void increment(Semaphore semaphore) {
        try {
            semaphore.acquire();
            System.out.println("count value update to " + ++count + " acquired by current thread: " +
                    Thread.currentThread().getName());
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
            System.out.println("lock released by thread: " + Thread.currentThread().getName());
        }
    }


}
