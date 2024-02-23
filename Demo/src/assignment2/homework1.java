package assignment2;
import java.util.Scanner;
public class homework1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();//换行读入输入的数
        String str = input.nextLine();
        String[] arr = str.split(" ");
        double l = input.nextDouble();
        double r = input.nextDouble();
        double sum=0;
        double[] coefficient =new double [n];
        int i = 0;
        while(i<arr.length) {
            coefficient[i] = Double.parseDouble(arr[i]);
            i += 1;
        }
            for (int j = 0; j <arr.length; j++) {
                double a=Math.pow(r, j+1);
                double b=Math.pow(l, j+1);
                sum = sum+ (coefficient[j] * (1.0 / (j+1) )*(a-b));
            }
        System.out.println(sum);
    }
}