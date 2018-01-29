package leetcode.design;

/*
 * Design a hit counter which counts the number of hits received in the past 5 minutes. 
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume 
 * that calls are being made to the system in chronological order (ie, the timestamp is 
 * monotonically increasing). You may assume that the earliest timestamp starts at 1. It is 
 * possible that several hits arrive roughly at the same time.
 * Example:
 * HitCounter counter = new HitCounter();
 * 
 * // hit at timestamp 1.
 * counter.hit(1);
 * 
 * // hit at timestamp 2.
 * counter.hit(2);
 * 
 * // hit at timestamp 3.
 * counter.hit(3);
 * 
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 * 
 * // hit at timestamp 300.
 * counter.hit(300);
 * 
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 * 
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301); 
 * 
 * Follow up: What if the number of hits per second could be very large? Does your design scale? 
 * Concurrency: http://www.nurkiewicz.com/2014/03/simplifying-readwritelock-with-java-8.html
 * 
 * Company: Dropbox, Google
 * Difficulty: medium
 */
public class HitCounter {
    private final int[] hits;
    private final int[] timestamps;

    public HitCounter() {
        hits = new int[300];
        timestamps = new int[300];
    }

    public void hit(int timestamp) {
        int idx = timestamp % 300;
        if (timestamps[idx] != timestamp) {
            timestamps[idx] = timestamp;
            hits[idx] = 1;
        } else {
            hits[idx]++;
        }
    }

    public int getHits(int timestamp) {
        int total = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - timestamps[i] < 300) {
                total += hits[i];
            }
        }
        return total;
    }
    
    public static void main(String[] args) {
        System.out.println((System.currentTimeMillis() / 1000) %10);
    }
}
