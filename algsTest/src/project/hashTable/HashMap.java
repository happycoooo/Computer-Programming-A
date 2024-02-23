package project.hashTable;
import java.util.ArrayList;

public class HashMap {
    static class HashNode<String, Integer>{
        String key;
        Integer value;
        HashNode<String, Integer> next;
        public HashNode(String key, Integer value){
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<HashNode<String, Integer>>bucketArray;
    private int numBuckets;
    private int size;
    public HashMap(){
        bucketArray = new ArrayList<>();
        numBuckets = 10;
        size = 0;
        for(int i = 0; i < numBuckets; i++){
            bucketArray.add(null);
        }
    }

    private int getBucketIndex(String key){
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return index;
    }

    public void add(String key, Integer value){
        int bucketIndex = getBucketIndex(key);
        HashNode<String,Integer>head = bucketArray.get(bucketIndex);
        while(head != null){
            if(head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }
        head = bucketArray.get(bucketIndex);
        HashNode<String, Integer>newNode = new HashNode<String, Integer>(key, value);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);
        size++;
        if((1.0*size)/numBuckets >= 0.7){
            //generatebiggerArray();
        }
    }

}
