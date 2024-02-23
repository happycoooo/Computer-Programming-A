package assignment2;
import java.util.Scanner;
public class homework5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long r = input.nextLong();
        long number = 0;
        double z;
        int i = 1;
        if (r == 0) {
            System.out.println(1);
        } else {
            while (i <= r - 1) {
                z = Math.sqrt(r * r - (long) i * i);
                if (z == (long) z) {
                    number = number + 1;
                }
                    i++;
                }
                System.out.println(number * 4 + 4);
            }
        }
    }

