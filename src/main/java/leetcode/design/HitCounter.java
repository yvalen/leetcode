package leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

/*
 * LEETCODE 362
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
 * https://nuttynanaus.wordpress.com/2014/03/09/software-engineer-interview-questions/
 * 
 * Follow up: What if the number of hits per second could be very large? Does your design scale? 
 * Concurrency: http://www.nurkiewicz.com/2014/03/simplifying-readwritelock-with-java-8.html
 * http://blog.gainlo.co/index.php/2016/09/12/dropbox-interview-design-hit-counter/
 * 
 * Company: Dropbox, Google
 * Difficulty: medium
 */
public class HitCounter {
	private static final int CAPACITY = 300;
	private int[] hits;
	//private final int[] timestamps;
	private int lastTimestamp;
	private int lastPos;
	private int totalHits;

	public HitCounter() {
		hits = new int[CAPACITY];

		//hits = new int[300];
		// timestamps = new int[300];
	}

	private void adjust(int timestamp) {
		int gap = Math.min(CAPACITY, timestamp-lastTimestamp);
		for (int i = 0; i < gap; i++) {
			lastPos = (lastPos+1) % CAPACITY;
			totalHits -= hits[lastPos];
			hits[lastPos] = 0;
		}
		lastTimestamp = timestamp;
	}
	
	public void hit(int timestamp) {
		adjust(timestamp);
		hits[lastPos]++;
		totalHits++;
		/*
        int idx = timestamp % 300;
        if (timestamps[idx] != timestamp) {
            timestamps[idx] = timestamp;
            hits[idx] = 1;
        } else {
            hits[idx]++;
        }
		 */
	}

	public int getHits(int timestamp) {
		int total = 0;
		int gap = Math.min(CAPACITY, timestamp-lastTimestamp);
		for (int i = 0, idx = lastPos; i < gap; i++) {
			total += hits[idx];
			idx = (idx-1) % CAPACITY;
		}
		return total;
		
		//adjust(timestamp);
		//return totalHits;
		/*
		int total = 0;
		for (int i = 0; i < 300; i++) {
			if (timestamp - timestamps[i] < 300) {
				total += hits[i];
			}
		}
		return total;
		*/
	}
	
	
	//
	// with map
	// 
	private static class Entry {
		private int timestamp;
		private int count;
	}
	private Entry[] entries;
	public HitCounter(int n) {
		entries = new Entry[300];
		for (int i = 0; i < 300; i++) {
			entries[i] = new Entry();
		}
	}
	public void hit_withEntry(int timestamp) {
		Entry entry = entries[timestamp % 300];
		if (entry.timestamp != timestamp) {
			entry.timestamp = timestamp;
			entry.count = 1;
		}
		else {
			entry.count++;
		}
	}
	public int getHits_withEntry(int timestamp) {
		int sum = 0;
		for (int i = 0; i < 300; i++) {
			Entry entry = entries[i];
			if (timestamp - entry.timestamp < 300) {
				sum += entry.count;
			}
		}
		return sum;
	}
	

	public static void main(String[] args) {
		HitCounter hc = new HitCounter();
		hc.hit(1);
		hc.hit(2);
		hc.hit(3);
		System.out.println(hc.getHits(4));
		hc.hit(300);
		System.out.println(hc.getHits(300));
		System.out.println(hc.getHits(301));
	}
}
