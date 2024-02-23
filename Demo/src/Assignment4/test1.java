package Assignment4;
import java.util.Scanner;
public class test1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String number1 = input.next();
        int a = number1.length();
        int result = 0;
        if(number1.charAt(0)=='-') {
            for (int i = 1; i < a; i++) {
                char c = number1.charAt(i);
                double b = Math.pow(2, a - i - 1) * (c - '0');
                result += b;
            }
            System.out.println(result*(-1));
        }else{
            for (int i = 0; i < a; i++) {
                char c = number1.charAt(i);
                double b = Math.pow(2, a - i - 1) * (c - '0');
                result += b;
            }
            System.out.println(result);
        }
    }
}
