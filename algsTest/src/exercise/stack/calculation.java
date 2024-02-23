package exercise.stack;
import java.util.EmptyStackException;
import java.util.Stack;

public class calculation {
        public static void main(String[] args) {
            System.out.println(new calculation().evaluate("4+(6-10+2*2.4)*2"));
        }

        /**
         * 表达式求值
         *
         * @param expression
         *            表达式
         * @return 返回double型的计算结果
         */

        public double evaluate(String expression) {
            Stack<Double> OPND_stack = new Stack<>();// 操作数栈
            Stack<Character> OPTR_stack = new Stack<>(); // 操作符栈
            // 遍历这个表达式
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);
                // 如果当前字符是数字，也就是操作数
                if (Character.isDigit(c) || c == '.') {
                    StringBuilder stringBulider = new StringBuilder();
                    // 操作数的拼接，包括小数点
                    while (i < expression.length() && (Character.isDigit(c = expression.charAt(i)) || c == '.')) {
                        stringBulider.append(c);
                        i++;
                    }
                    // 操作数入栈
                    OPND_stack.push(Double.valueOf(stringBulider.toString()));
                    // 跳过本次循环，i的值已经增加过，所以要减去
                    i--;
                    continue;
                } else {
                    // 当前的字符是操作符
                    outer: while (!OPTR_stack.isEmpty()) {
                        switch (precede(OPTR_stack.peek(), c)) {
                            case '<':
                                // 栈顶运算符小于该运算符，该运算符直接入栈
                                OPTR_stack.push(c);
                                break outer;
                            case '=':
                                // 栈顶运算符等于该运算符，只有一种情况，左右括号匹配，弹出左括号
                                OPTR_stack.pop();
                                break outer;
                            case '>':
                                // 栈顶运算符大小该运算符
                                char optr = OPTR_stack.pop();
                                // 如果有多余的操作符却没有操作数可以计算了，那么说明表达式错误
                                try {
                                    double opnd2 = OPND_stack.pop();
                                    double opnd1 = OPND_stack.pop();
                                    OPND_stack.push(operate(opnd1, optr, opnd2));
                                } catch (EmptyStackException e) {
                                    System.err.println("表达式有误0！");
                                    System.exit(0);
                                }
                                break;
                        }
                    }
                    // 第一次栈为空的情况，直接入栈。还有退栈直至栈为空的情况，当前操作符也需要入栈
                    if (OPTR_stack.isEmpty()) {
                        OPTR_stack.push(c);
                    }
                }
            }
            while (!OPTR_stack.isEmpty()) {
                char optr = OPTR_stack.pop();
                // 如果有多余的操作符却没有操作数可以计算了，那么说明表达式错误
                try {
                    double opnd2 = OPND_stack.pop();
                    double opnd1 = OPND_stack.pop();
                    OPND_stack.push(operate(opnd1, optr, opnd2));
                } catch (EmptyStackException e) {
                    System.err.println("表达式有误！");
                    System.exit(0);
                }
            }
            if (OPND_stack.size() == 1) {
                return OPND_stack.pop();
            } else {
                System.err.println("表达式有误！");
                System.exit(0);
            }
            return 0;
        }

        /**
         * 运算
         *
         * @param opnd1
         *            操作数1
         * @param optr
         *            操作符
         * @param opnd2
         *            操作符2
         * @return 运算结果
         */
        private double operate(double opnd1, char optr, double opnd2) {
            switch (optr) {
                case '+':
                    return opnd1 + opnd2;
                case '-':
                    return opnd1 - opnd2;
                case '*':
                    return opnd1 * opnd2;
                case '/':
                    return opnd1 / opnd2;
            }
            return 0;
        }

        /**
         * 比较两个运算符的优先权大小
         *
         * @param θ1
         * @param θ2
         * @return
         */
        public char precede(char θ1, char θ2) {
            if (θ1 == '+' || θ1 == '-') {
                if (θ2 == '+' || θ2 == '-' || θ2 == ')') {
                    return '>';
                } else {
                    return '<';
                }
            } else if (θ1 == '*' || θ1 == '/') {
                if (θ2 == '(') {
                    return '<';
                } else {
                    return '>';
                }
            } else if (θ1 == '(') {
                if (θ2 == ')') {
                    return '=';
                } else {
                    return '<';
                }
            }else if(θ1 == ')'){
                return '>';
            }
            return '>';
        }
}
