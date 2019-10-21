package br.com.edb.huffmanCoding;

public class Node {

    private String letter;
    private int frequency;
    private Node left = null;
    private Node right = null;

    public Node(String letter, int frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }
    public Node(int frequency) {
        this.frequency = frequency;
    }

    public Node() {}

    public String getLetter() { return letter; }
    public void setLetter(String letter) { this.letter = letter; }
    public int getFrequency() { return frequency; }
    public void setFrequency(int frequency) { this.frequency = frequency; }

    public Node getLeft() { return left; }
    public void setLeft(Node left) { this.left = left; }
    public void setRight(Node right) { this.right = right; }
    public Node getRight() { return right; }

    public boolean isLeaf(){
        if(left == null && right == null){
            return true;
        }
        return false;
    }
}
