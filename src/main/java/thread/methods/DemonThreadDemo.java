package thread.methods;

import thread.producerconsumer.ThreadUtil;

/**
 * daemon threads are low-priority threads that run in the background to perform tasks such as garbage collection or
 * provide services to user threads. The life of a daemon thread depends on the mercy of user threads,
 * meaning that when all user threads finish their execution, the Java Virtual Machine (JVM)
 * automatically terminates the daemon thread.
 *
 * To put it simply, daemon threads serve user threads by handling background tasks and have no role other than
 * supporting the main execution.
 *
 * Some examples of daemon threads in Java in include
 * 1. garbage collection (gc).
 * 2. Auto saving in editor
 *
 * Properties of Java Daemon Thread
 *
 * a. No Preventing JVM Exit: Daemon threads cannot prevent the JVM from exiting when all user threads finish their
 * execution. If all user threads complete their tasks, the JVM terminates itself, regardless of whether any daemon
 * threads are running.
 * b. Automatic Termination: If the JVM detects a running daemon thread, it terminates the thread and subsequently
 * shuts it down. The JVM does not check if the daemon thread is actively running; it terminates it regardless.
 *
 * c. Low Priority: Daemon threads have the lowest priority among all threads in Java.
 *
 * Note: Whenever the last non-daemon thread terminates, all the daemon threads will be terminated automatically.
 *
 * If you call the setDaemon() method after starting the thread, it would throw IllegalThreadStateException.
 *
 * @link <a href="https://www.geeksforgeeks.org/daemon-thread-java/">...</a>
 */
public class DemonThreadDemo {
    public static void main(String[] args) {
        System.out.println("main thread started execution");
        Thread thread = new Thread(() -> {
            System.out.println("start by thread: " + Thread.currentThread().getName());
            ThreadUtil.sleep(30000);
            System.out.println("end by thread: " + Thread.currentThread().getName());
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("is thread a demon thread: " + thread.isDaemon());
        System.out.println("main thread finished execution");
    }
}
