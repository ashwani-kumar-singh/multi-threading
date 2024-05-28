package thread.deprecatedmethods;

import thread.producerconsumer.ThreadUtil;

public class SharedResource {
    private boolean isAvailable = false;

    public synchronized void produce(){
        System.out.println("Lock acquired by thread: " + Thread.currentThread().getName());
        isAvailable = true;
        ThreadUtil.sleep(8000);
        System.out.println("Lock released by thread: " + Thread.currentThread().getName());
    }
}
