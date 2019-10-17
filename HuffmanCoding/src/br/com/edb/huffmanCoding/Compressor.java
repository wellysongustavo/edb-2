package br.com.edb.huffmanCoding;

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
        reader.leituraArquivo(arquivo_de_texto);
    }
}
