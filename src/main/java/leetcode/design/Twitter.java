package leetcode.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Design a simplified version of Twitter where users can post tweets, 
 * follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. 
 * Your design should support the following methods:
 * 	postTweet(userId, tweetId): Compose a new tweet.
 * 	getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. 
 * 		- Each item in the news feed must be posted by users who the user followed or by the user herself. 
 * 		- Tweets must be ordered from most recent to least recent.
 * 	follow(followerId, followeeId): Follower follows a followee.
 * 	unfollow(followerId, followeeId): Follower unfollows a followee.
 */
public class Twitter {
	
	private static final class Tweet {
		private final int tweetId;
		private final long createdTime;
	
		public Tweet(int tweetId, long createdTime) {
			this.tweetId = tweetId;
			this.createdTime = createdTime;
		}
	
		public int getTweetId() {
			return tweetId;
		}

		public long getCreatedTime() {
			return createdTime;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + tweetId;
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tweet other = (Tweet) obj;
			if (tweetId != other.tweetId)
				return false;
			return true;
		}
	}
	
	Map<Integer, Set<Integer>> followersMap;
	Map<Integer, Set<Integer>> followingMap;
	Map<Integer, Set<Tweet>> tweetsMap;
	Map<Integer, List<Integer>> feedsMap;
	
	/** Initialize your data structure here. */
    public Twitter() {
    	followersMap = new HashMap<>();
    	followingMap = new HashMap<>();
        tweetsMap = new HashMap<>();
        feedsMap = new HashMap<>();
    }
    
    /** 
     * Compose a new tweet. 
     * */
    public void postTweet(int userId, int tweetId) {
    	Set<Tweet> tweets = tweetsMap.get(userId);
    	if (tweets == null) {
    		tweets = new HashSet<>();
    		tweetsMap.put(userId, tweets);
    	}
    	tweets.add(new Tweet(tweetId, System.currentTimeMillis()));
    }
    
    /** 
     * Retrieve the 10 most recent tweet ids in the user's news feed. 
     * Each item in the news feed must be posted by users who the user followed or by the user herself. 
     * Tweets must be ordered from most recent to least recent. 
     * */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feeds = feedsMap.get(userId);
        
        if (feeds != null && feeds.size() > 0) {
        	List<Integer> result = new ArrayList<>();
        	for (int i = feeds.size() - 1, count = 1; i >= 0 || count <= 10; i--, count++) {
        		result.add(feeds.get(i));
        	}
        	return result;
        }
        
        return Collections.emptyList();
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
    	if (followerId == followeeId) return;
    	
    	Set<Integer> followers = followersMap.get(followeeId);
        if (followers == null) {
        	followers = new HashSet<>();
        	followersMap.put(followeeId, followers);
        }
        boolean newFollower = followers.add(followerId);
    	/*
        if (followers.add(followerId)) {
        	Set<Integer> tweets = tweetsMap.get(followeeId);
        	if (tweets != null) {
        		List<Integer> feeds = feedsMap.get(followerId);
        		if (feeds == null) {
        			feeds = new ArrayList<>();
        			feedsMap.put(followerId, feeds);
        		}
        		feeds.addAll(tweets);
        	}
        }
        */
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
    	if (followerId == followeeId) return;
    	
    	Set<Integer> followers = followersMap.get(followeeId);
        if (followers != null) {
        	followers.remove(followerId);
        }
    	
        List<Integer> feeds = feedsMap.get(followerId);
        /*
        Set<Integer> tweets = tweetsMap.get(followeeId);
        if (feeds != null && tweets != null) {
        	List<Integer> updatedFeeds = feeds.stream().filter(id -> !tweets.contains(id)).collect(Collectors.toList());
        	feedsMap.put(followerId, updatedFeeds);
        }*/
    }
	
    public static void main(String[] args) {
    	Twitter twitter = new Twitter();

    	
    	twitter.postTweet(1, 1);
    	twitter.postTweet(1, 3);
    	twitter.postTweet(1, 101);
    	twitter.postTweet(1, 13);
    	twitter.postTweet(1, 10);
    	twitter.postTweet(1, 2);
    	twitter.postTweet(1, 94);
    	twitter.postTweet(1, 505);
    	twitter.postTweet(1, 333);
    	twitter.postTweet(1, 22);

    	// User 1's news feed should return a list with 1 tweet id -> [5].
    	System.out.println(twitter.getNewsFeed(1));

    	
    	twitter.follow(2, 1);
    	
    	System.out.println(twitter.getNewsFeed(2));
    	
    	twitter.unfollow(2, 1);

    	System.out.println(twitter.getNewsFeed(2));
    }

}
