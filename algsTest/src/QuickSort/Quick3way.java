package QuickSort;

public class Quick3way {
    private static void sort(int[] a, int lo, int hi){
        if(hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        int v = a[lo];
        while(i <= gt){
            if (a[i] < v) exch(a,lt++,i++);
            else if(a[i] > v) exch(a,i,gt--);
            //a[i] == v
            else i++;
        }
    }

    private static void exch(int[] a, int i , int j){
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
