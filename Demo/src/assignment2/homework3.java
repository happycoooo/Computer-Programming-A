package assignment2;
import java.util.Scanner;
import java.util.Arrays;
public class homework3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        input.nextLine();//换行读入输入的数
        String str1 = input.nextLine();
        String str2 = input.nextLine();
        String[] homeCoordinates = str1.split(" ");
        String[] seatCoordinates = str2.split(" ");
        int[] a = new int[N];
        int[] b = new int[N];
        int[] c = new int[N];
        int max=0;
        for (int i = 0; i < N; i++) {
            a[i] = Integer.valueOf(homeCoordinates[i]);
            b[i] = Integer.valueOf(seatCoordinates[i]);
        }
        Arrays.sort(a);//对数组a进行排序
        Arrays.sort(b);
        for(int j = 0; j<N;j++){//遍历排序后的数组a
            c[j]= Math.abs(a[j]-b[j]);
            if(c[j]>max){
                max=c[j];
            }
        }
        System.out.println(max);
    }

}
