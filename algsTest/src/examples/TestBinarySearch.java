package examples;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdArrayIO;

public class TestBinarySearch {
    public static void main( String[] args ) {

        StdOut.println("Test BinarySearch:");

        int[] arr = new int[] { -2,-1,-1,-1,0 };
        int target = -1;
        StdOut.println("Array is:");
        StdArrayIO.print(arr);
        StdOut.printf("Index of %d is %d.\n", target, BinarySearch.indexOf(arr, target));
    }
}
