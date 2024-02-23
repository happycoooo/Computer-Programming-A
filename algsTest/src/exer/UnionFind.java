package exer;

public class UnionFind {
    private int [] id;
    public UnionFind( int N ) {
        id = new int[N];
        //将数组信息导入
        for(int i = 0 ; i < N; i++) { id[i] = i; }
    }
    public int find( int p ) {
        return (id[p] == p) ? p:(id[p] = find(id[p]));
    }
    public boolean isConnected( int p, int q ) {
        // Write code here!
        return find(p) == find(q);
    }

    public void union( int p, int q ) {
        id[find(p)] = find(q);
    }
}