package QuickSort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    public static void sort(int[] a){
        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }

    //递归函数
    private static void sort(int[] a, int lo , int hi){
        if(hi <= lo ) return;
        int j = partition(a,lo,hi); //find a pivot
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }

    private static int partition(int[] a, int lo, int hi){
        int i = lo;
        int j = hi + 1 ; //前置自减返回操作后的值
        int v = a[lo];
        while(true){
            //满足条件不断推进直到整个数组被遍历结束
            while( a[++i] < v )  if(i == hi) break;
            while( a[--j] > v )  if(j == lo) break;
            if(i >= j) break;  //i与j相遇时退出循环
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }

    private static void exch(int[] a, int i , int j){
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
