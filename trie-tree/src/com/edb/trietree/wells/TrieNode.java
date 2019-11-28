package com.edb.trietree.wells;

import java.util.HashMap;

public class TrieNode {

    private HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    private String text;
    private boolean isWord;

    public TrieNode() {
        //constructor
    }

    TrieNode(char text) {
        isWord = false;
        children = new HashMap<>();
    }

        //GETTERS E SETTERS
    public HashMap<Character, TrieNode> getChildren() { return children; }
    public String getText() { return text; }
    public boolean isWord() { return isWord; }
    public void setChildren(HashMap<Character, TrieNode> children) { this.children = children; }
    public void setText(String text) { this.text = text; }
    public void setWord(boolean word) { isWord = word; }

    public TrieNode getChild(char letter){
        return children.get(letter);
    }
    public TrieNode setChild(char letter) {
        children.put(letter, new TrieNode(letter));
        return children.get(letter);
    }

}
