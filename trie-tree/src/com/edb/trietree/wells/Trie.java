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
        int count = 0;

        for (char letter : text.toCharArray()) {
            count++;
            if(aux.getChildren().containsKey(letter)) {
                aux = aux.getChild(letter);
            }else {
                aux = aux.setChild(letter);
                if(count == text.length()) {
                    aux.setWord(true);
                }
            }
        }
    }

    public TrieNode checkPrefix(String text) {
        TrieNode aux = getRoot();

        for(char letter : text.toCharArray()) {
            if(aux.getChildren().containsKey(letter)){
                aux = aux.getChild(letter);
            }else {
                return null;
            }
        }

        return aux;
    }

    public void searchWord(String text) {
        TrieNode aux = checkPrefix(text);

        if(aux == null){
            System.out.println("A palavra '"+text+"' não existe na árvore.");
        }
        else if(aux.isWord()) {
            System.out.println("A palavra '"+text+"' existe na árvore.");
        }

    }

    public void searchForPrefix(String text) {
        ArrayList<String> words = new ArrayList<>();
        TrieNode aux = checkPrefix(text);

        if(aux != null) {
            words = getSugestions(words, aux, text);
            if(words.isEmpty()) {
                System.out.println("\nNenhuma sugestão encontrada para '"+text+"'.");
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

    public void checkRemove(String word) {
        boolean check = remove(word);
        if(check == true) {
            System.out.println("A palavra '"+word+"' foi removida com sucesso.");
        }else {
            System.out.println("Remoção inválida! A palavra '"+word+"' não foi encontrada na árvore.");
        }
    }

    public boolean remove(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        TrieNode aux = null;
        char deleteLetter = '\0';

        TrieNode parent = root;
        for(char letter : word.toCharArray()){

            TrieNode child = parent.getChildren().get(letter);
            if (child == null) {
                return false;
            }

            if (parent.getChildren().size() > 1 || parent.isWord()) {
                aux = parent;
                deleteLetter = letter;
            }
            parent = child;
        }

        if (!parent.isWord()) {
            return false;
        }

        if (parent.getChildren().isEmpty()) {
            aux.getChildren().remove(deleteLetter);
        } else {
            parent.setWord(false);
        }

        return true;
    }

}
