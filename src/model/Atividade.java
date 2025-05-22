package model;

public class Atividade {
    private String titulo;
    private String descricao;
    private String dataEntrega;

    public Atividade(String titulo, String descricao, String dataEntrega) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
    }

    // Getters e setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(String dataEntrega) { this.dataEntrega = dataEntrega; }

    public String toString() { return "[" + dataEntrega + "]" + "[" + titulo + "]" + " - " + descricao; }
}