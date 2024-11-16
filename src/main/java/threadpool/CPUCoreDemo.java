package threadpool;

public class CPUCoreDemo {
    public static void main(String[] args) {
        int avpro = Runtime.getRuntime().availableProcessors();
        multithread(avpro);

    }

    public static void multithread(int avpro) {
        for (int i = 1; i <= avpro; i++) {
            // Creating an object of thread class
            Thread obj = new Thread(() -> {
                // Displaying the name and id of current thread
                System.out.println("Thread running with Id: "
                        + Thread.currentThread().getId()
                        + " and Name: "
                        + Thread.currentThread().getName());
            });
            // starting the new thread
            obj.start();
        }
    }

}
