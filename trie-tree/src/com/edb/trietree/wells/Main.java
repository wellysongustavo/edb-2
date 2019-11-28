package com.edb.trietree.wells;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String arquivo = args[0];
        String prefixo = args[1];
        String quantidade = "-1";
        Trie trie = new Trie();

        if(args.length > 2){
            quantidade = args[2];
        }

        try {
            FileReader ler = new FileReader("arquivos/"+arquivo);
            BufferedReader reader = new BufferedReader(ler);
            String row;
            while( (row = reader.readLine()) != null ){
                trie.insert(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        trie.searchForPrefix(prefixo, Integer.parseInt(quantidade));

        trie.remove("ameixa");

        trie.searchWord("amor");
    }
}
