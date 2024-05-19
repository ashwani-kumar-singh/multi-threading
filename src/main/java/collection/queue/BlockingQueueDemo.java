package collection.queue;

/**
 * ArrayBlockingQueue class is a bounded blocking queue backed by an array. By bounded,
 * it means that the size of the Queue is fixed. Once created, the capacity cannot be changed.
 * Attempts to put an element into a full queue will result in the operation blocking.
 * Similarly, attempts to take an element from an empty queue will also be blocked.
 *
 * @link https://www.geeksforgeeks.org/blockingqueue-interface-in-java/
 * https://www.geeksforgeeks.org/arrayblockingqueue-class-in-java/
 * https://www.geeksforgeeks.org/linkedblockingqueue-class-in-java/
 *
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        // define capacity of ArrayBlockingQueue
        int capacity = 5;

        // create object of ArrayBlockingQueue
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(capacity);

        // Add elements to ArrayBlockingQueue using put
        // method
        queue.put("StarWars");
        queue.put("SuperMan");
        queue.put("Flash");
        queue.put("BatMan");
        queue.put("Avengers");
        //this will block the current thread only for 1 sec
        System.out.println("1 sec waiting time: " + queue.offer("Wanda", 1000, TimeUnit.MILLISECONDS));

        //this will  block the current thread
        //queue.put("Wanda");

        System.out.println("queue contains " + queue);

        // remove some elements
        queue.remove();
        queue.remove();

        // Add elements to ArrayBlockingQueue
        // using put method
        queue.put("CaptainAmerica");
        queue.put("Thor");

        System.out.println("queue contains " + queue);


       // create object of LinkedBlockingQueue using LinkedBlockingQueue() constructor
        BlockingQueue<Integer> lbq = new LinkedBlockingQueue<>();

        // add  numbers
        lbq.add(1);
        lbq.add(2);
        lbq.add(3);
        lbq.add(4);
        lbq.add(5);

        // print queue
        System.out.println("LinkedBlockingQueue:" + lbq);
    }
}

