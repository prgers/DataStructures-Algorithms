/**
 * @Author prgers
 * @Date 2021/6/29 9:53 下午
 */
public class _203_移除链表元素 {

    public ListNode removeElements(ListNode head, int val) {

        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) return null;

        ListNode prev = head;
        ListNode node = head.next;

        while (node != null) {
            if (node.val == val) {
                prev.next = node.next;
            }else {
                prev = node;
            }
            node = node.next;
        }
        return head;
    }

    //虚拟头节点
    public ListNode removeElements2(ListNode head, int val) {
        ListNode tempHead = new ListNode(0);
        tempHead.next = head;

        ListNode node = tempHead;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            }else {
                node = node.next;
            }
        }

        return tempHead.next;
    }



}
