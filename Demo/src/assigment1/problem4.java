package assigment1;
import java.util.Scanner;
public class problem4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] arr = str.split(" ");
        int l = 0;//用于条件判断
        int counter = 0;//违规次数计数器
        int w = 0;
        char RightString = 'Y';
        String result;
        for ( int i = 0; i < arr.length;  i++)  {
            if  ( arr[i].length() == 0 ) {//有多余空格
                System.out.println( "Not valid" );
                return;
            }
            w = w + 1;
        }
        if (w == arr.length) {//没有多余空格
            if (arr.length == 4) {//输入是四周的数据，继续
                for (int j = 0; j < 4; j++) {//遍历，判断每周数据是否为7个
                    if (arr[j].length() == 7) {//输入的字符串长度都为7，继续
                        for (int g = 0; g < 7; g++) {//遍历，判断是否存在除了Y和N以外的字符
                            if (arr[j].charAt(g) == 'Y' | arr[j].charAt(g) == 'N') {//不存在Y和N以外的字符
                                l = 1;//表示以上条件满足
                            } else {//存在Y和N以外的字符，退出循环
                                System.out.println("Not valid");
                                return;
                            }
                        }
                    } else {//输入字符串长度不为7，退出循环
                        System.out.println("Not valid");
                        return;
                    }
                }
            } else {//没有输入四周的数据
                System.out.println("Not valid");
            }
        }
        if (l == 1) {
            for (int k = 0; k < 4; k++) {
                result = arr[k];
                if (result.contains("NNN")) {
                    counter = counter + 1;
                } else {
                    int number = 0;
                    int y = 0;
                    while (y <= 6) {
                        char ch = result.charAt(y);
                        if (RightString == ch) {
                            number = number + 1;
                        }
                        y = y + 1;
                    }
                    if (number  <= 3) {
                        counter = counter + 1;
                    }
                }
            }
            if (counter== 0) {
                System.out.println("100");
            }
            if (counter == 1) {
                System.out.println("90");
            }
            if (counter == 2) {
                System.out.println("75");
            }
            if (counter == 3) {
                System.out.println("55");
            }
            if (counter == 4) {
                System.out.println("30");
            }
        }
    }
}

