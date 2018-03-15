package leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

import leetcode.array.ArrayUtil;

/*
 * LEETCODE 218 
 * A city's skyline is the outer contour of the silhouette formed by all the buildings 
 * in that city when viewed from a distance. Now suppose you are given the locations and 
 * height of all the buildings as shown on a cityscape photo (Figure A), write a program 
 * to output the skyline formed by these buildings collectively 
 * The geometric information of each building is represented by a triplet of integers 
 * [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the 
 * ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 
 * 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles 
 * grounded on an absolutely flat surface at height 0.
 * For instance, the dimensions of all buildings in Figure A are recorded as: 
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * The output is a list of "key points" (red dots in Figure B) in the format of 
 * [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the 
 * left endpoint of a horizontal line segment. Note that the last key point, where the rightmost 
 * building ends, is merely used to mark the termination of the skyline, and always has zero height. 
 * Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 * For instance, the skyline in Figure B should be represented as:
 * [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * Notes:
 * - The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * - The input list is already sorted in ascending order by the left x position Li.
 * - The output list must be sorted by the x position.
 * - There must be no consecutive horizontal lines of equal height in the output skyline. 
 * For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 
 * should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 * 
 * Company: Google, Facebook, Microsoft, Twiiter, Yelp
 * Difficulty: hard
 * Similar Questions: 699(FallingSquare)
 * https://briangordon.github.io/2014/08/the-skyline-problem.html
 */
public class Skyline {
    
    // Sweep line Algorithm : Michael Shamos
    // Enter heights of starting points as -ve and ending points as +ve
    // Sort the height arr according to the x coordinates in ascending order
    // Have a (max)pq and keep entering the heights in the pq if height is -ve and remove if +ve. 
    // If the top value in the pq changes we get a point.
    // n^2 worst case when all buildings go into the pq.
    public List<int[]> getSkyline_withPriorityQueue(int[][] buildings) {
        List<int[]> result = new ArrayList<>();

        // 1. use a int[][] to collect all [start point, - height] and [end
        // point, height] for every building;
        // 2. sort it, firstly based on the first value, then use the second to
        // break ties;
        List<int[]> heights = new ArrayList<>();
        for (int[] building : buildings) {
            // use negative height to differentiate start point from end point
            heights.add(new int[] { building[0], -building[2] }); 
            heights.add(new int[] { building[1], building[2] });
        }
        Collections.sort(heights, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // use maxHeap to store possible heights
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(0);
        int prev = 0;

        for (int[] height : heights) {
            if (height[1] < 0) { // add height for start point
                pq.offer(-height[1]);
            } else { // remove height for end point
                pq.remove(height[1]);
            }

            // compare current max height with previous max height
            int current = pq.peek();
            if (prev != current) {
                result.add(new int[] { height[0], current });
                prev = current;
            }
        }
        return result;
    }

    public List<int[]> getSkyline_withTreeMap(int[][] buildings) {
        List<int[]> result = new ArrayList<>();

        // 1. use a int[][] to collect all [start point, - height] and [end
        // point, height] for every building;
        // 2. sort it, firstly based on the first value, then use the second to
        // break ties;
        List<int[]> heights = new ArrayList<>();
        for (int[] building : buildings) {
            // use negative height to differentiate start point from end point
            heights.add(new int[] { building[0], -building[2] }); 
            heights.add(new int[] { building[1], building[2] });
        }
        Collections.sort(heights, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // use TreeMap to store heights, key is height, value is the number of
        // points with this height
        TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> (b - a));
        map.put(0, 1);
        int prevMax = 0;
        for (int[] height : heights) {
            if (height[1] < 0) { // increment height count when at start point
                map.put(-height[1], map.getOrDefault(-height[1], 0) + 1);
            } else {
                // decrement height count when at end point
                int count = map.get(height[1]);
                if (count == 1) {
                    map.remove(height[1]);
                } else {
                    map.put(height[1], count - 1);
                }
            }

            int currentMax = map.firstKey();
            if (prevMax != currentMax) {
                result.add(new int[] { height[0], currentMax });
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

        public static Comparator<Wall> comparator = new Comparator<Wall>() {
            @Override
            public int compare(Wall o1, Wall o2) {
                if (o1.position == o2.position) {
                    if (o1.start != o2.start) {
                        // right wall before left wall
                        return Boolean.compare(o2.start, o1.start);
                    } else if (o1.start) {
                        // left wall in descending order
                        return Integer.compare(o2.height, o1.height);
                    } else {
                        // right wall in ascending order
                        return Integer.compare(o1.height, o2.height);
                    }
                } else {
                    return Integer.compare(o1.position, o2.position);
                }
            }
        };

        @Override
        public String toString() {
            return "Wall [position=" + position + ", height=" + height + ", start=" + start + "]";
        }
        
        
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

        // height count map sorted in ascending order of height
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        int prevMax = 0;
        for (Wall wall : walls) {
            if (wall.start) {
                map.put(wall.height, map.getOrDefault(wall.height, 0) + 1);
            } else {
                int count = map.get(wall.height);
                if (count == 1) {
                    map.remove(wall.height);
                } else {
                    map.put(wall.height, count - 1);
                }
            }

            int currentMax = map.lastKey(); // map is in ascending order
            if (prevMax != currentMax) { // not shadowed
                result.add(new int[] { wall.position, currentMax });
                prevMax = currentMax;
            }
        }

        return result;
    }
    
    public static void main(String[] args) {
        Skyline skyline = new Skyline();
        // [[1,2,1],[1,2,2],[1,2,3]]
        /*
        int[][] buildings = {
                {0,2,3},
                {2,5,3}
        };*/
        int[][] buildings = {
                {1,2,1},
                {1,2,2},
                {1,2,3}
        };
        List<int[]> result = skyline.getSkyline(buildings);
        result.forEach(r -> ArrayUtil.printArray(r, ","));
    }

}
