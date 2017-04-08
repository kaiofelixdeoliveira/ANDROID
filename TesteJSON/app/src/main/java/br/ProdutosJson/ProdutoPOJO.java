package br.ProdutosJson;

/**
 * Created by ponto frio on 14/02/2016.
 */
public class ProdutoPOJO {

    private String nome;
    private Double preco;
    private int quantidade;
    private String _id;

    public ProdutoPOJO(String _id, String nome, Double preco, int quantidade) {
        this._id = _id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public ProdutoPOJO() {

    }


    public String get_id() {
        return _id;
    }

    public String set_id(String _id) {
        this._id = _id;
        return _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;

    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


}
