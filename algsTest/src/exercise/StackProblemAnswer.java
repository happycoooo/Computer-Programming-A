package exercise;
import java.util.Scanner;
import java.util.Stack;
public class StackProblemAnswer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string = sc.next();
//        System.out.println("Q1" + Arrays.toString(StoneWeight(new int[]{5, 3, 2, 4})));
        System.out.println("Q2 is " + score(string));
    }

    public static int score(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(0);
                //如果是左括号，压0入栈
            else {
                //如果是右括号，有两种可能性：
                int val = stack.pop();
                if (val == 0) {
                    //val==0代表上一个入栈的是左括号
                    //那么，这次的右括号和刚刚弹出栈的0(也就是左括号)共同组成了一对小括号()
                    //它的值为1，因为这是一个完整的括号，所以满足题干中的：S1S2=S1+S2
                    //将这个值加到上一个算出来的值中(将上一个值取出来，加上，再放回去)
                    stack.push(stack.pop() + 1);
                } else {
                    //val不等于0代表前面有完整的匹配括号对，如：() ; ((())())
                    //这个匹配括号对的值就是val
                    //此时，满足题目中的：(S1)=S1*2，val需要乘2
                    //将这个值加到上一个算出来的值中(将上一个值取出来，加上，再放回去)
                    stack.push(stack.pop() + val * 2);
                }
            }
        }
        return stack.pop();
    }
}
