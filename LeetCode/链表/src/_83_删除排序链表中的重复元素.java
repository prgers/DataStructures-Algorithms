/**
 * @Author prgers
 * @Date 2021/6/30 12:31 下午
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class _83_删除排序链表中的重复元素 {

    /**
     * 因为是升序,所以相同的数字肯定是连续的
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode node = head;
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            }else {
                node = node.next;
            }
        }
        return head;
    }
}
