package assignment3;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
public class Danganronpa{
    private int k;
    private static ArrayList<String> stm = new ArrayList<String>();
    private static ArrayList<String> tb = new ArrayList<String>();
    private static boolean fail = false; //indicate if gauge I < 0 in type2 event
    private static int matchNum = 0;
    private static int I;
    private static int score = 0;
    private static MaxHeap heap1 = new MaxHeap();
    private static MinHeap heap2 = new MinHeap();
    private static HashMap<String, Integer> stmMap = new HashMap<>();

    public static void main(String[] args) {
        int L;
        QReader input = new QReader();
        QWriter out = new QWriter();

        int n = input.nextInt(); I = input.nextInt();
        int[] m = new int[n];
        int sum_a = 0, sum_b = 0, sum_m = 0;
        for(int i = 0 ;i<n;i++){
            m[i] = input.nextInt();
            sum_m += m[i];

        }

        for(int i = 0 ; i <= n-1; i++){
            String line = input.nextLine();
            String arr[] = line.split(" ");

            if(Integer.valueOf(arr[0]) == 1) {
                stm.add(arr[1]);
                int k = arr[1].length();
                addToHeap(k); // modify minHeap and maxHeap when new statement is added in type1 event
                if (stmMap.containsKey(arr[1])) { // update hashmap to record # of each statement when type 1 event occurs
                    int curr = stmMap.get(arr[1]);
                    stmMap.replace(arr[1], curr+1);
                } else {
                    stmMap.put(arr[1], 1);
                }

            }
            else if (Integer.valueOf(arr[0]) == 2) {
                tb.add(arr[1]);
                alterInfluenceGauge(1);
                L = counterStatements(); // return the median length of statements
                out.println(L);
                if (I < L) alterInfluenceGauge(-stm.size());
                if (stmMap.containsKey(arr[1])) {  // update matchNum when type 2 event occurs
                    matchNum += stmMap.get(arr[1]);
                }
                if (I < 0) fail = true;
            } else if (Integer.valueOf(arr[0]) == 3) {
                out.println(pairMatch());
            }

            sum_a += counterStatements();
            sum_b += pairMatch();
        }
        score = sum_a * sum_b * sum_m; // the final score equals to the product of sum of a, b and m

        if (fail || score < 0) {
            out.print("Fail");
        } else {
            out.print("Qi Fei");
        }
        out.close() ;
    }

    public static int counterStatements() {//return the median length of statements
        if (stm.size() == 0) {
            return 0;
        }
        int median = heap1.getMax();
        return median;
    }

    public static void alterInfluenceGauge(int k){//Alter the value of influence gauge
        I += k;
    }

    public static int pairMatch(){//return the number of pair matched
        return matchNum;
    }

    public static void addToHeap(int k) {
        if (heap1.size() == 0 || heap1.getMax() >= k) {
            heap1.push(k);
            if (heap1.size() > heap2.size()+ 1) {
                heap2.push(heap1.getMax());
                heap1.pop();
            }
        }
        else {
            heap2.push(k);
            if (heap1.size() < heap2.size()) {
                heap1.push(heap2.getMin());
                heap2.pop();
            }
        }
    }


    static class QReader {
        private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");
        private String innerNextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String nextLine = innerNextLine();
                if (nextLine == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(nextLine);
            }
            return true;
        }

        public String nextLine() {
            tokenizer = new StringTokenizer("");
            return innerNextLine();
        }

        public String next() {
            hasNext();
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class QWriter implements Closeable {
        private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        public void print(Object object) {
            try {
                writer.write(object.toString());
            } catch (IOException e) {
                return;
            }
        }

        public void println(Object object) {
            try {
                writer.write(object.toString());
                writer.write("\n");
            } catch (IOException e) {
                return;
            }
        }
        @Override
        public void close() {
            try {
                writer.close();
            } catch (IOException e) {
                return;
            }
        }
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

