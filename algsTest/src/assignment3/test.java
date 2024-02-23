package assignment3;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class test {

    public static class TreeNode {
        public int data;
        public TreeNode parent;
    }

    public static TreeNode getNode(ArrayList<TreeNode> nodeList, int data){
        for(int i = 0 ; i < nodeList.size(); i++){
            if(nodeList.get(i).data == data){
                return nodeList.get(i);
            }
        }
        return null;
    }

    public static void addEdge(ArrayList<TreeNode> nodeList, int v, int w) {
        int min, max;
        if (v < w) {
            min = v;
            max = w;
        } else {
            min = w;
            max = v;
        }
        boolean hasPNode = false;
        boolean hasCNode = false;
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).data == min) { //父节点已存入
                TreeNode node2 = new TreeNode();
                node2.data = max;
                node2.parent = nodeList.get(i);
                nodeList.add(i+1, node2);
                hasPNode = true;
                break;
            }
            if (nodeList.get(i).data == max) { //子节点已存入
                hasCNode = true;
            }
        }
        if (nodeList.size() == 0 ) { //没有任何结点存入
            TreeNode node1 = new TreeNode();
            TreeNode node2 = new TreeNode();
            node1.data = min;
            node2.data = max;
            node2.parent = node1;
            nodeList.add(node1);
            nodeList.add(node2);
        } else if (!hasPNode) { //父节点未存入
            if(hasCNode){//子节点已存入
                for (int i = 0; i < nodeList.size(); i++) {
                    if (nodeList.get(i).data == max) {
                        TreeNode node1 = new TreeNode();
                        node1.data = min;
                        nodeList.get(i).parent = node1;
                        nodeList.add(i-1,node1);
                    }
                }
            }else{
                TreeNode node1 = new TreeNode();
                TreeNode node2 = new TreeNode();
                node1.data = min;
                node2.data = max;
                node2.parent = node1;
                nodeList.add(node1);
                nodeList.add(node2);
            }
        }
    }

    public static int[] findParents ( int n, ArrayList<TreeNode> nodeList){
        int[] parents = new int[n];
        for (int i = 0; i < nodeList.size(); i++) {
            if(getNode(nodeList,i+1).parent == null){
                parents[i] = -1;
            }else{
                parents[i] = getNode(nodeList,i+1).parent.data;
            }
        }
        return parents;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int n = 6;
        ArrayList<TreeNode> nodeList = new ArrayList<>();
        addEdge(nodeList,1,2);
        addEdge(nodeList,1,3);
        addEdge(nodeList,3,4);
        addEdge(nodeList,3,5);
        addEdge(nodeList,2,6);
        int[] parents = findParents(n, nodeList);
        for (int parent : parents) {
            System.out.println(parent);
        }
    }
}
