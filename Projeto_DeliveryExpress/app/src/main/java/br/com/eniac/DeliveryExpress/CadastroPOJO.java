package br.com.eniac.DeliveryExpress;

/**
 * Created by ponto frio on 23/02/2016.
 */
public class CadastroPOJO {

    private String nomecompleto;
    private String cidade;
    private String endereco;
    private String estado;
    private String senha;
    private String cpf;

    public CadastroPOJO(){

    }

    public CadastroPOJO(String nomecompleto, String cidade, String endereco,
                        String estado, String senha, String cpf, String cnpj,
                        String sexo, String tipo, String usuario, int idade) {
        this.nomecompleto = nomecompleto;
        this.cidade = cidade;
        this.endereco = endereco;
        this.estado = estado;
        this.senha = senha;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.sexo = sexo;
        this.tipo = tipo;
        this.usuario = usuario;
        this.idade = idade;
    }

    private String cnpj;
    private String sexo;
    private String tipo;
    private String usuario;
    private int idade;

    public String getNomecompleto() {
        return nomecompleto;
    }

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }





}
