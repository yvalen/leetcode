package leetcode.list;

public class OddEven {

    /**
     * Given a singly linked list, group all odd nodes together followed by the
     * even nodes. Please note here we are talking about the node number(INDEX)
     * and not the value in the nodes. You should try to do it in place. The
     * program should run in O(1) space complexity and O(nodes) time complexity.
     * Example: Given 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL. Note: The
     * relative order inside both the even and odd groups should remain as it
     * was in the input. The first node is considered odd, the second node even
     * and so on ...
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode head2 = head.next, odd = head, even = head.next;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = head2;

        return head;
    }

    /**
     * Given a Linked List of integers, write a function to modify the linked
     * list such that all odd numbers appear before all even numbers in the
     * modified linked list. Also, keep the order of even and odd numbers same.
     */
    public ListNode oddEvenListOddFirst(ListNode head) {
        ListNode lastOdd = null, firstEven = null, lastEven = null;
        for (ListNode current = head; current != null; current = current.next) {
            if (current.val % 2 == 0) {
                if (firstEven == null) {
                    firstEven = current;
                    lastEven = current;
                } else {
                    lastEven.next = current;
                    lastEven = current;
                }
            } else {
                if (lastOdd == null) {
                    head = current;
                } else {
                    lastOdd.next = current;
                }
                lastOdd = current;
            }
        }

        if (lastOdd != null && firstEven != null) {
            lastOdd.next = firstEven;
            lastEven.next = null;
        }

        return head;
    }

    public ListNode oddEvenListNaturalOrder(ListNode head) {
        ListNode head1 = null, tail1 = null, head2 = null, tail2 = null;
        for (ListNode current = head; current != null; current = current.next) {
            if (head1 == null) {
                head1 = current;
                tail1 = current;
                continue;
            }

            if (head1.val % 2 == current.val % 2) {
                tail1.next = current;
                tail1 = tail1.next;
            } else {
                if (head2 == null) {
                    head2 = current;
                    tail2 = current;
                } else {
                    tail2.next = current;
                    tail2 = tail2.next;
                }
            }
        }

        if (tail1 != null && head2 != null) {
            tail1.next = head2;
            tail2.next = null;
        }

        return head1;
    }

    public static void main(String[] args) {
        OddEven testObj = new OddEven();

        int[] ary = { 2, 1, 4, 3, 6, 5, 7, 8 };
        ListNode list = ListUtil.createList(ary);
        ListUtil.printList(list);

        ListNode oddEvenList = testObj.oddEvenList(list);
        ListUtil.printList(oddEvenList);

    }

}
