package thread.producerconsumer.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

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
public class Client {

    public static void main(String[] args) {
        System.out.println("Main method start | thread name: " + Thread.currentThread().getName());
        BlockingQueueResource sharedResource = new BlockingQueueResource(new ArrayBlockingQueue<>(5));

        Thread producer = new Thread(() -> {
            Thread producerThread = Thread.currentThread();
            producerThread.setName("Producer");
            System.out.println("Producer Thread : " + producerThread.getName());
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(i * 100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                sharedResource.addMessage(String.valueOf(i));
            }
        });

        Thread consumer = new Thread(() -> {
            Thread consumerThread = Thread.currentThread();
            consumerThread.setName("Consumer");
            System.out.println("Consumer Thread : " + consumerThread.getName());

            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(i * 200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                sharedResource.consumeMessage();
            }
        });

        producer.start();
        consumer.start();

        System.out.println("Main method end | thread name: " + Thread.currentThread().getName());
    }

}
