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
    }

    public TrieNode checkPrefix(String text) {
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

    public void searchWord(String text) {
        TrieNode aux = checkPrefix(text);

        if(aux == null){
            System.out.println("A palavra pesquisada não existe.");
        }
        else if(aux.isWord()) {
            System.out.println("A palavra pesquisada existe.");
        }

    }

    public void searchForPrefix(String text) {
        ArrayList<String> words = new ArrayList<>();
        TrieNode aux = checkPrefix(text);

        if(aux != null) {
            words = getSugestions(words, aux, text);
            if(words.isEmpty()) {
                System.out.println("\nNenhuma sugestão encontrada.");
            }else {
                System.out.println("\nAutocomplete para '"+text+"':");
                for(String word : words) {
                        System.out.println(word);
                }
            }
        }
    }

    public void searchForPrefix(String text, int limitPrefix) {
        ArrayList<String> words = new ArrayList<>();
        TrieNode aux = checkPrefix(text);

        if(aux != null) {
            words = getSugestions(words, aux, text);
            if(words.isEmpty()) {
                System.out.println("\nNenhuma sugestão encontrada.");
            }else {
                if(limitPrefix != -1) {
                    System.out.println("\nAutocomplete para '"+text+"' com limite de "+limitPrefix+" sugestões:");
                    for(String word : words) {
                        if(limitPrefix > 0) {
                            System.out.println(word);
                            limitPrefix--;
                        }
                    }
                }
            }
        }
    }

    public ArrayList<String> getSugestions(ArrayList<String> words, TrieNode aux, String text) {
        if(aux.isWord()) {
            words.add(text);
        }

        for(char prefix : aux.getChildren().keySet()) {
            words = getSugestions(words, aux.getChild(prefix), text.concat(String.valueOf(prefix)));
        }
        return words;
    }

}
