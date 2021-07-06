import java.util.Stack;

/**
 * @Author prgers
 * @Date 2021/7/3 11:14 下午
 * https://leetcode-cn.com/problems/score-of-parentheses/
 */
public class _856_括号的分数 {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            }else {
                int x = stack.pop();
                int y = stack.pop();
                stack.push(y + Math.max(2 * x, 1));
            }
        }
        return stack.pop();
    }
}
