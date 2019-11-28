package com.edb.trietree.wells;

public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("amei");
        trie.insert("ameixa");
        trie.searchWord("ameixaa");
        //System.out.println(trie.getRoot().isWord());
    }
}
