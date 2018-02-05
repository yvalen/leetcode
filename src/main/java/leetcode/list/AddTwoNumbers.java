package leetcode.list;

/*
 * LEETCODE 2
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * 
 * Company: Microsoft, Bloomberg, Amazon, Airbnb, Adobe
 * Difficulty: medium
 * Similar Questions: 67(AddBinary), 43(MultiplyString), 371(SumOfTwoIntegers), 415(AddStrings)
 * 445(AddTwoNumbersII)
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers_andnullcheck(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = val / 10;
            current.next = new ListNode(val%10);
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            current = current.next;
        }
        // need to check for carry in the end
        if (carry > 0) current.next = new ListNode(carry);

        /*
        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + carry;
            carry = val >= 10 ? 1 : 0;
            current.next = new ListNode(val % 10);
            current = current.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode l = l1 == null ? l2 : l1;
        while (l != null) {
            int val = l.val + carry;
            carry = val >= 10 ? 1 : 0;
            current.next = new ListNode(val % 10);
            current = current.next;
            l = l.next;
        }

        if (carry == 1)
            current.next = new ListNode(1);
         */

        return dummy.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int sum = 0;
        while (l1 != null || l2 != null) {
            // previous sum, divided by 10 to get the number to add to the new
            // sum
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
            current = current.next;
        }

        // take care the overflow of the last digit
        if (sum / 10 > 0) {
            current.next = new ListNode(1);
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        AddTwoNumbers a = new AddTwoNumbers();

        int[] nums1 = { 1, 8 };
        int[] nums2 = { 0 };
        ListNode l1 = ListUtil.createList(nums1);
        ListNode l2 = ListUtil.createList(nums2);
        ListNode l = a.addTwoNumbers(l1, l2);
        ListUtil.printList(l);
    }
}
