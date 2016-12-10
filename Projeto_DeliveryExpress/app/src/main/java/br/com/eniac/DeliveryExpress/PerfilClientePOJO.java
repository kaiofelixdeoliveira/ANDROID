package br.com.eniac.DeliveryExpress;

import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by ponto frio on 14/11/2015.
 */
public class PerfilClientePOJO {
    private String cliente;
    private String entregador;
    private int id;
    private String cidade ;
    private String numero ;
    private String estado ;
    private String pontoRef ;
    private String horario ;
    private String prazoEntreg ;
    private String endereco;
    private String status;

    public PerfilClientePOJO(){


    }

    public PerfilClientePOJO(String cliente, String entregador, int id, String cidade, String numero, String estado, String pontoRef, String horario, String prazoEntreg, String endereco, String status) {
        this.cliente = cliente;
        this.entregador = entregador;
        this.id = id;
        this.cidade = cidade;
        this.numero = numero;
        this.estado = estado;
        this.pontoRef = pontoRef;
        this.horario = horario;
        this.prazoEntreg = prazoEntreg;
        this.endereco = endereco;
        this.status = status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEntregador() {
        return entregador;
    }

    public void setEntregador(String entregador) {
        this.entregador = entregador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPontoRef() {
        return pontoRef;
    }

    public void setPontoRef(String pontoRef) {
        this.pontoRef = pontoRef;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPrazoEntreg() {
        return prazoEntreg;
    }

    public void setPrazoEntreg(String prazoEntreg) {
        this.prazoEntreg = prazoEntreg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
