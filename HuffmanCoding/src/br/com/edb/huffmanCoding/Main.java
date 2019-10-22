package br.com.edb.huffmanCoding;

public class Main {
    public static void main(String[] args) throws Exception{
        if(args[0].equals("compress")){

            Compressor compressor = new Compressor(args[1], args[2], args[3]);
            compressor.comprimir();

        }else if(args[0].equals("extract")){
            Extrator extrator = new Extrator(args[1], args[2], args[3]);
            extrator.extrair();

        }else{
            System.out.println("nada absolutamente nada");
        }
    }
}
