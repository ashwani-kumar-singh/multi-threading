package thread.deprecatedmethods;

import thread.producerconsumer.ThreadUtil;

/**
 * Why Stop, Resume, Suspended got deprecated?
 * STOP: Terminate the thread abruptly, No lock is released, No Resource clean up
 * SUSPEND: Put the thread on hold for temporarily, No lock is released
 * RESUME: Use to resume the execution of suspended thread.
 * <p>
 * Both this operation could let to issue like deadlock.
 */
public class Client {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(sharedResource::produce);

        Thread t2 = new Thread(() -> {
            ThreadUtil.sleep(1000);
            sharedResource.produce();
        });

        t1.start();
        t2.start();
        ThreadUtil.sleep(3000);
        System.out.println("Thread t1 is suspended");
        t1.suspend();
        // first lock is hold by thread t1. since thread t1 got suspended it will not release the lock.
        // thread t2 will be always in waiting state. This is a deadlock situation.

        ThreadUtil.sleep(3000);

        System.out.println("Thread t1 is resumed");
        t1.resume();
        // this will resume suspended thread t1, it will finish it's execution and thread t2 will get the lock
        // for execution

        System.out.println("Main thread finished it's execution");
    }


}
