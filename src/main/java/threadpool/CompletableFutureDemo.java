package threadpool;

import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CompletableFuture is a class in java.util.concurrent package that implements the Future and CompletionStage
 * Interface. It represents a future result of an asynchronous computation.
 * It can be thought of as a container that holds the result of an asynchronous operation that is being executed in
 * a different thread. It provides a number of methods to perform various operations on the result of the async
 * computation.
 * <p>
 * Static methods "runAsync" and "supplyAsync" allow us to create a CompletableFuture instance out of Runnable
 * and Supplier functional types correspondingly.
 * It provide an instance of the Supplier as a lambda expression that does the calculation and returns the result.
 * <p>
 * "thenAccept" -> method receives a Consumer and passes it the result of the computation. If we don’t need to return
 * a value down the Future chain, we can use an instance of the Consumer functional interface.
 * <p>
 * "thenRun" -> if we neither need the value of the computation nor want to return some value at the end of the chain,
 * then we can pass a Runnable lambda to the thenRun method. In the following example, we simply print a line in the
 * console after calling the future.get().
 * <p>
 * thenApply() -> method of the CompletableFuture class to chain multiple Future tasks together. This
 * method takes a Function argument, which is applied to the result of the previous Future task, and returns a new
 * CompletableFuture that completes with the result of the function.
 * <p>
 * thenCombine() -> method of the CompletableFuture class to combine the results of two Future tasks.
 * This method takes another CompletableFuture and a BiFunction argument, which is applied to the results of both
 * Future tasks, and returns a new CompletableFuture that completes with the result of the function.
 * <p>
 * Difference Between thenApply() and thenCompose()
 * <p>
 * thenApply() -> We can use this method to work with the result of the previous call. However, a key point to
 * remember is that the return type will be combined of all calls.
 * <p>
 * thenCompose() -> uses the previous stage as the argument. It will flatten and return a Future with the result directly,
 * rather than a nested future as we observed in thenApply().
 * <p>
 * allOf() -> method of the CompletableFuture class to create a new CompletableFuture that will complete when all of
 * the specified Future tasks have completed.
 * <p>
 * exceptionally() -> method of the CompletableFuture class to specify a callback that will be executed if the
 * Future task completes exceptionally (i.e., if it throws an exception). This method takes a Function argument,
 * which is applied to the exception thrown by the task, and returns a default value to be used as the result of
 * the CompletableFuture.
 * <p>
 * handle() -> CompletableFuture class allows us to handle it in a special handle method. This method receives two
 * parameters: a result of a computation (if it finished successfully) and the exception thrown
 * (if some computation step did not complete normally).
 * we use the handle method to provide a default value when the asynchronous computation
 * of a greeting was finished with an error.
 *
 * thenApplyAsync() -> These methods are usually intended for running a corresponding execution step in another thread.
 * In contrast, the Async method without the Executor argument runs a step using the common fork/join pool
 * implementation of Executor that is accessed with the ForkJoinPool.commonPool(),
 *
 * <a href="https://connect2grp.medium.com/understating-java-future-and-callable-features-aec70d2aef6">...</a>
 * <a href="https://www.baeldung.com/java-completablefuture">...</a>
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws InterruptedException {
        try {
            System.out.println("completableFuture.complete method result -> " + calculateAsync().get());
            System.out.println("completableFuture.thenApply method result -> " + thenApply().get());
            System.out.println("completableFuture.thenAccept method result -> " + thenAccept().get());
            System.out.println("completableFuture.thenRun method result -> " + thenRun().get());
            System.out.println("completableFuture.thenCombine method result -> " + thenCombine().get());
            System.out.println("completableFuture.thenCompose method result -> " + thenCompose().get());
            System.out.println("completableFuture.allOf method result -> " + allOf().get());
            System.out.println("completableFuture.exceptionally method result -> " + exceptionally().get());
            System.out.println("completableFuture.handle method result -> " + handle().get());
            System.out.println("completableFuture.thenApplyAsync method result -> " + thenApplyAsync().get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });
        executorService.shutdown();
        return completableFuture;
    }

    private static CompletableFuture<Void> thenAccept() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<Void> future = completableFuture.thenAccept(s -> System.out.println("Computation returned: " + s));
        return future;
    }

    private static CompletableFuture<Void> thenRun() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<Void> future = completableFuture.thenRun(() -> System.out.println("Computation finished."));
        return future;
    }

    private static CompletableFuture<Integer> thenApply() throws RuntimeException {
        CompletableFuture<Integer> finalResult = CompletableFuture.supplyAsync(() -> 0).thenApply(s -> s + 1);
        return finalResult;
    }


    public static CompletableFuture<Integer> thenCompose() {
        CompletableFuture<Integer> finalResult = CompletableFuture.supplyAsync(() -> 0)
                .thenCompose(futureResult -> CompletableFuture.supplyAsync(() -> 10 + futureResult));
        return finalResult;
    }

    public static CompletableFuture<String> thenCombine() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> " World"), (s1, s2) -> s1 + s2);
        return completableFuture;
    }

    public static CompletableFuture<Void> allOf() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        //using stream and CompletableFuture.join method
        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        return combinedFuture;
    }

    public static CompletableFuture<String> exceptionally() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Something went wrong");
        }, executor);
        CompletableFuture<String> future2 = future.exceptionally(ex -> {
            System.out.println("Exception: " + ex.getMessage());
            return "Default value";
        });
        executor.shutdown();
        return future2;
    }

    public static CompletableFuture<String> handle() {
        String name = null;
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> s != null ? s : "Hello, Stranger!");
        return completableFuture;
    }

    public static CompletableFuture<String> thenApplyAsync() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future = completableFuture.thenApplyAsync(s -> s + " World");
        return future;
    }

}
