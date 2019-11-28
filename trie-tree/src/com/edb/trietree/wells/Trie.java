package com.edb.trietree.wells;

import java.util.ArrayList;
import java.util.HashMap;

public class Trie {

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public TrieNode getRoot() {
        return this.root;
    }

    public void insert(String text) {
        TrieNode aux = getRoot();
        char[] letters = text.toCharArray();
        int count = 0;

        for (char letter : letters) {
            count++;
            if(aux.getChildren().containsKey(letter)) {
                aux = aux.getChild(letter);
            }else {
                aux = aux.setChild(letter);
                if(count == letters.length) {
                    aux.setWord(true);
                }
            }
        }
        System.out.println(aux.isWord());
    }

    public void searchWord(String text) {
        TrieNode aux = searchForPrefix(text);
        if(aux == null){
            System.out.println("A palavra pesquisada não existe.");
        }
        else if(aux.isWord()) {
            System.out.println("A palavra pesquisada existe.");
        }

    }

    public TrieNode searchForPrefix(String text) {
        TrieNode aux = getRoot();
        char[] letters = text.toCharArray();
        int count = 0;

        for(char letter : letters) {
            if(aux.getChildren().containsKey(letter)) {
                aux = aux.getChild(letter);
            }else aux = null;
        }
        return aux;
    }

    public TrieNode searchForPrefix(String text, int limitPrefix) {
        ArrayList<String> autocomplet = new ArrayList<>();
        TrieNode aux = searchForPrefix(text);
    }

}
