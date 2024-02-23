package project.BFS;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class GraphTraversal {
    ListGraph graph;
    boolean[] visited;

    public GraphTraversal(ListGraph listGraph){
        this.graph = listGraph;
        visited = new boolean[listGraph.graphs.size()];
    }

    public void BFSTraversal(int v){
        Deque<Integer> queue = new ArrayDeque<>();
        visited[v] = true;
        queue.offerFirst(v);
        while(queue.size()!=0){
            Integer cur = queue.pollFirst();
            System.out.print(cur + "->");
            Iterator<Integer>neighbors = graph.graphs.get(v).listIterator();
            while(neighbors.hasNext()){
                int nextNode = neighbors.next();
                if(!visited[nextNode]){
                    queue.offerLast(nextNode);
                    visited[nextNode] = true;
                }
            }
        }
    }

    public void BFS(){
        for(int i = 0; i < graph.graphs.size(); i++){
            if(!visited[i]){
                BFSTraversal(i);
            }
        }
    }



}
