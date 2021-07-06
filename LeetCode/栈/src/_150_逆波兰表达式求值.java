import java.util.Stack;

/**
 * @Author prgers
 * @Date 2021/7/6 3:28 下午
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class _150_逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int length = tokens.length;
        for (int i = 0; i < length; i++) {
            String s = tokens[i];
            if (isNumber(s)) {
                stack.push(Integer.parseInt(s));
            }else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                switch (s) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num2 - num1);
                        break;
                    case "*":
                        stack.push(num2 * num1);
                        break;
                    case "/":
                        stack.push(num2 / num1);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    public static void main(String[] args) {
        _150_逆波兰表达式求值 aa = new _150_逆波兰表达式求值();

        String[] aaa = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(aa.evalRPN(aaa));
    }
}
