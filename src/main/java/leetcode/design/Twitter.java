package leetcode.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Twitter {
	private final Map<Integer, Set<Integer>> following;
    private final Map<Integer, List<Tweet>> tweets;
    private long count;
    
    private static class Tweet implements Comparable<Tweet> {
    	int id;
    	long timestamp;
    	
		public Tweet(int id, long timestamp) {
			super();
			this.id = id;
			this.timestamp = timestamp;
		}

		@Override
		public int compareTo(Tweet o) {
			return this.timestamp == o.timestamp ? this.id - o.id : (int) (this.timestamp - o.timestamp);
		}
    }
    
    /** Initialize your data structure here. */
    public Twitter() {
        following = new HashMap<>();
        tweets = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!tweets.containsKey(userId)) {
            tweets.put(userId, new ArrayList<>());
        }
        tweets.get(userId).add(new Tweet(tweetId, count++));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> pq = new PriorityQueue<>(10);
        
        addTweetsToFeed(pq, userId);
        
        if (following.get(userId) != null) {
        	for (int followee : following.get(userId)) {
        		addTweetsToFeed(pq, followee);
        	}
        }
        	
        List<Integer> result = new ArrayList<>(10);
        while (pq.size() > 0) {
            result.add(pq.poll().id);
        }
        
        Collections.reverse(result);
        
        return result;
    }
    
    private void addTweetsToFeed(PriorityQueue<Tweet> pq, Integer userId) {
    	List<Tweet> tweetList = tweets.get(userId);
    	if (tweetList == null) return;
    	
    	for (int i = tweetList.size() - 1; i >= 0 && i >= tweetList.size() - 10; i--) {
    		Tweet tweet = tweetList.get(i);
    		if (pq.size() < 10) {
    			pq.offer(tweet);
    		}
    		else if (pq.peek().compareTo(tweet) < 0 ) {
    			pq.poll();
    			pq.offer(tweet);
    		}
    	}
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
    	if (followerId == followeeId) return;
        if (!following.containsKey(followerId)) {
            following.put(followerId, new HashSet<>());
        }      
        following.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (following.containsKey(followerId)) {
            following.get(followerId).remove(followeeId);
        }
    }
    
    public static void main(String[] args)  {
    	Twitter twitter = new Twitter();
    	
    	// User 1 posts a new tweet (id = 5).
    	twitter.postTweet(1, 5);
    	
    	// User 1 posts a new tweet (id = 3).
    	twitter.postTweet(1, 3);

    	// User 1's news feed should return a list with 1 tweet id -> [5].
    	System.out.println(twitter.getNewsFeed(1));

    	// User 1 follows user 2.
    	twitter.follow(1, 2);

    	// User 2 posts a new tweet (id = 6).
    	twitter.postTweet(2, 6);

    	// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
    	// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
    	System.out.println(twitter.getNewsFeed(1));

    	// User 1 unfollows user 2.
    	twitter.unfollow(1, 2);

    	// User 1's news feed should return a list with 1 tweet id -> [5],
    	// since user 1 is no longer following user 2.
    	twitter.getNewsFeed(1);System.out.println(twitter.getNewsFeed(1));
    }
}
