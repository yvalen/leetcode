package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * There are three threads managed by thread pool. They should be executed in order,
 * thread 2 starts when thread 1 finishes, and thread 3 starts when thread 2 finishes.
 */
public class ThreadSynchronization {
    private static class MyTask implements Runnable {
        private final int taskId;
        private final CountDownLatch latch;
        public MyTask(int taskId, CountDownLatch latch) {
            this.taskId = taskId;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.print("task " + taskId + " started  ");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                System.out.println("task " + taskId + " finished");
                latch.countDown();
            }
        }
    }

    public static void executeWithSingleThreadPool() {
        final CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new MyTask(1, latch));
        executorService.execute(new MyTask(2, latch));
        executorService.execute(new MyTask(3, latch));

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ALL TASKS COMPLETED");

        // shut down
        executorService.shutdown(); // disable new tasks being submitted
        try {
            // Wait a while for existing tasks to terminate
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("task interrupted");
        } finally {
            if (!executorService.isTerminated()) {
                System.err.println("cancel unfinished tasks");
            }
            executorService.shutdownNow(); // Cancel currently executing tasks
            System.out.println("shutdown finished");
        }
    }

    public void executeWithFixedThreadPoolAndCountDownLatch() {
        //CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(1);
        executorService.submit(() -> {
            System.out.print("task 1 started  ");
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("task 1 finished");
                //latch.countDown();
                latch1.countDown();
            }
        });
        executorService.submit(() -> {
            try {
                latch1.await();
                System.out.print("task 2 started  ");
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("task 2 finished");
                //latch.countDown();
                latch2.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                latch2.await();
                System.out.print("task 3 started  ");
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("task 3 finished");
                latch3.countDown();
            }
        });

        try {
            latch3.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ALL TASKS COMPLETED");
        executorService.shutdown();
    }



    public static void main(String[] args) {
        ThreadSynchronization ts = new ThreadSynchronization();

        //IntStream.range(0, 3).forEach(i -> executeWithSingleThreadPool());

        IntStream.range(1, 5).forEach(i -> ts.executeWithFixedThreadPoolAndCountDownLatch());
    }
}
