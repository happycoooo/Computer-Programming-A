package lab15;

public class CharSort {

    public static void sort(Character[] array){
        int[] count = new int[65536+1];

        for(int i = 0 ; i < array.length; i++){
            if(array[i] == null){
                count[0]++;
            }else{
                count[array[i]+1]++;
//                char c = array[i];
//                int id = (int)c+1;
//                count[id]++;

            }
        }

        for(int i = 0, j = 0 ; i < count.length; i++){
            while(count[i]-- > 0 ){
                if(i == 0){
                    array[j++] = null;
                }else{
                    array[j++] = (char)(i-1);
                }
            }
        }
    }

    public static void main(String[] args){
        Character[] array = {'h','e','l','l','o','w','o','r','l','d',null};
        sort(array);
        for(Character c : array){
            System.out.println(c);
        }
    }
}
