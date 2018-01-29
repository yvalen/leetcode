package leetcode.design;

import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter {
    private final int capacity;
    private final int tokensPerSecond;
    private long timestamp;
    private int tokens;
    
    public TokenBucketRateLimiter(int tokensPerUnit, TimeUnit timeUnit) {
        this.tokensPerSecond = (int) (tokensPerUnit / timeUnit.toSeconds(1L));
        this.capacity = this.tokensPerSecond;
        this.timestamp = System.currentTimeMillis();
    }
    
    public boolean accquire() {
        long now = System.currentTimeMillis();
        tokens += (now - timestamp) * tokensPerSecond / 1000; 
        if (tokens > capacity) tokens = capacity;
        timestamp = now;
        // return before decrement token so that this request won't accumulate tokens
        if (tokens < 1) return false;
        tokens--;
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(250, TimeUnit.MINUTES);
        TimeUnit.SECONDS.sleep(1L);
        for (int i = 0; i < 5; i++) {
            System.out.println(rateLimiter.accquire());
        }
        TimeUnit.SECONDS.sleep(1L);
        for (int i = 0; i < 5; i++) {
            System.out.println(rateLimiter.accquire());
        }
    }
}
