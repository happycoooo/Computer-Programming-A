package assignment3;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
class Main {
    private int k;
    static ArrayList<String> stm = new ArrayList<String>();
    static ArrayList<String> tb = new ArrayList<String>();
    public static void handleOne(String s){stm.add(s);}//Type1处理
    public static void handleTwo(String s){tb.add(s);}//Type2处理
    static boolean flag = false;
    static boolean hasNegative = false;
    public static int matchNum = 0;
    public static int I;
    public static int score = 0;
    public static MaxHeap heap1 = new MaxHeap();
    public static MinHeap heap2 = new MinHeap();
    public static HashMap<String,Integer> stmMap = new HashMap<>();


    public static  int counterStatements() {
        int l;
        if (stm.size() == 0) {
            return 0;
        } else {
            for (int i = 0; i < stm.size(); i++) {
                int k = stm.get(i).length();
                if (heap1.size() == 0 | heap1.getMax() > k) {
                    heap1.push(k);
                }
                if (heap1.size() > heap2.size() + 1) {
                    heap2.push(heap1.getMax());
                    heap1.pop();
                } else {
                    heap2.push(k);
                }
                if (heap1.size() < heap2.size()) {
                    heap1.push(heap2.getMin());
                    heap2.pop();
                }
            }
            flag = true;
            return heap1.getMax();//return the median length of statements
        }
    }
    public static void alterInfluenceGauge(int k){//Alter the value of influence gauge
        if(matchNum!=0){
            k++;
        }
        if(flag && counterStatements()>k){
            k = k - stm.size();
        }
    }
    public static int pairMatch(){  //return the number of pair matched
        for( int i = 0; i < stm.size() ; i++ ){
            if(stmMap.containsKey(stm.get(i))){
                stmMap.put(stm.get(i),stmMap.get(stm.get(i))+1);
                matchNum += stmMap.get(stm.get(i));
            }
            else{
                stmMap.put(stm.get(i),1);
            }
        }
        return matchNum;
    }

    public static void main(String[] args) {
        Main medianFinder =  new Main();
        medianFinder.counterStatements();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); int I = input.nextInt();
        input.nextLine();
        int[] m = new int[n];
        for(int i = 0 ;i<n;i++){
            m[i] = input.nextInt();
        }
        input.nextLine();
        for(int i = 0 ; i < n-1; i++){
            String line = input.nextLine();
            String arr[] = line.split(" ");
            if(Integer.valueOf(arr[0]) ==1){
                stm.add(arr[1]);
            }
            else{
                tb.add(arr[1]);
            }
        }
        input.nextLine();
        int x = input.nextInt();
        input.close() ;
        long score;
        System.out.println(x);




            /*if ( a.alterInfluenceGauge()<0)
            {
                System.out.println("Fail");
            }else{
                if (score >= 0) {
                    System.out.println("Qi Fei");
                } else {
                    System.out.println("Fail");
                }
            }
            }*/
}
static class MinHeap{
    private static final int MAXSIZE = 10010;
    private int[] a = new int[MAXSIZE];
    private void swap(int x,int y){
        a[x] = a[x]^a[y];
        a[y] = a[y]^a[x];
        a[x] = a[x]^a[y];
    }
    private void up(){
        int p = a[0];
        while(p > 1){
            if (a[p] < a[p/2]){
                swap(p,p/2);
                p = p / 2;
            }
            else
                break;
        }
    }

    public void push(int k){
        a[++a[0]] = k;
        up();
    }
    public void pop(){
        int s = 2, t = 1;
        a[1] = a[a[0]--];
        while (s <= a[0]){
            if (s <= a[0] - 1 && a[s+1] < a[s])
                ++s;
            if (a[s] < a[t]){
                swap(s, t);
                t = s;
                s*=2;
            }
            else
                break;
        }
    }
    public int getMin(){
        return a[1];
    }
    public int size(){
        return a[0];
    }
}
static class MaxHeap{
    private static final int MAXSIZE = 10010;
    private int[] a = new int[MAXSIZE];
    private void swap(int x,int y){
        a[x] = a[x]^a[y];
        a[y] = a[y]^a[x];
        a[x] = a[x]^a[y];
    }
    private void up(){
        int p = a[0];
        while(p > 1){
            if (a[p] > a[p/2]){
                swap(p,p/2);
                p = p / 2;
            }
            else
                break;
        }
    }
    public void push(int k){
        a[++a[0]] = k;
        up();
    }
    public void pop(){
        int s = 2, t = 1;
        a[1] = a[a[0]--];
        while (s <= a[0]){
            if (s <= a[0] - 1 && a[s+1] > a[s])
                ++s;
            if (a[s] > a[t]){
                swap(s,t);
                t = s;
                s*=2;
            }
            else
                break;
        }
    }
    public int getMax(){
        return a[1];
    }
    public int size(){
        return a[0];
    }
}
}

