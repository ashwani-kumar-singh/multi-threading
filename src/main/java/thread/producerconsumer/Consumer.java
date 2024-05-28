package thread.producerconsumer;

public class Consumer implements Runnable {
    private boolean running = false;
    private final DataQueue<Message> dataQueue;

    public Consumer(DataQueue<Message> dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        running = true;
        consume();
    }

    public void stop() {
        running = false;
    }

    public void consume() {
        while (running) {
            if (dataQueue.isEmpty()) {
                try {
                    dataQueue.waitIsEmpty();
                } catch (InterruptedException e) {
                    System.out.println("Error while waiting to Consume messages.");
                    break;
                }
            }
            // avoid spurious wake-up
            if (!running) {
                break;
            }
            Message message = dataQueue.take();
            useMessage(message);
            //Sleeping on random time to make it realistic
            ThreadUtil.sleep((long) (Math.random() * 50));
        }
        System.out.println("Consumer Stopped");
    }

    private void useMessage(Message message) {
        if (message != null) {
            System.out.println(String.format("[%s] Consuming Message. Id: %d, Data: %s",
                    Thread.currentThread().getName(), message.getId(), message.getData()));
        }
    }

}
