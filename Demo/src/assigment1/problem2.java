package assigment1;
import java.util.Scanner;
public class problem2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long s = sc.nextLong();
        int x = 0;
        int q = 1;
        int o = 1;
        while (s != 0) {
            s = s / 10;
            x = x + 1;
        }
        if (x != 8) {
            System.out.println("Not valid");
            q = 0;
        }
        if (q == 1) {
            String m = sc.next();
            int i = 0;
            int a = 0;
            int b = 0;
            int p;
            char str1 = 'Y';
            char str2 = 'N';
            p = m.length();
            if (p != 7) {
                System.out.println("Not valid");
                o = 0;
            }
            if (o == 1) {
                if (m.contains("NNN")) {
                    System.out.println("Has not participated in Nucleic Acid PCR tests as required!");
                }
                else{
                while (i <= 6) {
                    char ch = m.charAt(i);
                    if (str1 == ch) {
                        a = a + 1;
                    }
                    i = i + 1;
                }
                    if (a <= 3) {
                        System.out.println("Has not participated in Nucleic Acid PCR tests as required!");
                    }
                    else {
                        System.out.println("Good, keep it up!");
                    }
                }
            }
        }
    }
}