package com.edb.trietree.wells;

import java.util.ArrayList;

public class Trie {

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public TrieNode getRoot() {
        return this.root;
    }

    public void insert(String word) {
        char letters[] = word.toCharArray();
        int count = 0;

        for (char letter : letters) {
            TrieNode trieAux;

            if((getRoot().children).containsKey(letter)) {
                trieAux = (getRoot().children).get(letter);
            }else {
                trieAux = new TrieNode(letter);
                (getRoot().children).put(letter, trieAux);
            }
            getRoot().children = trieAux.children;
            count++;

            if(count == word.length()-1) {
                trieAux.isWord = true;
            }
        }



    }


}
