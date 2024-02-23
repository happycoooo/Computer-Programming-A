package MergeSort;
import java.util.Arrays;
import java.util.Random;

public class NaturalSort {
    //find the right end of increasing interval given the left position
    private static int findInterval(int[] array,int lo) {
        int size = 1;
        for(int i = lo; i < array.length -1 ; i++){
            if(array[i] <= array[i+1])
                size++;
            else
                break;
        }
      return size;
    }

    private static void sort(int[] array) {
        int[] aux = new int[array.length];
        while(true){
            //找到第一个有序块
            int start=0;
            int point2 = 0;
            int point1 = findInterval(array, start) -1;
            if(point1 == array.length -1)
                break;
            while( point1 < array.length -1){
                point2 = findInterval(array, point1 + 1) + point1;
                Merge(array, start, point1, point2,aux);
                start = point2 + 1;
                point1 = findInterval(array,start) + start -1;
            }
        }




    }
    public static void Merge( int[] a,int lo, int mid, int hi, int[] aux)
    {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (aux[j] < aux[i])
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    private static boolean test( int[] array ) {
        int[] array2 = new int[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        Arrays.sort(array);
        sort(array2);
        return Arrays.equals(array, array2);
    }

    public static void main(String[] args) {
        int[] array = new int[20000];
        Random rand1 = new Random();
        for(int j = 0 ; j < 100; j++){
            for(int i = 0; i < 20000; i++){
                array[i] = rand1.nextInt();
            }
            System.out.println(test(array));
        }
        }

}
