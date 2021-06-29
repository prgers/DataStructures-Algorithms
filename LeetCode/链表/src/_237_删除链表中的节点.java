/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class _237_删除链表中的节点 {

    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
    }
}
