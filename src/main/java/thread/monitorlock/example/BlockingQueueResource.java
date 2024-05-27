package thread.monitorlock.example;

import java.util.concurrent.BlockingQueue;

/**
 *
 * Producer and Consumer are two separate processes. Both processes share a common buffer or queue. The producer
 * continuously produces certain data and pushes it onto the buffer, whereas the consumer consumes those data from
 * the buffer.
 *
 * Both producer and consumer may try to update the queue at the same time. This could lead to data loss or inconsistencies.
 * Producers might be slower than consumers. In such cases, the consumer would process elements fast and wait.
 * In some cases, the consumer can be slower than the producer. This situation leads to a queue overflow issue.
 * In real scenarios, we may have multiple producers, multiple consumers, or both. This may cause the same message
 * to be processed by different consumers.
 *
 * We need to handle resource sharing and synchronization to solve a few complexities:
 *
 * Synchronization on queue while adding and removing data
 * When the queue is empty, the consumer has to wait until the producer adds new data to the queue
 * When the queue is full, the producer has to wait until the consumer consumes data and the queue has some
 * empty buffer
 * <a href="https://www.baeldung.com/java-producer-consumer-problem">...</a>
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
