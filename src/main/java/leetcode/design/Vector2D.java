package leetcode.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/*
 * Implement an iterator to flatten a 2d vector.
 * For example, Given 2d vector =
 * [
 * 	[1,2],
 * 	[3],
 * 	[4,5,6]
 * ]
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 * Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java. 
 */
public class Vector2D implements Iterator<Integer> {
    private Iterator<List<Integer>> i;
    private Iterator<Integer> j;

    public Vector2D(List<List<Integer>> vec2d) {
        i = vec2d.iterator();
    }

    @Override
    public boolean hasNext() {
        while ((j == null || !j.hasNext()) && i.hasNext()) {
            j = i.next().iterator();
        }
        return j == null ? false : j.hasNext(); // need to do null check for j
                                                // for [] as input
    }

    @Override
    public Integer next() {
        // if (!hasNext()) throw new NoSuchElementException();
        hasNext(); // need to call hasNext() to initialize/update j
        return j.next();
    }

    /*
     * private final List<List<Integer>> vec2d; private final int size; private
     * int index; private Iterator<Integer> itr;
     * 
     * public Vector2D(List<List<Integer>> vec2d) { this.vec2d = vec2d; size =
     * vec2d.size(); index = 0; if (vec2d != null && vec2d.size() > 0) { itr =
     * vec2d.get(0).iterator(); } }
     * 
     * @Override public boolean hasNext() { if (itr == null) return false;
     * 
     * if (itr.hasNext()) return true;
     * 
     * while (!itr.hasNext() && index < size-1) { // need to loop here to handle
     * empty list index++; itr = vec2d.get(index).iterator(); }
     * 
     * return itr.hasNext(); }
     * 
     * @Override public Integer next() { return (itr == null) ? null :
     * itr.next(); }
     */

    public static void main(String[] args) {
        // List<List<Integer>> vec2d = Arrays.asList(Arrays.asList(1, 2),
        // Arrays.asList(3), Arrays.asList(4, 5, 6));
        List<List<Integer>> vec2d = Arrays.asList(Collections.emptyList(), Collections.emptyList(), Arrays.asList(-1));
        Vector2D v2d = new Vector2D(vec2d);
        while (v2d.hasNext()) {
            System.out.print(v2d.next() + " ");
        }
    }

}
