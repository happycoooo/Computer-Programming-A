package lab2;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;
import java.nio.channels.IllegalChannelGroupException;

public class ThreeSumProblem {
    public static void main(String[] args){
        /*Stopwatch stopwatch = new Stopwatch();
        int[] array = new int[] {30, -40, -20, -10, 40, 0, 10, 5 };
        int count = ThreeSum.count(array);
        double elapsedTime = stopwatch.elapsedTime();
        StdOut.printf("Result:"+count+"Time:"+elapsedTime);*/
        for( int i = 1; i<=8 ; ++i) {
            try {
                In fin = new In("./resources/data/"+ i + "Kints.txt");
                int[] arr = fin.readAllInts();
                fin.close();

                StdOut.printf("Calculating sums in %dKints.txt", i);
                Stopwatch timer = new Stopwatch();
                int count = ThreeSum.count(arr);

                StdOut.printf("size of data: %d, result: %d sums, time spent: %f seconds.\n",
                        arr.length, count, timer.elapsedTime());
            }catch(IllegalArgumentException e){
                e.printStackTrace();
            }
        }
    }
}
