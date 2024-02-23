package lab8;

import edu.princeton.cs.algs4.ST;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class MaxPQ {

    ST<String,Integer> st = new ST<String, Integer>();

    private int[] pq;
    private int count;

    public MaxPQ( int capacity ) {
        pq = new int[capacity + 1];//开头0的位置多占一个位
        count = 0;
    }

    private void resize( int newsize ) {
        int[] tmp = new int[newsize];
        System.arraycopy(pq, 1, tmp, 1, count);
        pq = tmp;
    }

    //不断与其父节点交换，直到满足所有的父节点大于子节点为止
    private void swim( int idx ) {
        int value = pq[idx];
        while( idx > 1 && pq[idx/2] < value ) {
            pq[idx] = pq[idx/2];
            idx /= 2;
        }
        pq[idx] = value;
    }

    //最后一个位置放入，对当前位置进行上浮操作
    public void insert( int value ) {
        if( count+1 >= pq.length )
            resize( pq.length*2 );
        pq[++count] = value;
        swim( count );
    }

    //下沉替换的是子节点中更大的那一个
    private void sink( int idx ) {
        int value = pq[idx];
        while( idx*2 <= count ) {
            int id2 = (idx*2+1 <= count && pq[idx*2]<pq[idx*2+1]) ? (idx*2+1) : (idx*2);
            if( pq[id2] > value ) {
                pq[idx] = pq[id2];
                idx = id2;
            } else
                break;
        }
        pq[idx] = value;
    }

    //将最大元素删除
    public int delMax() {
        if( count <= 0 )
            throw new NoSuchElementException("MaxPQ.delMax called when count="+count);
        int result = pq[1];
        pq[1] = pq[count--];//将最后一个数放到根节点上
        sink( 1 );
        return result;//返回最大值
    }

    private static void sink( int[] array, int count, int idx ) {
        int value = array[idx-1];
        while( idx*2 <= count ) {//while i has a left child
            int id2 = (idx*2+1 <= count && array[idx*2-1]<array[idx*2]) ? (idx*2+1) : (idx*2);
            if( array[id2-1] > value ) {
                array[idx-1] = array[id2-1];
                idx = id2;
            } else
                break;
        }
        array[idx-1] = value;
    }
    public static void heapSort( int[] array ) {
        if( array.length <= 1 )
            return;
        int count = array.length;
        // build a heap:
        for( int k = count/2; k >= 1; k -- )
            sink( array, count, k);
        // do sorting:
        while( count > 1 ) {
            int tmp = array[count-1];
            array[count-1] = array[0];
            array[0] = tmp;
            count --;
            sink( array, count, 1 );
        }
    }

    private static boolean test( int[] array ) {
        int[] array2 = new int[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        Arrays.sort(array);
        heapSort(array2);
        return Arrays.equals(array, array2);
    }
    public static void main( String[] args ) {
        Random rand = new Random();
        boolean result = test(new int[]{1, 2, 3, 4, 5});
        result = result && test(new int[100000]);
        result = result && test(new int[0]);
        int[] array = new int[1000];
        for( int ite = 0; ite < 1000; ++ ite ) {
            for( int i = 0; i < array.length; ++ i )
                array[i] = rand.nextInt();
            result = result && test(array);
        }
        System.out.println(result);
    }
}
