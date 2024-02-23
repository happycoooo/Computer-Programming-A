package examples;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;
import java.util.Stack;

public class Evaluate<T> {
    class Node<T>{
        T data;
        Node<T> next;
        Node(T d){
            data = d;
            next = null;
        }
    }

    private Node<T> first = null;

    public void push(T element){
        Node<T> node  = new Node<>(element);
        node.next = first;
        first = node;
    }

    public T pop(){
        if(first == null){
            throw new NoSuchElementException("Stack is empty");
        }
        T result = first.data;
        first = first.next;
        return result;
    }
    public T peek(){
        if(first == null){
            throw new NoSuchElementException("Stack is empty");
        }
        return first.data;

    }
}
