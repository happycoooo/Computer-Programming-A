import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;

public class Main {

    public static boolean match(char c1, char c2) {
        return c1 == '(' && c2 == ')' || c1 == '[' && c2 == ']' || c1 == '{' && c2 == '}';
    }

    public static boolean isValid1(String sequence){
        Stack<Character> s1 = new Stack<>();

        for(char current : sequence.toCharArray()){
            switch(current){
                case'(':
                case'{':
                case'[':
                    s1.push(current);
                    break;
                case'}':
                case')':
                case']':
                    if(s1.isEmpty() || !match(s1.pop(),current))
                        return false;
                    break;
            }
        }
        return s1.isEmpty();
    }

    public static boolean isValid2(String sequence) {
        Stack<Character> s1 = new Stack<>();
        for (char s:sequence.toCharArray()) {
            if (s == '(' || s == '{' || s == '[') {
                s1.push(s);
            } else if (s == ')' || s == '}' || s == ']') {
                if ( s1.isEmpty()|| !match(s, s1.pop()))
                    return false;
                break;
            }
        }
        return s1.isEmpty();
    }

    public static void main(String[] args) {
        for (int data = 1; data < 100; data++) {
            String sequence = new In("data/" + data + ".in").readAll();
            int answer = new In("data/" + data + ".out").readInt();
            if ((isValid1(sequence) ? 1 : 0) != answer)
                StdOut.println("error");

            if ((isValid2(sequence) ? 1 : 0) != answer)
                StdOut.println("error");
        }
    }
}
