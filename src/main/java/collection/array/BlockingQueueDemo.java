package collection.array;

import java.util.*;

public class BlockingQueueDemo<E> {
    private final List<E> queue = new LinkedList<E>();

    // limit variable to define capacity
    private final int limit;

    // constructor of BlockingQueue
    public BlockingQueueDemo(int limit) {
        this.limit = limit;
    }

    // enqueue method that throws Exception when you try to insert after the limit
    public synchronized void enqueue(E item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            System.out.println("enqueue operation not allowed, queue is full");
            wait();
        }
        if (this.queue.size() == 0) {
            System.out.println("notify threads waiting for dequeue operation");
            notifyAll();
        }
        this.queue.add(item);
    }

    // dequeue methods that throws Exception when you try to remove element from an empty queue
    public synchronized E dequeue() throws InterruptedException {
        while (this.queue.size() == 0) {
            System.out.println("dequeue operation not allowed, queue is empty");
            wait();
        }
        if (this.queue.size() == this.limit) {
            System.out.println("notify threads waiting for enqueue operation");
            notifyAll();
        }
        return this.queue.remove(0);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueueDemo<Integer> blockingQueueDemo = new BlockingQueueDemo<>(3);
        System.out.println("Adding item to Blocking Queue");
        blockingQueueDemo.enqueue(1);
        blockingQueueDemo.enqueue(2);
        blockingQueueDemo.enqueue(3);
        blockingQueueDemo.enqueue(4);
        blockingQueueDemo.enqueue(5);


        System.out.println("Removing item to Blocking Queue");
        System.out.println("element removed: " + blockingQueueDemo.dequeue());
        System.out.println("element removed: " + blockingQueueDemo.dequeue());

        System.out.println("Adding item to Blocking Queue");
        blockingQueueDemo.enqueue(6);
        blockingQueueDemo.enqueue(7);
        blockingQueueDemo.enqueue(8);

    }
}

