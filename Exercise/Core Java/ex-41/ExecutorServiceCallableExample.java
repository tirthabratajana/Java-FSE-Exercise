// ExecutorServiceCallableExample.java
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceCallableExample {

    // A Callable task that returns a result
    static class SumCallable implements Callable<Integer> {
        private int start;
        private int end;

        public SumCallable(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
                // Simulate some work
                // Thread.sleep(5);
            }
            System.out.println("Task " + Thread.currentThread().getName() + ": Sum from " + start + " to " + end + " = " + sum);
            return sum;
        }
    }

    public static void main(String[] args) {
        // 1. Create an ExecutorService with a fixed thread pool
        // This pool will reuse a fixed number of threads to execute tasks.
        int corePoolSize = 3; // Number of threads in the pool
        ExecutorService executor = Executors.newFixedThreadPool(corePoolSize);
        System.out.println("ExecutorService created with " + corePoolSize + " threads.");

        List<Future<Integer>> futures = new ArrayList<>();

        // 2. Submit multiple Callable tasks
        futures.add(executor.submit(new SumCallable(1, 10)));
        futures.add(executor.submit(new SumCallable(11, 20)));
        futures.add(executor.submit(new SumCallable(21, 30)));
        futures.add(executor.submit(new SumCallable(31, 40))); // This task will wait for a thread to become available
        futures.add(executor.submit(new SumCallable(41, 50)));

        int totalSum = 0;
        // 3. Collect results using Future.get()
        System.out.println("\n--- Collecting Results ---");
        for (Future<Integer> future : futures) {
            try {
                // get() blocks until the task is complete and returns its result
                Integer result = future.get();
                totalSum += result;
                System.out.println("Received result: " + result);
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error while getting result: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("\nTotal sum of all tasks: " + totalSum);

        // Shut down the executor service gracefully
        executor.shutdown(); // Initiates an orderly shutdown
        try {
            // Wait for all tasks to complete or timeout
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Force shutdown if not terminated within timeout
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("ExecutorService shut down.");
    }
}