package br.com.wells.FilaBanco;

public class Pessoa {
    private String nome;
    private int idade;
    private FilaBanco heap;

    public Pessoa(String nome, int idade, FilaBanco heap) {
        this.nome = nome;
        this.idade = idade;
        this.heap = heap;
    }

    public FilaBanco getHeap() {
        return heap;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
        this.heap.updateHeap(this);
    }

}