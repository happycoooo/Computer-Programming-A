package MergeSort;

import java.util.Scanner;
import java.lang.String;

public class Merge {
    private static Comparable[] aux;

    public static void insertion(int[] array ,int left, int right){
        for(int i = left ; i<=right ;i++){

        }
    }

    //sort [left,right] in array
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;

    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        /*if(hi > lo){
            System.arraycopy(a,lo, aux, lo, hi-lo+1);
        }*/
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }
    /*public static void merge1(int[] a, int left, int mid, int right) {
        int[] aux = new int[a.length];
        for(int i = left ; i <= right ;i++){
            aux[i] = a[i];
        }
        int i = left;
        int i1 = left;
        int i2 = mid + 1;
        while( i1 <= mid && i2 <= right){
            if(aux[i2] < aux[i1] ){
                a[i] = aux[i2++];
                i++;
            }
            else if (i1>mid)
                a[i]
        }
    }*/

    //定义用于递归的sort
    private static void sort(Comparable[] a, int lo, int hi) {
        //终止条件,防止StackOverFlowError
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);

    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] str = s.split(" ");
        sort(str);
        assert isSorted(str);
        show(str);
    }
}
