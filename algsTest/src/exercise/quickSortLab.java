package exercise;

public class quickSortLab {
    public static int[] quickSort(int[] array){
        sort(array,0,array.length-1);
        return array;
    }
    private static void sort(int[] a, int lo , int hi){
        if(hi <= lo ) return;
        int j =partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }
    private static int partition(int[] a, int lo, int hi){
        int i = lo;
        int j = hi + 1 ;
        int v = a[lo];
        while(true){
            while( a[++i] < v )  if(i == hi) break;
            while( v < a[--j] )  if(j == lo) break;
            if(i >= j) break;
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
