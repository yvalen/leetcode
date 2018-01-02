package leetcode.list;

import java.util.Stack;

/*
 * LEETCODE 445
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The most significant digit comes first and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Follow up: what if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * Example:
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 * 
 * Company: Microsoft, Bloomberg
 * Difficulty: medium
 * Similar Questions: 2(AddTwoNumbers)
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers_long(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        Stack<Integer> s1 = new Stack<>();
        ListNode c1 = l1;
        while (c1 != null) {
            s1.push(c1.val);
            c1 = c1.next;
        }

        Stack<Integer> s2 = new Stack<>();
        ListNode c2 = l2;
        while (c2 != null) {
            s2.push(c2.val);
            c2 = c2.next;
        }

        boolean addOne = false;
        ListNode next = null;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            int val = s1.pop() + s2.pop() + (addOne ? 1 : 0);
            if (val >= 10) {
                addOne = true;
                val %= 10;
            } else {
                addOne = false;
            }
            ListNode node = new ListNode(val);
            node.next = next;
            next = node;
        }

        Stack<Integer> s = s1.isEmpty() ? s2 : s1;
        while (!s.isEmpty()) {
            int val = s.pop() + (addOne ? 1 : 0);
            if (val >= 10) {
                val %= 10;
                addOne = true;
            } else {
                addOne = false;
            }
            ListNode node = new ListNode(val);
            node.next = next;
            next = node;
        }

        if (addOne) {
            ListNode node = new ListNode(1);
            node.next = next;
            next = node;
        }

        return next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        Stack<Integer> s1 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }

        Stack<Integer> s2 = new Stack<>();
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty())
                sum += s1.pop();
            if (!s2.isEmpty())
                sum += s2.pop();
            list.val = sum % 10;
            ListNode node = new ListNode(sum / 10);
            node.next = list;
            list = node;
            sum /= 10; // update sum
        }

        return (list.val == 0) ? list.next : list;
    }
    
    public ListNode addTwoNumbers_withReverse(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        // reverse input list first
        l1 = reverse(l1);
        l2 = reverse(l2);
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int sum = 0;
        while (l1 != null || l2 != null) {
            sum = sum / 10;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            current.next = new ListNode(sum % 10);
            current = current.next;  // advance current pointer
        }
        if (sum >= 10) current.next = new ListNode(1); // need to check for equals to 10
        
        return reverse(dummy.next);
    }
    
    private ListNode reverse(ListNode head) {
        ListNode current = head, prev = null, next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        AddTwoNumbersII a = new AddTwoNumbersII();
        //int[] ary1 = { 7, 2, 4, 3 };
        //int[] ary2 = { 5, 6, 4 };
        int[] ary1 = {5};
        int[] ary2 = {5};
        ListNode l1 = ListUtil.createList(ary1);
        ListNode l2 = ListUtil.createList(ary2);
        ListNode l = a.addTwoNumbers_withReverse(l1, l2);
        ListUtil.printList(l);
    }
}
