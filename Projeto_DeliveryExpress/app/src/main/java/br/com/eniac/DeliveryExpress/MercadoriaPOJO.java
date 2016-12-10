package br.com.eniac.DeliveryExpress;


public class MercadoriaPOJO {

    private String volume;
    private String peso;
    private int codigo ;
    private String cliente ;
    private String periculosidade ;
    private String tipo ;

    public MercadoriaPOJO (){

    }



    public MercadoriaPOJO(String volume, String peso, int codigo, String cliente, String periculosidade, String tipo) {
        this.volume = volume;
        this.peso = peso;
        this.codigo = codigo;
        this.cliente = cliente;
        this.periculosidade = periculosidade;
        this.tipo = tipo;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getPericulosidade() {
        return periculosidade;
    }

    public void setPericulosidade(String periculosidade) {
        this.periculosidade = periculosidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
