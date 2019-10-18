package br.com.edb.huffmanCoding;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

//import static java.util.Map.Entry.comparingByValue;

public class Compressor {
    private String arquivo_de_texto;
    private String arquivo_comprimido;
    private String arquivo_tabela_de_codificacao;
    private BinaryTree arvore;

    Compressor(String arg1, String arg2, String arg3){
        arquivo_de_texto = arg1;
        arquivo_comprimido = arg2;
        arquivo_tabela_de_codificacao = arg3;
    }

    public void sortedMap() {
        Reader reader = new Reader();
        Map<Character, Integer> map = reader.leituraArquivo(arquivo_de_texto);

        //transformar elementos em nós e inserir na min heap
        BinaryTree minHeap = new BinaryTree();
        for (Character c : map.keySet()) {
            Node no = new Node(String.valueOf(c), map.get(c));
            minHeap.insert(no);
        }
        createBinaryTree(minHeap);
    }

    public void createBinaryTree(BinaryTree minHeap) {
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
        minHeap.printTree();
        createTableBinary(minHeap);
    }


    public void createTableBinary(BinaryTree minHeap) {

    }


}
