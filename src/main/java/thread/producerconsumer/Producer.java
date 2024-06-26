package thread.producerconsumer;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private static final AtomicInteger idSequence = new AtomicInteger(0);
    private boolean running = false;
    private final DataQueue dataQueue;

    public Producer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        running = true;
        produce();
    }

    public void stop() {
        running = false;
    }

    public void produce() {
        while (running) {
            if (dataQueue.isFull()) {
                try {
                    dataQueue.waitIsFull();
                } catch (InterruptedException e) {
                    System.out.println("Error while waiting to Produce messages.");
                    break;
                }
            }
            // avoid spurious wake-up
            if (!running) {
                break;
            }
            dataQueue.add(generateMessage());
            System.out.println("Size of the queue is: " + dataQueue.getSize());
            //Sleeping on random time to make it realistic
            ThreadUtil.sleep((long) (Math.random() * 200));
        }

        System.out.println("Producer Stopped");
    }

    private Message generateMessage() {
        Message message = new Message(idSequence.incrementAndGet(), "Message for id: " + idSequence);
        System.out.println(String.format("[%s] Generated Message. Id: %d, Data: %s",
                Thread.currentThread().getName(), message.getId(), message.getData()));

        return message;
    }

}
