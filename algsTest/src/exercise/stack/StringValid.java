package exercise.stack;

import java.util.Stack;
public class StringValid {
    public static boolean match(String s){
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            switch (c) {
                case ')':
                    if(!stack.isEmpty() && stack.pop() == '('){
                        break;
                    }else{
                        return false;
                    }
                case ']':
                    if(!stack.isEmpty() && stack.pop() == '['){
                        break;
                    }else{
                        return false;
                    }
                case '}':
                    if(!stack.isEmpty() && stack.pop() == '{'){
                        break;
                    }else{
                        return false;
                    }
                default:
                    stack.push(c);
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(match("{[()]()[{}]}"));
        System.out.println(match("{[()]}}"));
    }
}