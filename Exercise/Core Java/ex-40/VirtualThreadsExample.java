// VirtualThreadsExample.java
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VirtualThreadsExample {

    private static final int NUM_THREADS = 100_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Demonstrating Virtual Threads (Java 21+) ---");

        // --- Virtual Threads Example ---
        System.out.println("\nStarting " + NUM_THREADS + " Virtual Threads...");
        Instant startVirtual = Instant.now();

        // Use a virtual thread executor (ForkJoinPool backing for flexibility)
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUM_THREADS; i++) {
                final int threadId = i;
                executor.submit(() -> {
                    // Simulate some work
                    // System.out.println("Virtual Thread: " + threadId + " running."); // Too much output
                    try {
                        Thread.sleep(Duration.ofMillis(1)); // Small delay
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        } // executor.close() is called automatically here, awaiting task completion

        Instant endVirtual = Instant.now();
        System.out.println("All Virtual Threads submitted and completed.");
        System.out.println("Time taken by Virtual Threads: " + Duration.between(startVirtual, endVirtual).toMillis() + " ms");


        // --- Platform (Traditional) Threads Example (Caution: Might run out of memory for large NUM_THREADS) ---
        // For demonstration, we'll use a smaller number if NUM_THREADS is very high
        int numPlatformThreads = Math.min(NUM_THREADS, 5000); // Limit platform threads to avoid OOM

        System.out.println("\nStarting " + numPlatformThreads + " Platform Threads...");
        Instant startPlatform = Instant.now();

        try (var executor = Executors.newFixedThreadPool(100)) { // Limited pool size
            for (int i = 0; i < numPlatformThreads; i++) {
                final int threadId = i;
                executor.submit(() -> {
                    // System.out.println("Platform Thread: " + threadId + " running.");
                    try {
                        Thread.sleep(Duration.ofMillis(1)); // Small delay
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        } // executor.close() is called automatically here

        Instant endPlatform = Instant.now();
        System.out.println("All Platform Threads submitted and completed.");
        System.out.println("Time taken by Platform Threads: " + Duration.between(startPlatform, endPlatform).toMillis() + " ms");

        System.out.println("\nComparison: Virtual threads are much more efficient for high concurrency due to lower overhead.");
    }
}