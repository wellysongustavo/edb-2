package br.com.edb.huffmanCoding;

public class Node {

    private char letter;
    private int frequency;
    private Node left = null;
    private Node right = null;

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Node(char letter, int frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }
    public Node(int frequency) {
        this.frequency = frequency;
    }

    public Node() {

    }

    public char getLetter() { return letter; }
    public int getFrequency() { return frequency; }
    public void setFrequency(int frequency) { this.frequency = frequency; }

    public Node getLeft() { return left; }
    public void setLeft(Node left) { this.left = left; }
    public void setRight(Node right) { this.right = right; }
    public Node getRight() { return right; }

}
