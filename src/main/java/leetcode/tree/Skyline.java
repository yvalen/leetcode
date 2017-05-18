package leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Skyline {
	public List<int[]> getSkyline_withPriorityQueue(int[][] buildings) {
		List<int[]> result = new ArrayList<>();
		
		// 1. use a int[][] to collect all [start point, - height] and [end point, height] for every building;
		// 2. sort it, firstly based on the first value, then use the second to break ties;
		List<int[]> heights = new ArrayList<>();
		for (int[] building : buildings) {
			heights.add(new int[] {building[0], -building[2]}); // use negative height to differentiate start point from end point 
			heights.add(new int[] {building[1], building[2]});
		}
		Collections.sort(heights, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		
		// use maxHeap to store possible heights
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
		pq.offer(0);
		int prev = 0;
		
		for (int[] height : heights) {
			if (height[1] < 0) { // add height for start point
				pq.offer(-height[1]);
			}
			else {  // remove height for end point
				pq.remove(height[1]);
			}
			
			// compare current max height with previous max height
			int current = pq.peek();
			if (prev != current) {
				result.add(new int[] {height[0], current});
				prev = current;
			}
		}
		return result;
    }
	
	public List<int[]> getSkyline_withTreeMap(int[][] buildings) {
		List<int[]> result = new ArrayList<>();
		
		// 1. use a int[][] to collect all [start point, - height] and [end point, height] for every building;
		// 2. sort it, firstly based on the first value, then use the second to break ties;
		List<int[]> heights = new ArrayList<>();
		for (int[] building : buildings) {
			heights.add(new int[] {building[0], -building[2]}); // use negative height to differentiate start point from end point 
			heights.add(new int[] {building[1], building[2]});
		}
		Collections.sort(heights, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		
		// use TreeMap to store heights, key is height, value is the number of points with this height
		TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> (b - a));
		map.put(0, 1);
		int prevMax = 0;
		for (int[] height : heights) {
			if (height[1] < 0) { // increment height count when at start point
				map.put(-height[1], map.getOrDefault(-height[1], 0) + 1);
			}
			else {
				// decrement height count when at end point
				int count = map.get(height[1]);
				if (count == 1) {
					map.remove(height[1]);
				}
				else {
					map.put(height[1], count-1);
				}
			}
			
			int currentMax = map.firstKey();
			if (prevMax != currentMax) {
				result.add(new int[] {height[0], currentMax});
				prevMax = currentMax;
			}
		}
		return result;
    }
	
	
	private static class Wall {
		int position;
		int height;
		boolean start;
		
		Wall(int position, int height, boolean start) {
			this.position = position;
			this.height = height;
			this.start = start;
		}
		
		public static Comparator comparator = new Comparator<Wall> (){
			@Override
			public int compare(Wall o1, Wall o2) {
				if (o1.position == o2.position) {
					if (o1.start != o2.start) {
						// right wall before left wall
						return Boolean.compare(o2.start, o1.start);
					}
					else if (o1.start) {
						// left wall in descending order
						return Integer.compare(o2.height, o1.height);
					}
					else {
						// right wall in ascending order
						return Integer.compare(o1.height, o2.height);
					}
				}
				else {
					return Integer.compare(o1.position, o2.position);
				}
			}
		};
	}
	
	public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        
        Wall[] walls = new Wall[buildings.length * 2];
        int idx = 0;
        for (int[] building : buildings) {
        	walls[idx++] = new Wall(building[0], building[2], true);
        	walls[idx++] = new Wall(building[1], building[2], false);
        }
        Arrays.sort(walls, Wall.comparator);
        
        TreeMap<Integer, Integer> map = new TreeMap<>(); // height count map sorted in ascending order of height
        map.put(0,  1);
        int prevMax = 0;
        for (Wall wall : walls) {
        	if (wall.start) {
        		map.put(wall.height, map.getOrDefault(wall.height, 0)+1);
        	}
        	else {
        		int count = map.get(wall.height);
        		if (count == 1) {
        			map.remove(wall.height);
        		}
        		else {
        			map.put(wall.height, count-1);
        		}
        	}
        	
        	int currentMax = map.lastKey();  // map is in ascending order
        	if (prevMax != currentMax) {
        		result.add(new int[] {wall.position, currentMax});
        		prevMax = currentMax;
        	}
        }
        
        return result;
	}
	
	
}
