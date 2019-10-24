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
        encodeText();
        storeCodingTable();
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
        minHeap.insert(new Node("EOF", 1));
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

    public void encodeText() throws IOException {

        FileReader fr = new FileReader("arquivos-de-teste/"+arquivo_de_texto);
        OutputStream compressed = new BufferedOutputStream(new FileOutputStream(arquivo_comprimido));
        BitSet encoded_bitset = new BitSet();
        //BufferedReader buff = new BufferedReader(fr);

        int n_bits = 0;
        int c;
        String encoded_string = "";
        while ((c = fr.read()) != -1){
            encoded_string = binary_table.get(Character.toString((char)c));
            System.out.println(encoded_string);
            //transformando de string para bitset cada caractere lido acima
            if(encoded_string != null){
                for (int i = 0; i < encoded_string.length(); i++){
                    encoded_bitset.set(n_bits, encoded_string.charAt(i) - '0' > 0 ? true : false);
                    n_bits++;
                }
            }
        }
        //acrescentando o código do EOF
        encoded_string = binary_table.get("EOF");
        System.out.println(encoded_string);
        for(int i = 0; i < encoded_string.length(); i++) {
            encoded_bitset.set(n_bits, encoded_string.charAt(i) - '0' > 0 ? true : false);
            n_bits++;
        }

        System.out.println(encoded_bitset.length());
        byte[] bytes = encoded_bitset.toByteArray();
        compressed.write(bytes);
        fr.close();
        compressed.close();
    }


    public void storeCodingTable() throws IOException {
        FileWriter file = new FileWriter(arquivo_tabela_de_codificacao);
        PrintWriter write = new PrintWriter(file);

        for (Map.Entry<String, String> coding : binary_table.entrySet()) {
            write.printf(coding.getKey()+coding.getValue()+"\r\n");
        }

        file.close();
    }



}
