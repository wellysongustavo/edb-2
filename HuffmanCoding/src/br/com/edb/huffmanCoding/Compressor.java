package br.com.edb.huffmanCoding;

import javax.swing.*;
import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Compressor {
    private String arquivo_de_texto;
    private String arquivo_comprimido;
    private String arquivo_tabela_de_codificacao;
    private Map<Character, Integer> map;
    private Map<String, String> binary_table;
    private BinaryTree minHeap;

    Compressor(String arg1, String arg2, String arg3){
        arquivo_de_texto = arg1;
        arquivo_comprimido = arg2;
        arquivo_tabela_de_codificacao = arg3;

    }

    public void comprimir() throws Exception{
        transformCharToNode();
        createBinaryTree();
        createBinaryTable();
        //encodeText();
        //storeCodingTable();
    }

    public void transformCharToNode(){
        Reader reader = new Reader();
        map = reader.leituraArquivo(arquivo_de_texto);

        //transformar elementos em nós e inserir na min heap
        minHeap = new BinaryTree();
        for (Character c : map.keySet()) {
            Node no = new Node(String.valueOf(c), map.get(c));
            minHeap.insert(no);
        }
    }

    public void createBinaryTree() {
        while (minHeap.getSize() > 1){

            Node left = minHeap.peek();
            minHeap.remove();
            Node right = minHeap.peek();
            minHeap.remove();

            Node node = new Node();
            node.setFrequency(left.getFrequency() + right.getFrequency());
            node.setLetter(String.valueOf(left.getFrequency() + right.getFrequency()));

            node.setLeft(left);
            node.setRight(right);

            minHeap.insert(node);
        }
        //minHeap.printTree();
    }

    public void createBinaryTable() {
        binary_table = new HashMap<String, String>();
        Node root = minHeap.peek();
        String binary = "";
        findLeaf(root, binary);
        System.out.println(binary_table);
    }

    public void findLeaf(Node root, String binary){
        if(root.isLeaf()){
            binary_table.put(root.getLetter(), binary);
            return;

        }else{
            findLeaf(root.getLeft(),binary + "0");
            findLeaf(root.getRight(),binary + "1");
        }
    }
/*
    public void encodeText() throws IOException {

        FileReader fr = new FileReader("arquivos-de-teste/"+arquivo_de_texto);

        //BufferedReader buff = new BufferedReader(fr);
        //FileOutputStream out = new FileOutputStream(arquivo_comprimido);

        int c;
        String encoded_string = "";
        while ((c = fr.read()) != -1){
            encoded_string.concat(binary_table.get(String.valueOf(c)));
        }
        System.out.println(encoded_string);
        fr.close();

        BitSet encoded_bitset = new BitSet();
        int index = encoded_string.length() - encoded_string.length() % 8;
        encoded_bitset = fromString(encoded_string.substring(0,index));
        encoded_string = encoded_string.substring(index+1);
        System.out.println(encoded_string);

        encoded_bitset = fromString(encoded_string.substring(0,encoded_string.length()));
        encoded_string = "";
    }

    private static BitSet fromString(final String s) {
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }

    private static String toString(BitSet bs) {
        return Long.toString(bs.toLongArray()[0], 2);
    }
*/
    public void storeCodingTable() throws IOException {
        FileWriter file = new FileWriter(arquivo_tabela_de_codificacao);
        PrintWriter write = new PrintWriter(file);

        for (Map.Entry<String, String> coding : binary_table.entrySet()) {
            write.printf(coding.getKey()+coding.getValue()+"\r\n");
        }

        file.close();
    }



}
