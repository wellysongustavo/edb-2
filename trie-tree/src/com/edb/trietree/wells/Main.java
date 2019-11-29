package com.edb.trietree.wells;

public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("amei");
        trie.insert("ameixa");
        trie.insert("amor");
        trie.insert("amora");
        trie.searchWord("amor");
        trie.searchForPrefix("am", 3);
        trie.searchForPrefix("amo");
        //System.out.println(trie.getRoot().isWord());
    }
}
