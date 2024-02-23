package exercise;
import edu.princeton.cs.algs4.Stack;
public class exercise3 {
    public static int score(String s) {
        Stack<Integer> val = new Stack<>();
        Stack<Character> ops = new Stack<>();
        Stack<Character> str = new Stack<>();
        for( char current : s.toCharArray() ) {
            if(current == '('){
                if(str.isEmpty()){
                    str.push('(');
                }else{
                    if( str.peek() == current) { //存在叠加括号中
                        val.push(2);
                        ops.push('*');
                        str.push('(');
                    }else{
                        if(!ops.isEmpty() && ops.peek()!='*'){
                            char op = ops.pop();
                            int v = val.pop();
                            if(op == '+') {
                                v = val.pop() + v;
                            } else {
                                v = val.pop() + v;
                            }
                            val.push(v);
                        }else{
                            ops.push('+');
                        }
                    }
                }
            } else{ //current == ')'
                if(str.peek() == '('){
                    val.push(1);
                    str.push(')');
                }else{
                    if(!val.isEmpty() && !ops.isEmpty()){
                        char op = ops.pop();
                        int v = val.pop();
                        if(op == '+') v = val.pop() + v;
                        else v = val.pop() + v;
                        val.push(v);
                    }
                }
            }
        }
        while(!ops.isEmpty()){
            char op = ops.pop();
            int v = val.pop();
            if(op == '+') {
                v = val.pop() + v;
            } else {
                v = val.pop() + v;
            }
            val.push(v);
        }
        return val.pop();
    }
    public static void main(String[] args){
        String s  = "((()))()()";
        System.out.print(score(s));
    }
}
