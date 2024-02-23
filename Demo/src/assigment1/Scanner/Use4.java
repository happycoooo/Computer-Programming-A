package assigment1.Scanner;//确定行数，不确定每行具体变量数，但知道每行最多有几个变量
import java.util.ArrayList;//第一行输入m和n m代表行数，n代表每行最多有n个
import java.util.Arrays;
import java.util.Scanner;
public class Use4 {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("输入：");
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        sc.nextLine();  // 很重要，跳到第二行
        // 若直接确定行数，注释掉上面两行，加入下面一行
        // int m = 3;
        String[] strArr = new String[m];
        // 从第二行开始读取
        for(int i = 0; i < m; i++) {
            strArr[i] = sc.nextLine();
        }
        System.out.println("输出：");
        System.out.println(Arrays.toString(strArr));
        ArrayList<String[]> strToOne = new ArrayList<String[]>();
        for(int i = 0; i < m; i ++) {
            String[] tmp = strArr[i].trim().split(" ");
            strToOne.add(tmp);
        }
        System.out.println(strToOne);
        // 形象点显示
        System.out.print("[");
        for(int i = 0; i < strToOne.size(); i++) {
            System.out.print(Arrays.toString(strToOne.get(i)));
            if(i != strToOne.size()-1)
                System.out.print(", ");
        }
        System.out.print("]");
    }
}

