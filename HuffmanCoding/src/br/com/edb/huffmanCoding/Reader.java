package br.com.edb.huffmanCoding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        //System.out.printf("Informe o nome de arquivo: ");
        String nome = "br/com/edb/huffmanCoding/arquivo.txt/";//ler.nextLine();

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try {
            FileReader arquivo = new FileReader(nome);
            BufferedReader lerArquivo = new BufferedReader(arquivo);

            String linha = lerArquivo.readLine(); // lê a primeira linha
            // a variável "linha" recebe o valor "null" quando o processo
            // de repetição atingir o final do arquivo texto
            while (linha != null) {
                System.out.printf("%s\n", linha);

                linha = lerArquivo.readLine(); // lê da segunda até a última linha
            }

            arquivo.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        System.out.println();
    }
}


