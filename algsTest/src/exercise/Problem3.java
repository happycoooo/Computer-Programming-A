package exercise;

import edu.princeton.cs.algs4.Stack;

public class Problem3 {
    public static int score(String s) {
        Stack<Integer> val = new edu.princeton.cs.algs4.Stack<>();
        Stack<Character> ops = new edu.princeton.cs.algs4.Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '(') {
                if (i >= 1 && s.charAt(i - 1) == ')') { // )(
                    if (s.charAt(i + 1) == '(') {
                        while (!ops.isEmpty()) {
                            char op = ops.pop();
                            int v = val.pop();
                            if (op == '+') {
                                v = val.pop() + v;
                            } else {
                                v = val.pop() * v;
                            }
                            val.push(v);
                        }
                    }
                    ops.push('+');
                }
            } else {  //current == ')'
                if (s.charAt(i - 1) == '(') { //()
                    val.push(1);
                } else {//   ))
                    while (!ops.isEmpty()) {
                        char op = ops.pop();
                        int v = val.pop();
                        if (op == '+') {
                            v = val.pop() + v;
                        } else {
                            v = val.pop() * v;
                        }
                        val.push(v);
                    }
                    val.push(2);
                    ops.push('*');
                }
            }
        }
        while (!ops.isEmpty()) {
            char op = ops.pop();
            int v = val.pop();
            if (op == '+') {
                v = val.pop() + v;
            } else {
                v = val.pop() * v;
            }
            val.push(v);
        }
        return val.pop();
    }
    public static void main(String[] args) {
        String s = "((()()))";
        System.out.println(score(s));
    }
}

