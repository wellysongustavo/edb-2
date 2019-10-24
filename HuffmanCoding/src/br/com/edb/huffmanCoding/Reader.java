package br.com.edb.huffmanCoding;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Reader {

    public Map<Character, Integer> leituraArquivo(String arquivo_de_teste) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        try {
            FileReader arquivo = new FileReader("arquivos-de-teste/"+arquivo_de_teste);
            BufferedReader ler_arquivo = new BufferedReader(arquivo);

            String linha = ler_arquivo.readLine();

            System.out.println("\nConteúdo de "+arquivo_de_teste+": ");
            while (linha != null) {
                System.out.printf("%s\n", linha);

                // loop para contagem da frenquência de cada caractere
                for (int i = 0; i < linha.length(); i++) {
                    char c = linha.charAt(i);
                    Integer val = map.get(c);
                    if (val != null) {
                        map.put(c, new Integer(val + 1));
                    } else {
                        map.put(c, 1);
                    }
                }
                linha = ler_arquivo.readLine(); // lê da segunda até a última linha
            }

            arquivo.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        //System.out.println(map);
        return map;
    }

    public BitSet leituraArquivoBinario(String arquivo_comprimido) throws IOException {
        Path caminho = Paths.get(arquivo_comprimido);
        byte[] leitura_bytes = Files.readAllBytes(caminho);
        BitSet leitura_bits = BitSet.valueOf(leitura_bytes);
        return leitura_bits;
    }
}


