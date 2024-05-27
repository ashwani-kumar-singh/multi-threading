package thread.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class DataQueue {
    private final Queue<Message> queue = new LinkedList<>();
    private final int maxSize;
    private final Object IS_FULL = new Object();
    private final Object IS_EMPTY = new Object();

    DataQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getSize() {
        return queue.size();
    }

    public boolean isFull() {
        return queue.size() == this.maxSize;
    }

    public void add(Message message) {
        queue.add(message);
        notifyIsNotEmpty();
    }

    public Message poll() {
        Message mess = queue.poll();
        notifyIsNotFull();
        return mess;
    }

    public Queue<Message> getQueue() {
        return queue;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // When the queue is full, the producer waits on the IS_FULL object. And, as soon as we remove a message,
    // we notify that the queue is no longer full.
    public void waitIsFull() throws InterruptedException {
        synchronized (IS_FULL) {
            IS_FULL.wait();
        }
    }

    // When the consumer polls a message, the dataQueue notifies producers through the notifyIsNotFull method.
    private void notifyIsNotFull() {
        synchronized (IS_FULL) {
            IS_FULL.notify();
        }
    }

    //If the queue is empty, the consumer waits on the IS_EMPTY object. And, as soon as we add a message,
    // we notify that the queue is no longer empty.
    public void waitIsEmpty() throws InterruptedException {
        synchronized (IS_EMPTY) {
            IS_EMPTY.wait();
        }
    }

    //When the producer adds a message, the dataQueue notifies consumers through the notifyIsNotEmpty
    public void notifyIsNotEmpty() {
        synchronized (IS_EMPTY) {
            IS_EMPTY.notify();
        }
    }
}
