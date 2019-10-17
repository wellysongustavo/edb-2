package br.com.edb.huffmanCoding;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

//import static java.util.Map.Entry.comparingByValue;

public class Compressor {
    private String arquivo_de_texto;
    private String arquivo_comprimido;
    private String arquivo_tabela_de_codificacao;

    Compressor(String arg1, String arg2, String arg3){
        arquivo_de_texto = arg1;
        arquivo_comprimido = arg2;
        arquivo_tabela_de_codificacao = arg3;
    }

    public void comprimir() {
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

        for (char c : sorted.keySet()) {

        }

        //adicionando na árvore
        BinaryTree arvore = new BinaryTree();
    }
}
