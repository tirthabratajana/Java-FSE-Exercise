// ThreadExample.java

// Option 1: Extend Thread class
class MyThread extends Thread {
    private String threadName;

    public MyThread(String name) {
        this.threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread: " + threadName + ", Count: " + i);
                // Pause for a bit to observe interleaving
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println(threadName + " interrupted.");
        }
        System.out.println(threadName + " exiting.");
    }
}

// Option 2: Implement Runnable interface (more flexible as a class can implement multiple interfaces)
class MyRunnable implements Runnable {
    private String threadName;

    public MyRunnable(String name) {
        this.threadName = name;
        System.out.println("Creating Runnable " + threadName);
    }

    public void run() {
        System.out.println("Running Runnable " + threadName);
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Runnable: " + threadName + ", Count: " + i);
                Thread.sleep(70); // Slightly different sleep to show more interleaving
            }
        } catch (InterruptedException e) {
            System.out.println("Runnable " + threadName + " interrupted.");
        }
        System.out.println("Runnable " + threadName + " exiting.");
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        System.out.println("Main thread starting.");

        // Create and start thread using Thread class
        MyThread thread1 = new MyThread("Thread-1");
        thread1.start(); // Calls run() method

        // Create and start thread using Runnable interface
        Thread thread2 = new Thread(new MyRunnable("Thread-2"));
        thread2.start(); // Calls run() method of MyRunnable

        System.out.println("Main thread finished creating and starting threads.");
    }
}