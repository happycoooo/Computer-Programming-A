package assignment2;
import java.util.Scanner;
public class homework2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        input.nextLine();
        String[] a = new String[T];
        String[] b = new String[8];
        String[] c = new String[8];
        int[] bb = new int[8];
        int[] cc = new int[8];
        boolean flag=true;
        String[] arr;
        for (int i = 0; i < T; i++) {
            a[i] = input.nextLine();//数组a用于记录整行的字符串
        }
        for (int j = 0; j < T; j++)
        {
            arr = a[j].split(" ");
            for (int k = 0; k < 8; k++) {
                b[k] = arr[k].substring(0, 1);
                bb[k]=b[k].charAt(0)-96;//chatAt从
                c[k] = arr[k].substring(1, 2);
                cc[k]=Integer.parseInt(c[k]);
            }
            loop:
            for (int k=0;k<7;k++) {
                for (int h = k+1; h < 8; h++) {
                    double m = Math.abs(cc[h] - cc[k]);
                    double z = Math.abs(bb[h] - bb[k]);
                    if (z==0|m==0) {
                        flag=false;
                        break loop;
                    } else if ( m == z ) {
                        flag=false;
                        break loop;
                    }
                    else{
                        flag=true;
                    }

                }
            }
            if(flag){
                System.out.println("Yes");
            }
            else{
                System.out.println("No");
            }
        }
    }
}


