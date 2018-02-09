package concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleExample {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        Runnable beeper = () -> {
            System.out.println("beep at " + System.currentTimeMillis());
        };

        // schedule beeper to run every 10 seconds every 10 seconds with 10 seconds delay
        ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 10, 10, TimeUnit.SECONDS);

        // stop the scheduler after 60 seconds;
        scheduler.schedule(() -> {
            System.out.println("cancle beeper at " + System.currentTimeMillis());
            beeperHandle.cancel(true);
            }, 60, TimeUnit.SECONDS
        );

        try {
            scheduler.awaitTermination(90, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("shutdown tasks");
        scheduler.shutdownNow();
    }


}
