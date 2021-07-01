/**
 * @Author prgers
 * @Date 2021/6/30 12:40 下午
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class _876_链表的中间结点 {

    public ListNode middleNode(ListNode head) {

        ListNode node = head;
        int i = 0;
        while (node != null) {
            i ++;
            node = node.next;
        }

        int j = 0;
        node = head;
        while (j < (i >> 1)) {
            j ++;
            node = node.next;
        }
        return node;
    }
}
