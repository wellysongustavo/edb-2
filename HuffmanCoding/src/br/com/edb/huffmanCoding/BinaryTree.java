package br.com.edb.huffmanCoding;

import javax.swing.*;
import java.util.Arrays;

public class BinaryTree {
    private Node[] nodes; //manda nodes
    private int size;//quantos elementos tem
    private int capacity;//quantos elementos cabem

    public BinaryTree() {
        this(10);
    }

    public BinaryTree(int capacity) {
        nodes = new Node[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public void insert(char letter, int frequency) {
        insert(new Node(String.valueOf(letter), frequency));
    }

    public void insert(Node node) {
        ensureCapacity();
        nodes[getSize()] = node;
        heapifyUp(getSize());
        size++;
    }

    private void heapifyUp(int index) {
        int parentIndex = getParentIndex(index);

        if (parentIndex < 0) {
            return;
        }

        Node pai    = nodes[parentIndex];
        Node node = nodes[index];

        if (node.getFrequency() < pai.getFrequency()) {
            nodes[index]   = pai;
            nodes[parentIndex] = node;
            heapifyUp(parentIndex);
        }
    }

    public int getParentIndex(int index) {
        return (int) Math.floor((index - 1) / 2);
    }

    private void ensureCapacity() {
        if (size == capacity) {
            nodes = Arrays.copyOf(nodes, capacity * 2);
            capacity = capacity * 2;
        }
    }

    public int getSize() {
        return size;
    }

    public Node peek() {
        if (getSize() == 0) {
            return null;
        }
        return nodes[0];
    }

    public void remove() {
        nodes[0] = nodes[getSize() - 1];
        nodes[getSize() - 1] = null;
        size--;
        heapifyDown(0);
    }

    private void heapifyDown(int index) {
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;

        int childIndex = -1;
        if (leftChild < getSize()) {
            childIndex = leftChild;
        }

        if (childIndex == -1) { // se é folha
            return;
        }

        if (rightChild < getSize()) {
            if (nodes[rightChild].getFrequency() < nodes[leftChild].getFrequency()) {
                childIndex = rightChild;
            }
        }

        if (nodes[index].getFrequency() > nodes[childIndex].getFrequency()) {
            Node tmp          = nodes[index];
            nodes[index]      = nodes[childIndex];
            nodes[childIndex] = tmp;
            heapifyDown(childIndex);
        }
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public Node getRaiz() {
        return null;
    }

    public void printTree(){

        JFrame window = new JFrame(); //é uma estrutura organizada em árvore
        window.setSize(400,300);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //qnd fechar janela mata o processo

        View view = new View(this);
        window.add(view);
        window.setVisible(true);
    }
}



