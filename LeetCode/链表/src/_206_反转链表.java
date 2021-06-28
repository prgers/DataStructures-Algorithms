public class _206_反转链表 {

    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
