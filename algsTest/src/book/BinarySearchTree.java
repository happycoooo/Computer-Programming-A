package book;
import book.SymbolTable;
import edu.princeton.cs.algs4.Queue;
import java.util.NoSuchElementException;

public abstract class BinarySearchTree< Key extends Comparable<Key>, Value> implements SymbolTable<Key,Value>{

    protected class Node{
        protected Key key;
        protected Value value;
        protected Node left;
        protected Node right;
        protected int size;

        public Node(Key key, Value value, int size){
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    protected Node root;  //二叉查找树的根节点

    public int size(){
        return size(root);
    }

    //size():返回以当前结点为根结点的子树的结点数
    protected int size(Node node){
        if (node == null){
            return 0;
        }
        return node.size;
    }

    public boolean isEmpty(){
        return size(root) == 0;
    }

    public Value get(Key key){
        return get(root, key);
    }

    //在以x为根结点的子树中查找并返回key所对应的值，未找到则返回null
    public Value get(Node x, Key key){
        if(x == null){
            return null;
        }
        int compare = key.compareTo(x.key);
        if(compare < 0){
            return get(x.left, key);
        }else if(compare > 0){
            return get(x.right,key);
        }else{
            return x.value;
        }
    }

    public boolean contains(Key key){
        if(key == null){
            throw new IllegalArgumentException("Argument to contains() cannot be null");
        }
        return get(key) != null;
    }

    //查找key，找到则更新他的值，否则为它创建一个新的结点
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value val){
        if(x == null ){ //树未形成，该结点为第一个结点
            return new Node(key, val,1);
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0 ){
            x.left = put(x.left, key, val);
        }else if(cmp > 0 ){
            x.right = put(x.right, key, val);
        }else{
            x.value = val; //找到key,更新值
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if(x.left == null) return x;
        return min(x.left);
    }

    public Key max(){
        return max(root).key;
    }

    private Node max(Node x){
        if(x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key){
        Node x = floor(root, key);
        if(x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);

        if(cmp == 0 ) return x;   //case1:[k equals the key in the node]
        //case2:[k is less than the key in the node]
        //The floor of k is in the left subtree.
        if(cmp < 0 ) return floor(x.left, key);

        //case3:[k is greater than the key in the node]
        //The floor of k is in the right subtree(if exists), otherwise it is the key in the node
        Node t = floor(x.right, key);
        if(t != null) return t;
        else return x;
    }

    public Key select(int k){ //返回排名为k的键
        return select(root, k).key;
    }

    //返回排名为k的结点 ???
    private Node select(Node x, int k){
        if(x == null) return null;
        int t = size(x.left);
        if(t > k ){
            return select(x.left, k);
        }else if(t < k){
            return select(x.right, k-t-1);
        }else{
            return x;
        }
    }

    // 返回key的排名  ???
    public int rank(Key key){
        return rank(key, root);
    }

    private int rank(Key key, Node x){
        if(key == null){
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0 ){
            return rank(key, x.left);
        }else if (cmp > 0 ){
            return rank(key, x.right) + size(x.left) + 1;
        }else{
            return  size(x.left);
        }
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if(x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
}
