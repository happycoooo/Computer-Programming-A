package assigment1.Scanner;//不确定行数，采取设置结束标志后循环结束的方式。
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//后面一直有输入的时候，没有指明 n，获得所有数据都在的一个列表
//注意： 在IDEA测试的时候以-1结束，上机考试时候不需要-1结束
public class Use3{
        public static ArrayList<Integer> inputStringToIntArrayListWithoutN()
        {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while(in.hasNext()){
        int a = in.nextInt();
        if(a == -1)
        break;
        else
        list.add(a);
        }
        return list;
        }
}