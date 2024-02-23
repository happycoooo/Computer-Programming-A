package lab2.exercise1;

import java.util.Random;

public class Main {

    public static void moveSmall2Bottom(DeckQueue deckqueue){
        if (deckqueue.first() < deckqueue.second()){
            deckqueue.moveTop2Bottom();
        }else{
            deckqueue.exchangeTopTwo();
            deckqueue.moveTop2Bottom();
        }
    }

    public static void sort( DeckQueue deckqueue ) {
        int N = deckqueue.size();
        for(int i = 1 ; i <= N ; i++ ){
            for(int j = 0 ; j < N - i ;j++ ){
                moveSmall2Bottom(deckqueue);
            }if(i != N ){
                for(int j = 0 ; j < i ; j++ ){
                    deckqueue.moveTop2Bottom();
                }
            }
        }
    }

    public static void main( String[] args ) {
        Random rand = new Random();
        int[] array = new int[10000];
        for(int i = 0 ; i < array.length ; i++ ){
            array[i] = rand.nextInt();
        }
        DeckQueue dq = new DeckQueue(array);
        sort(dq);
        System.out.println(dq.isSorted());
    }
}