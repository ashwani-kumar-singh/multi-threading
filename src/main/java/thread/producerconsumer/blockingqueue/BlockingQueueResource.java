package thread.producerconsumer.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue has two primary methods i.e. put() and take()
 * put() : void put(E e) throws InterruptedException
 * e is the element to be added
 * InterruptedException is thrown if the thread is interrupted while waiting
 *
 * take(): E take() throws InterruptedException
 * returns head of the queue
 * InterruptedException is thrown if the thread is interrupted while waiting
 *
 * BlockingQueue also has add(E e) and remove() methods. But these methods must not be used for
 * producer-consumer problems because:
 * a. add will throw IllegalStateException if the queue is full.
 * b. remove returns a boolean, but an element is to be returned.
 */

public class BlockingQueueResource {
    private final BlockingQueue<String> messageQueue;

    public BlockingQueueResource(BlockingQueue<String> messageQueue) {
        this.messageQueue = messageQueue;
    }

    public synchronized void addMessage(String message) {
        System.out.println("Add message to queue: " + message);
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void consumeMessage() {
        try {
            String message = messageQueue.take();
            System.out.println("Consumed message from queue: " + message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
