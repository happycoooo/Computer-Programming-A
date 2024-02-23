//package lab14;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.PriorityQueue;
//
//public class MinimalTree {
//    public static List<Edge> kruskal(WeightedGraph graph){
//        PriorityQueue<Edge> pq = new PriorityQueue<>();
//        for(int i = 0 ; i < graph.size(); i++){
//            pq.addAll(graph.adjacent(i));
//        }
//        LinkedList<Edge> result = new LinkedList<>();
//        while(result.size() < graph.size()-1 && !pq.isEmpth()){
//            Edge edge = pq.poll();//返回优先级队列中权重最小的边
////            if(!edge.from and edge.to is connected){
////                connect edge.from and edge.to
////            }
//
//        }
//    }
//}
