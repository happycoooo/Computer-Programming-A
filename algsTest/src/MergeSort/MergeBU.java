package MergeSort;

public class MergeBU {

    public static void merge(int[] integers,int min,int mid,int max) {
        int i=min;
        int j=mid+1;
        int[] aux = new int[integers.length];
        for (int k = 0; k < integers.length; k++) {
            aux[k]=integers[k];
        }
        for(int k=min;k<=max;k++){
            if (i>mid) {
                integers[k]=aux[j++];
            }else if (j>max) {
                integers[k]=aux[i++];
            }else if (aux[i]<aux[j]) {
                integers[k]=aux[i++];
            }else {
                integers[k]=aux[j++];
            }
        }
    }
    /**
     * 自底向上归并排序
     * 外层for循环控制子序列长度
     * 内层for循环控制子序列合并
     * 归并排序算法复杂度O(N²)
     * @param integers
     * @return
     */
    public static int[] sort(int[] integers) {
        for (int i = 1; i < integers.length; i=i+i) {
            for(int j=0;j<integers.length-i;j=j+2*i){
                merge(integers, j, j+i-1, Math.min(j+2*i-1, integers.length-1));
            }
        }
        return integers;
    }
    public static void main(String[] args) {
        int[] integers=sort(new int[]{67 ,88 ,23 ,5 ,77});
        for (int i : integers) {
            System.out.println(i);
        }
    }
}