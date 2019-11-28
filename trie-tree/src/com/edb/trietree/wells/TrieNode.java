package com.edb.trietree.wells;

import java.util.HashMap;

public class TrieNode {

    char letter;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    String text;
    boolean isWord;

    public TrieNode() { }

    public TrieNode(char letter) {
        this.letter = letter;
    }
}
