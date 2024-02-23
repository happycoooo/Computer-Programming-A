package lab10;

public class BST<Key extends Comparable<Key>, Value> {

    private class Node{
        Key key;
        Value val;

        Node left = null;
        Node right = null;
        Node parent = null;

        Node(Key k, Value v){
            key = k;
            val = v;
        }
    }
    private Node root = null;
    private int size = 0;

    // return null if not found,非递归实现
    public Value get(Key key){
        if (key == null){
            throw new IllegalArgumentException("key == null in get");
        }
        Node node = root;
        while(node != null){
            int compare = key.compareTo(node.key);
            if(compare < 0){
                node = node.left;
            }else if(compare > 0){
                node = node.right;
            }else{
                return node.val;
            }
        }
        return null;
    }

    //return old value, or null if not found
    public Value put(Key key, Value val){
        if (key == null || val == null){  //这里不允许val为空
            throw new IllegalArgumentException("key == null || val == null in put");
        }
        if(root == null){
            root = new Node(key, val);
            size = 1;
            return null;
        }
        Node node = root;
        while(node != null){
            int compare = key.compareTo(node.key);
            if(compare < 0){
                if(node.left == null){
                    node.left = new Node(key, val);
                    node.left.parent = node;
                    size++;
                    return null;
                }else
                    node = node.left;
            }else if (compare > 0){
                if(node.right == null){
                    node.right = new Node(key, val);
                    node.right.parent = node;
                    size++;
                    return null;
                }else
                    node = node.right;
            }else{
                Value ret = node.val;
                node.val = val;
                return ret;
            }
        }
        return null;
    }

    private void replaceNodeWithChild(Node node, Node child){
        if( node == root ){
            root = child;
        }else if(node == node.parent.left)
            node.parent.left  = child;
        else
            node.parent.right = child;
        if(child != null){
            child.parent = node.parent;
        }
        size--;
    }

    private Node min(Node node){
        while(node.left != null)
            node = node.left;
        return node;
    }

    private Node delMin(Node node){
        node = min(node);
        replaceNodeWithChild(node, node.right);
        return node;
    }

    public Value delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key == null in delete");
        }

        Node node = root;
        while (node != null) {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                node = node.left;
            } else if (compare > 0) {
                node = node.right;
            } else { // delete node from tree
                if (node.left == null) {
                    replaceNodeWithChild(node, node.right);
                }else if(node.right == null){
                    replaceNodeWithChild(node, node.left);
            }

        }
    }
return null;
    }

    public static void main (String[]args){
        BST<Integer, Double> bst = new BST<>();
        System.out.println(bst.put(1, 2.0));
        System.out.println(bst.put(2, 3.0));
        System.out.println(bst.put(1, 4.0));

        System.out.println(bst.get(1));
        System.out.println(bst.get(2));
        System.out.println(bst.get(3));
    }







    }
