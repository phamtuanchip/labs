import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  private static final int NTHREDS = 10;

  public void example2() {
    // We will store the threads so that we can check if they are done
    List<Thread> threads = new ArrayList<Thread>();
    // We will create 500 threads
    for (int i = 0; i < 500; i++) {
      Runnable task = new MyRunnable(10000000L + i);
      Thread worker = new Thread(task);
      // We can set the name of the thread
      worker.setName(String.valueOf(i));
      // Start the thread, never call method run() direct
      worker.start();
      // Remember the thread for later usage
      threads.add(worker);
    }
    int running = 0;
    do {
      running = 0;
      for (Thread thread : threads) {
        if (thread.isAlive()) {
          running++;
        }
      }
      System.out.println("We have " + running + " running threads. ");
    } while (running > 0);

  }


  public void example1() {
    ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
    for (int i = 0; i < 500; i++) {
      Runnable worker = new MyRunnable(10000000L + i);
      executor.execute(worker);
    }
    // This will make the executor accept no new threads
    // and finish all existing threads in the queue
    executor.shutdown();
    // Wait until all threads are finish
    while (!executor.isTerminated()) {

    }
    System.out.println("Finished all threads");
  }


  public static void main(String[] args) {
    Main m = new Main() ;
    m.example1() ;
    m.example2() ;
  }
}