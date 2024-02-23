package assigment1.Scanner;//输入多行变量，每行变量个数由第一行确定(第一行几个数字就是几行，每个数字是相应行数里面的变量数)
import java.util.Arrays;
import java.util.Scanner;
    public class Use1 {
        public static void main(String[] args) {
            System.out.println("输入：");
            Scanner sc = new Scanner(System.in);
            int m =sc.nextInt();
            int n =sc.nextInt();
            String[] num1 = new String[m];//表示第一行输入的元素总个数，以空格隔开，每个数依次对应每行的元素个数
            String[] num2 = new String[n];
            // 换成其他数据类型也一样，其他数值类型就修改int跟nextInt就可以了，
            //String就把nextInt()换成next()
            for(int i = 0; i < m; i++) {
                num1[i] = sc.next();  // 一个一个读取
            }
            for(int i = 0; i < n; i ++) {
                num2[i] = sc.next();
            }
            System.out.println("输出：");
            System.out.println(Arrays.toString(num1));
            System.out.println(Arrays.toString(num2));
        }
    }
