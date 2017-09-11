package leetcode.stack;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {
	private Integer integer;
	private List<NestedInteger> list;
	
	/**
	 * Constructor initializes an empty nested list.
	 */
	public NestedInteger() {}

	/**
	 * Constructor initializes a single integer.
	 * 
	 * @param value
	 */
	public NestedInteger(int value) {
		this.integer = value;
	}
	
	/**
	 * Set this NestedInteger to hold a single integer.
	 * 
	 * @param value
	 */
	public void setInteger(int value) {
		this.integer = value;
	}
	
	/**
	 * Set this NestedInteger to hold a nested list and adds a nested integer to it.
	 * 
	 * @param ni
	 */
	public void add(NestedInteger ni) {
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(ni);
	}
	
	/** 
	 * @return true if this NestedInteger holds a single integer, rather than a nested list.
	 */
	public boolean isInteger() { return this.integer != null;}
	
	/**
	 * @return the single integer that this NestedInteger holds if it holds a single integer.
	 * Return null if this NestedInteger holds a nested list
	 */
	public Integer getInteger() {
		return this.integer;
	}
	
	/** 
	 * @return the nested list that this NestedInteger holds if it holds a nested list
	 * Return null if this NestedInteger holds a single integer
	 */
	public List<NestedInteger> getList() {
		return this.list;
	}

	@Override
	public String toString() {
		if (isInteger()) return this.integer.toString();
		else if (this.list != null) {
			StringBuilder sb = new StringBuilder("[");
			for (NestedInteger ni : this.list) {
				sb.append(ni.toString());
			}
			sb.append("]");
			return sb.toString();
		}
		return "";
	}
	
	
}
