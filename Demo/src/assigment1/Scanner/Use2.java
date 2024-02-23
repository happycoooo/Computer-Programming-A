package assigment1.Scanner;//单行输入多个参数
import java.util.Arrays;
import java.util.Scanner;
public class Use2 {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("输入：");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();  // 读取一行
        System.out.println("输出：");
        System.out.println(str);
        String[] strIn = str.trim().split(" ");  // 以空格分割
       // System.out.println(Arrays.toString(strIn));//包括这行的话输出会多一行带框的
    }
}

