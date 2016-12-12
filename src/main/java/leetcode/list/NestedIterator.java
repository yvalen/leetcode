package leetcode.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {
	
	
	private Stack<NestedInteger> stack;
	
	public interface NestedInteger {
		/** 
		 * @return true if this NestedInteger holds a single integer, rather than a nested list.
		 */
		public boolean isInteger();
		
		/**
		 * @return the single integer that this NestedInteger holds if it holds a single integer.
		 * Return null if this NestedInteger holds a nested list
		 */
		public Integer getInteger();
		
		/** 
		 * @return the nested list that this NestedInteger holds if it holds a nested list
		 * Return null if this NestedInteger holds a single integer
		 */
		public List<NestedInteger> getList();

	}
	
	public NestedIterator(List<NestedInteger> nestedList) {
		stack = new Stack<>();
		for(int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
	}
	
	@Override
	public Integer next() {
		Integer result = null;
		if (hasNext()) {
	    	result = stack.pop().getInteger();
		}
		
	    return result;
	}

	@Override
	public boolean hasNext() {
		while (!stack.isEmpty()) {
			NestedInteger nestedInt = stack.peek();
			if (nestedInt.isInteger()) return true;
			
			nestedInt = stack.pop();
			List<NestedInteger> list = nestedInt.getList();
			for (int i = list.size() - 1; i >= 0; i--) {
				stack.push(list.get(i));
			}
			return true;
		}
		
		return false;
	}
	

	/*
	 * flatten first
	private List<Integer> flattenedList;
	private int current, size;
    public NestedIterator(List<NestedInteger> nestedList) {
    	this.flattenedList = new LinkedList<>();
    	if (nestedList == null) {
    		return;
    	}
    	flatten(nestedList);
    	size = flattenedList.size();
    }

    @Override
    public Integer next() {
    	Integer result = null;
    	if (hasNext()) {
    		result = flattenedList.get(current++);
    	}
    	
		return result;
        
    }

    @Override
    public boolean hasNext() {
		return (current < size);
        
    }
    
    private void flatten(List<NestedInteger> nestedList) {
    	for (NestedInteger itr : nestedList) {
    		if (itr.isInteger()) {
    			this.flattenedList.add(itr.getInteger());
    		}
    		else {
    			flatten(itr.getList());
    		}
    	}
    }
    */
}

