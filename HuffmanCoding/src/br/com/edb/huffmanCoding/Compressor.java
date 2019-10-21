package br.com.edb.huffmanCoding;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

//import static java.util.Map.Entry.comparingByValue;

public class Compressor {
    private String arquivo_de_texto;
    private String arquivo_comprimido;
    private String arquivo_tabela_de_codificacao;
    private Map<Character, Integer> map;
    private Map<String, Integer> binary_table;
    private BinaryTree minHeap;

    Compressor(String arg1, String arg2, String arg3){
        arquivo_de_texto = arg1;
        arquivo_comprimido = arg2;
        arquivo_tabela_de_codificacao = arg3;
    }

    public void sortedMap() {
        Reader reader = new Reader();
        map = reader.leituraArquivo(arquivo_de_texto);

        //transformar elementos em nós e inserir na min heap
        minHeap = new BinaryTree();
        for (Character c : map.keySet()) {
            Node no = new Node(String.valueOf(c), map.get(c));
            minHeap.insert(no);
        }
        createBinaryTree();
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
        createBinaryTable();
    }

    public void createBinaryTable() {
        binary_table = new HashMap<String, Integer>();
        Node root = minHeap.peek();
        String binary = "";
        //percorrer árvores até cada folha e capturar seu código binário
        //findLeaf(root, binary);
        System.out.println(binary_table);
    }

    public void findLeaf(Node root, String binary){
        if (root == null){
            return;
        }
        if(root.isLeaf()){
            binary_table.put(root.getLetter(), Integer.valueOf(binary));
            binary = binary.substring(0,binary.length()-1);
            return;
        }else{
            if(root.getLeft() != null){
                binary = binary.concat("0");
                findLeaf(root.getLeft(),binary);
            }
            if(root.getRight() != null){
                binary = binary.concat("1");
                findLeaf(root.getRight(),binary);
            }
        }
    }


}
