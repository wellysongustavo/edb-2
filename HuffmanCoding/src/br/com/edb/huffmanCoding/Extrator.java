package br.com.edb.huffmanCoding;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;

public class Extrator {
    private String arquivo_comprimido;
    private String  arquivo_tabela_de_codificacao;
    private String arquivo_de_texto;
    private HashMap<String, String> tabela_codificacao;

    Extrator(String arg1, String arg2, String arg3){
        arquivo_comprimido = arg1;
        arquivo_tabela_de_codificacao = arg2;
        arquivo_de_texto = arg3;
    }

    public void extrair() throws IOException {
        Reader reader = new Reader();
        BitSet leitura_bits = reader.leituraArquivoBinario(arquivo_comprimido);
        FileWriter arquivo_descomprimido = new FileWriter(arquivo_de_texto);
        BufferedWriter descomprimido = new BufferedWriter(arquivo_descomprimido);

        tabela_codificacao = new HashMap<String, String>();
        preencherTabela(tabela_codificacao);
        //System.out.println(tabela_codificacao);

        int i = 0;
        String codigo = "";
        int aux = 0;
        while (aux == 0){
            //reescrevendo texto em string binária
            codigo += leitura_bits.get(i) ? "1" : "0";
            //procura existência do codigo na tabela
            String caractere = tabela_codificacao.get(codigo);

            if(caractere != null){
                if(caractere.equals("EOF")){
                    aux = 1;
                }else{
                    //se houver correspondência do código no map
                    descomprimido.write(caractere);
                    //zera codigo de busca
                    codigo = "";
                }
            }
            i++;
        }
        //System.out.println(codigo);
        descomprimido.close();
        printCompressionPercents(arquivo_comprimido, arquivo_de_texto);
    }

    private void preencherTabela(HashMap<String, String> tabela_codificacao) throws IOException {
        FileReader arquivo = new FileReader(arquivo_tabela_de_codificacao);
        BufferedReader tabela = new BufferedReader(arquivo);
        String linha = "";

        while ((linha = tabela.readLine()) != null){
            if(linha.contains("EOF")){
                tabela_codificacao.put(linha.substring(3),linha.substring(0,3));
            }else{
                tabela_codificacao.put(linha.substring(1),linha.substring(0,1));
            }
        }
        tabela.close();
    }

    public void printCompressionPercents (String arquivo_comprimido, String arquivo_de_texto) throws IOException {
        Reader reader = new Reader();
        BitSet leitura_bits = reader.leituraArquivoBinario(arquivo_comprimido);
        BitSet leitura_bits_original = reader.leituraArquivoBinario(arquivo_de_texto);
        System.out.println("\n----------------------------HUFFMAN CODING----------------------------\n");
        System.out.println("Tamanho do arquivo original: "+ leitura_bits_original.length());
        System.out.println("Tamanho do arquivo comprimido: "+ leitura_bits.length()+ "\n");
        double taxa = (leitura_bits.length()*100)/leitura_bits_original.length();
        System.out.println("----->  O arquivo foi comprimido "+taxa+"% do seu tamanho original. <-----");

    }
}
