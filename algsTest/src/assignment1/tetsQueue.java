package assignment1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static jdk.nashorn.internal.objects.Global.print;

public class tetsQueue {
    public static void main (String[] args){
        Queue<Integer> queue = new LinkedList<>();
        int[] InitCards = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        ArrayList<Integer> destroyOrder = new ArrayList<>();
        for(int i = 0 ; i < InitCards.length; i++ ){
            queue.add(InitCards[i]);
        }
        int counter = 0;
        while(queue.size()>=1){
            if(queue.size() == 1){
                destroyOrder.add(queue.peek());
                queue.poll();
            }
            else if(counter % 2  == 0){
                destroyOrder.add(queue.peek());
                queue.poll();
                counter += 1;
            }else{
                queue.add(queue.peek());
                queue.poll();
                counter += 1;
            }
        }

        for(int i = 0 ; i < destroyOrder.size(); i++){
            System.out.println(destroyOrder.get(i));
        }
    }

}
