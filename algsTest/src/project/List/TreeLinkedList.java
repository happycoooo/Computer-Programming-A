package project.List;

import project.ChessStep;
import project.MoveMethod;

import java.util.Stack;

public class TreeLinkedList {

    protected Node root = null;
    protected Node current = null;
    protected Node end = null;
    protected int count;

    //每个结点记录当下棋盘的状态：棋子步骤、父节点、下一个结点指向
    protected  static class Node {
        public ChessStep chessStep;
        public Node parentNode;
        public Node link;
    }

    //清空结点
    public void ClearAll(){
        root = null;
        current = null;
        end = null;
        count = 0;
    }

    //插入结点
    public void insertNode(ChessStep newitem){
        //产生一个新节点
        Node newNode = new Node();
        //赋予新节点的棋子移动
        newNode.chessStep = newitem;
        newNode.link = null;
        newNode.parentNode = null;

        //如果该结点为第一个节点
        if(root == null){
            root = newNode;
            current = newNode;
            end = current;
        }else{//不是第一个节点
            newNode.parentNode = current;
            end.link = newNode;
            end = newNode;
        }
        count++;
    }

    public void MoveCurrentToNext(){
        if(current != null && current.link != null ){
            current = current.link;
        }
    }

    //获取棋子步法的列表
    /*public ChessStep[] TraceResult(ChessStep chessStep){
        //利用栈记录棋子移动的步骤
        Stack stack = new Stack();
        Node nodeTracer = current;
        MoveMethod m = chessStep.moveMethod;




    }*/




}
