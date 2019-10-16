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

    public static void leituraArquivo() {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        Scanner ler = new Scanner(System.in);

        //System.out.printf("Informe o nome de arquivo texto:\n");
        //Precisa verificar uma forma de indicar o caminho da pasta independente de qual pc rodar
        //esse eh temporário e tem que criar antes de executar
        String nome = "/tmp/file.txt";  //ler.nextLine();

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try {
            FileReader arquivo= new FileReader(nome);
            BufferedReader ler_arquivo = new BufferedReader(arquivo);

            String linha = ler_arquivo.readLine(); // lê a primeira linha

            // a variável "linha" recebe o valor "null" quando o processo
            // de repetição atingir o final do arquivo texto
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
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println(map);
    }
}


