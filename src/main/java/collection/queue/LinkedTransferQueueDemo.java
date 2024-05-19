package collection.queue;

/**
 * It implements the TransferQueue and provides an unbounded functionality based on linked nodes.
 * The elements in the LinkedTransferQueue are ordered in FIFO order, with the head pointing to the
 * element that has been on the Queue for the longest time and the tail pointing to the element that
 * has been on the queue for the shortest time. Because of its asynchronous nature, size() traverses
 * the entire collection, so it is not an O(1) time operation.
 *
 * LinkedTransferQueue has used message-passing applications. There are two aspects in which the message will be passed from Producer thread to Consumer thread.
 *
 * a. put(E e): This method is used if the producer wants to enqueue elements without waiting for a consumer.
 * However, it waits till the space becomes available if the queue is full.
 *
 * b. transfer(E e): This method is generally used to transfer an element to a thread that is waiting to
 * receive it, if there is no thread waiting then it will wait till a thread comes to waiting for
 * state as soon as the waiting thread arrives element will be transferred into it.
 * @link: https://www.geeksforgeeks.org/linkedtransferqueue-in-java-with-examples/
 */

import java.util.concurrent.LinkedTransferQueue;

public class LinkedTransferQueueDemo {
    public static void main(String[] args) {
        LinkedTransferQueue<Integer> LTQ = new LinkedTransferQueue<>();

        LTQ.add(7855642);
        LTQ.add(35658786);
        LTQ.add(5278367);
        LTQ.add(74381793);

        System.out.println("Linked Transfer Queue: " + LTQ);

        System.out.println("Size of Linked Transfer Queue: " + LTQ.size());

        // removes the front element and prints it using poll() method
        System.out.println("First element: " + LTQ.poll());

        System.out.println("Linked Transfer Queue: " + LTQ);

        // prints the size of Queue after removal using size() method
        System.out.println("Size of Linked Transfer Queue: " + LTQ.size());

        // Add numbers to end of LinkedTransferQueue using offer() method
        LTQ.offer(20);

        System.out.println("Linked Transfer Queue: " + LTQ);

        // prints the size of Queue after removal using size() method
        System.out.println("Size of Linked Transfer Queue: " + LTQ.size());
    }
}

