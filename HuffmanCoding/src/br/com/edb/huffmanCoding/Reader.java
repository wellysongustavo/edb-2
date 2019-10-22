package br.com.edb.huffmanCoding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
}


