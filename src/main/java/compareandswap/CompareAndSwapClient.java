package compareandswap;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSwapClient {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        for (int i = 1; i <= 100; i++) {
            Thread t1 = new Thread(resource::increment);
            t1.start();
        }
    }

}

class SharedResource {
    AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        System.out.println("counter value: " + counter.getAndIncrement() + " update by thread: " +
                Thread.currentThread().getName());

    }
}
