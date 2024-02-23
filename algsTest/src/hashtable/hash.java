package hashtable;

import java.util.Iterator;
import java.util.LinkedList;

import static java.util.Objects.hash;

public class hash<Key, Value> {
    private static class Node<Key, Value>{
        Key key;
        Value val;
        Node(Key k, Value v){
            key = k;
            val = v;
        }
    }

    private LinkedList<Node<Key, Value>>[] data;//存储结点的链表，可针对相同的哈希值存储多个元素

    private hash(int capacity){//初始化
        data = new LinkedList[capacity];
        for(int i = 0; i< data.length; i++){
            data[i] = new LinkedList<>();
        }
    }

    private int hash(Key key){
        int h = key.hashCode();
        return (h & 0x7fffffff) % data.length;
    }

    public Value put (Key key, Value value){
        int hash = hash(key);
        for(Node<Key, Value> node: data[hash]){
            if(node.key.equals(key)){  //旧的值存在
                Value old = node.val;
                node.key =  key;
                node.val = value;
                return old;
            }
        }
        data[hash].add(new Node<>(key,value));
        return null;
    }

    public Value get(Key key){
        if(key == null){
            throw new IllegalArgumentException("key is null in get.");
        }
        int hash = hash(key);
        for(Node<Key, Value> node : data[hash]){
            if(node.key.equals(key)){
                return node.val;
            }
        }
        return null;
    }

    public Value delete(Key key){
        if(key == null){
            throw new IllegalArgumentException("Key is null in get");
        }
        int hash = hash(key);
        for(Iterator<Node<Key, Value>> ite = data[hash].iterator(); ite.hasNext();){
            Node<Key, Value> node = ite.next();
            if( node.key.equals(key)){
                ite.remove();
                return node.val;
            }
        }
        return null;
    }

    public static void main(String[] args){
        hash<Point, Integer> table = new hash(5);
        System.out.println(table.put(new Point(-1,-1),111));
        System.out.println(table.put(new Point(-2,-1),111));
        System.out.println(table.put(new Point(-3,-1),111));
        System.out.println(table.put(new Point(-4,-1),111));
        System.out.println(table.put(new Point(-5,-1),111));
    }
}
