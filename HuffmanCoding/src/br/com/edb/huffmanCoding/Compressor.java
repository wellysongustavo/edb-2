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

        //ordenar elementos de acordo com suas frequências
        Map<Character, Integer> sorted = map.entrySet()
                                            .stream()
                                            .sorted(Map.Entry.<Character, Integer>comparingByValue())
                                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                                    (e1,e2) -> e1, LinkedHashMap::new));
        System.out.println(sorted);

        //transformar elementos em nós e inserir na min heap
        BinaryTree minHeap = new BinaryTree();
        for (Character c : sorted.keySet()) {
            Node no = new Node(c, map.get(c));
            minHeap.insert(no);
        }
        System.out.println(minHeap.getSize());
        System.out.println("oi");

        createBinaryTree(minHeap);
    }

    public void createBinaryTree(BinaryTree minHeap) {
        if(!minHeap.isEmpty()){
            //System.out.println(minHeap.getSize());
            //createTableCod(minHeap);
        }
        while (minHeap.getSize() > 1){

            Node left = minHeap.peek();
            minHeap.remove();
            Node right = minHeap.peek();
            minHeap.remove();

            Node node = new Node();
            node.setFrequency(left.getFrequency() + right.getFrequency());

            node.setLeft(left);
            node.setRight(right);

            minHeap.insert(node);

        }

        JFrame window = new JFrame(); //é uma estrutura organizada em árvore
        window.setSize(400,300);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //qnd fechar janela mata o processo
//JLabel label = new JLabel("Hello, world!!!!"); // cada filho também é uma árvore
//window.add(label); // comentado pois não vamos nos aprofundar
        ArvoreBinariaBuscaView view = new ArvoreBinariaBuscaView(minHeap);
        window.add(view);
        window.setVisible(true);

        System.out.println("cheguei aqui");
    }


    public void createTableCod(BinaryTree minHeap) {


    }


}
