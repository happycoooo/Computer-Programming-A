package exercise;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class exercise1 {
    public static String[] adoptOrder(String[] names, String[] type, String[] adoptType) {
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < adoptType.length; i++) {
            queue.add(adoptType[i]);
        }
        String[] result = new String[adoptType.length];
        int num = 0;
        while (!queue.isEmpty()) {
            for (int j = 0; j < type.length; j++) {
                if (j == type.length-1) { //No animal meet demand
                    for (int i = 0; i < type.length; i++) {
                        if (type[i] != "null") {
                            result[num] = names[i];
                            type[i] = "null";
                            num++;
                            queue.poll();
                            break;
                        }
                    }
                }
                if (type[j].equals(queue.peek())) {
                    queue.poll();
                    result[num] = names[j];
                    type[j] = "null";
                    num++;
                    break;
                }
            }
        }
        return result;
    }
    public static void main(String[] args){
        String[] names = new String[]{"qwe","rty","uio","asd","fghj","kl","zxcvb"};
        String[] type = new String[]{"Cat","Dog","Dog","Dog","Cat","Cat","Cat"};
        String[] adoptType = new String[]{"Cat","Dog","Cat","Dog"};
            System.out.println(Arrays.toString(adoptOrder(names,type,adoptType)));
    }
    }