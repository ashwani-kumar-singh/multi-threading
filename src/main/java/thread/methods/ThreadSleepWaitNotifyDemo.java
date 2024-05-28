package thread.methods;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 * sleep(): This causes the thread which sends this message to sleep an interval of time (given in milliseconds).
 * The Thread may sleep more or less than requested.
 * <p>
 * wait(): method causes the current thread to wait indefinitely until another thread either invokes notify() for
 * this object or notifyAll().
 *
 * Why Enclose wait() in a while Loop?
 * Since notify() and notifyAll() randomly wake up threads that are waiting on this object’s monitor, it’s not always
 * important that the condition is met. Sometimes the thread is woken up, but the condition isn’t actually satisfied yet.
 * We can also define a check to save us from spurious wakeups — where a thread can wake up from waiting without ever
 * having received a notification
 * <p>
 * wait(long timeOut) : Using this method, we can specify a timeout after which a thread will be woken up automatically.
 * A thread can be woken up before reaching the timeout using notify() or notifyAll().
 * <p>
 * notify: This method wakes up a single random thread, we can use it to implement mutually exclusive locking where threads
 * are doing similar tasks. But in most cases, it would be more viable to implement notifyAll().
 * <p>
 * notifyAll: This method simply wakes all threads that are waiting on this object’s monitor.
 * <p>
 * interrupt(): If any thread is in sleeping or waiting for a state then using the interrupt() method,
 * we can interrupt the execution of that thread by showing InterruptedException
 *
 * Problem Statement:
 * <p>
 * a. The packet variable denotes the data that is being transferred over the network.
 * b. We have a boolean variable transfer, which the Sender and Receiver will use for synchronization:
 * If this variable is true, the Receiver should wait for Sender to send the message.
 * If it’s false, Sender should wait for Receiver to receive the message.
 * <p>
 * c. The Sender uses the send() method to send data to the Receiver:
 * If transfer is false, we’ll wait by calling wait() on this thread.
 * But when it is true, we toggle the status, set our message, and call notifyAll() to wake up other threads to
 * specify that a significant event has occurred, and they can check if they can continue execution.
 * <p>
 * d. The Receiver will use the receive() method:
 * If the transfer was set to false by Sender, only then will it proceed, otherwise we’ll call wait() on this thread.
 * When the condition is met, we toggle the status, notify all waiting threads to wake up, and return the data packet
 * that was received.
 *
 * @link <a href="https://www.baeldung.com/java-wait-notify">...</a>
 */

public class ThreadSleepWaitNotifyDemo {
    public static void main(String[] args) {
        Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        Thread receiver = new Thread(new Receiver(data));

        sender.start();
        receiver.start();
    }
}

class Sender implements Runnable {
    private final Data data;

    public Sender(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        String[] packets = {
                "First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "Fifth packet",
                "End"
        };

        for (String packet : packets) {
            data.send(packet);
            // Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
    }
}

class Receiver implements Runnable {
    private final Data load;

    public Receiver(Data load) {
        this.load = load;
    }

    @Override
    public void run() {
        for (String receivedMessage = load.receive();
             !"End".equals(receivedMessage);
             receivedMessage = load.receive()) {

            System.out.println(receivedMessage);

            //Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
    }
}

class Data {
    private String packet;
    // if transfer is True then receiver should wait and if it is False sender should wait
    private boolean transfer = true;

    public synchronized void send(String packet) {
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
        transfer = false;

        this.packet = packet;
        notifyAll();
    }

    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
        transfer = true;
        String returnPacket = packet;
        notifyAll();
        return returnPacket;
    }


}

