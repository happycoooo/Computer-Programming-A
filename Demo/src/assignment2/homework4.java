package assignment2;
import java.util.Scanner;
public class homework4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long k = input.nextLong();
        long n;
        n = (k - 1) * 2 + k - 1 + 2;
        System.out.println(n + 4);
        for (long i = -1; i <= k; i++) {
            if (i == -1) {
                System.out.println(i + " " + i);
                System.out.println(i + " " + (i + 1));
            } else if (i < k) {
                for (long m = -1; m <= 1; m++) {
                    if (i==0&&(m + i) == 0) {
                    }
                    else{
                        System.out.println(i + " " + (m + i));
                    }
                }
            } else {//i=k
                System.out.println(i + " " + (k - 1));
                System.out.println(i + " " + k);
            }
        }

    }
}

