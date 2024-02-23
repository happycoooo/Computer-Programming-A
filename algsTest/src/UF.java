import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import static sun.net.ftp.FtpReplyCode.find;

public class UF {
    private int[] id;
    private int count;

    public UF(int N){
        count = N;
        id = new int[N];
        //数组的初始化
        for(int i = 0 ; i < N ; i++ )
            id[i] = i;
    }

    public int count() { return count; }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int find(int p){
        return id[p];
    }

    public void union(int p, int q){
        int pID = find(p);
        int qID = find(q);
        if(pID == qID) return;        //若p和q已经在相同的分量之中则不需要采取任何行动
        for(int i = 0 ; i < id.length ; i++){
            if( id[i]==pID ) id[i] = qID;        //将所有和id[p]相等的元素的值均改为id[q]的值
        }
        count--;
    }



    public static void main(String[] args){
        int N = StdIn.readInt();  //读取触点数量
        UF uf = new UF(N);        //初始化N个分量
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(uf.connected(p,q)) continue;
            uf.union(p,q);
            StdOut.println(p+' '+q);
        }
        StdOut.println(uf.count() + "components");
    }
}
