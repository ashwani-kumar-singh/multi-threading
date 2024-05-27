package thread.monitorlock.example;

import java.util.concurrent.ArrayBlockingQueue;

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
