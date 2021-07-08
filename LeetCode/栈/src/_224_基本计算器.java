import java.util.Stack;

/**
 * @Author prgers
 * @Date 2021/7/6 4:41 下午
 * https://leetcode-cn.com/problems/basic-calculator/
 */
public class _224_基本计算器 {

    public int calculate(String s) {
        int ans=0;
        char[] str=s.toCharArray();
        int len=str.length;
        Stack<Integer> st_num=new Stack<>();
        Stack<Integer> st_signs=new Stack<>();
        int sign=1;//正负号,运算符号
        for(int i=0;i<len;i++){
            if(str[i]==' ') continue;
            if(str[i]=='+'||str[i]=='-') sign=str[i]=='+'? 1:-1;
            else if(str[i]>='0'&&str[i]<='9'){//数字
                int num=str[i]-'0';
                while(i<len-1&&str[i+1]>='0'&&str[i+1]<='9'){//将这个数字找完
                    num=num*10+(str[++i]-'0');
                }
                ans+=sign*num;
            }else if(str[i]=='('){//左括号，暂存结果
                st_num.push(ans);
                st_signs.push(sign);
                ans=0;
                sign=1;
            }
            else ans=st_num.pop()+ans*st_signs.pop();//右括号更新结果
        }
        return ans;
    }
}
