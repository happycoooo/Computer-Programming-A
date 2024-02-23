package lab15;
public class StringSort {
    public static int charAt(String str, int position){
        if(str.length() <= position)
            return 0;
        else
            return str.charAt(position)-'a' +1;
    }
    public static int[] sort(String[] array, int position,int lo, int hi,String[] aux ){
        int[] count = new int[26+2];

        for(int i = 0 ; i < array.length; i++)
            count[charAt(array[i],position)+1]++;

        for(int i = 1 ; i < count.length; i++)
            count[i] += count[i-1];


        for(int i = 0; i < hi-lo+1; i++){
            aux[count[charAt(array[i+lo],position)]++] = array[i+lo];
        }

        for(int i = 0 ; i < hi-lo+1; i++){
            array[i+lo] = aux[i];
        }
        return count;

    }

    public static void msdSort(String[] array, int position, int lo, int hi, String[] aux){
        int[] count = sort(array, position, lo, hi, aux);
        for(int i = 0; i < count.length-1; i++){
            if(count[i]-count[i-1] > 1){
                msdSort(array,position+1, lo+count[i-1],lo+count[i]-1,aux);
            }
        }
    }

    public static void msdSort(String[] array){
        msdSort(array, 0, 0, array.length-1,new String[array.length]);
    }


    public static void main(String[] args){
        String[] array = { "hzllo", "hello", "world", "dsaa", "b", "helly", "worle", "dsaab", "daaab" };
        //sort(array,1);
    }
}
