package br.com.eniac.DeliveryExpress;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static br.com.eniac.DeliveryExpress.TelaLogin.vsenha;
import static br.com.eniac.DeliveryExpress.TelaLogin.vtipo;
import static br.com.eniac.DeliveryExpress.TelaLogin.vusuario;


public class CadastroDAO {

    private static final String URL = "https://api.mlab.com/api/1/databases/aplicativo_android/collections/cadastro";
    private static final String API_KEY = "?apiKey=3_-Ooah_DIe6SgsbTR8hrci8ivNt3_2g";


    private SQLiteDatabase db;
    private BancoDados banco;

    public CadastroDAO(Context baseContext) {
    }

    /*public CadastroDAO(Context context) {
        banco = new BancoDados(context);
    }*/



    /*public String IncluirUsuario(String vnomecompleto, String vcidade, String vendereco,
                                 String vestado, String vsenha, String vcpf, String vcnpj,
                                 String vsexo, String vtipo, String vusuario, int vidade) {

        ContentValues valores;
        Long resultado;

        Cursor dados = null;


        String condicao = "usuario = '" + vusuario + "'";

        db = banco.getReadableDatabase();


        if (vtipo == "Entregador") {

            dados = db.query("entregador", null, condicao, null, null, null, null);

        } else {
            dados = db.query("cliente", null, condicao, null, null, null, null);
        }


        if (dados.moveToFirst()==false) {



            db = banco.getWritableDatabase();

            valores = new ContentValues();

            valores.put("nomecompleto", vnomecompleto);
            valores.put("sexo", vsexo);
            valores.put("idade", vidade);
            valores.put("endereco", vendereco);
            valores.put("usuario", vusuario);
            valores.put("senha", vsenha);
            valores.put("cpf", vcpf);
            valores.put("cnpj", vcnpj);
            valores.put("tipo", vtipo);
            valores.put("cidade", vcidade);
            valores.put("estado", vestado);


            if (vtipo == "Entregador") {
                resultado = db.insert("entregador", null, valores);
            } else {
                resultado = db.insert("cliente", null, valores);
            }
            db.close();

            if (resultado == -1) {
                return "Erro Ao Cadastrar";
            } else {
                return "Usuario Cadastrado :]";
            }
        }else{
            db.close();
            return "Este usuario ja esta sendo usado";
        }

    }

*/



    public String post(String vnomecompleto, String vcidade, String vendereco,
                       String vestado, String vsenha, String vcpf, String vcnpj,
                       String vsexo, String vtipo, String vusuario, int vidade) {



        HttpURLConnection urlConnection = null;
        String mensagem="";
        try {

            urlConnection = null;
            URL url = new URL(URL + API_KEY);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            urlConnection.setRequestProperty("Accept", "application/json");

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            Writer w = new BufferedWriter(new OutputStreamWriter(out));

            CadastroPOJO pojo=new CadastroPOJO();
            pojo.setNomecompleto(vnomecompleto);
            pojo.setCidade(vcidade);
            pojo.setEndereco(vendereco);
            pojo.setEstado(vestado);
            pojo.setSenha(vsenha);
            pojo.setCpf(vcpf);
            pojo.setCnpj(vcnpj);
            pojo.setSexo(vsexo);
            pojo.setTipo(vtipo);
            pojo.setUsuario(vusuario);
            pojo.setIdade(vidade);


            Gson gson = new Gson();
            String json = gson.toJson(pojo);

            w.write(json);
            w.close();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.nextLine();
            in.close();
            mensagem="CADASTRO EFETUADO COM SUCESSO!!";
        } catch (Exception e) {

            mensagem="FALHA AO FAZER O CADASTRO";

            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();

        }


        return mensagem;

    }

    public String ExcluirUsuario() {


        String condicao = "usuario = '" + vusuario + "' and senha='" + vsenha + "'";
        db = banco.getReadableDatabase();
        int retorno;


        if (vtipo == "Entregador") {
            retorno = db.delete("entregador", condicao, null);
        } else {
            retorno = db.delete("cliente", condicao, null);
        }
        db.close();

        if (retorno == -1) {

            return "Erro ao Encerrar Conta";

        } else {

            return "Conta Encerrada Com Sucesso";
        }
    }


    public Cursor Login(String vusuario, String vsenha, String vtipo) {


        Cursor dados = null;

        String[] campos = {"nomecompleto,estLogin"};
        String condicao = "usuario = '" + vusuario + "' and senha='" + vsenha + "' and tipo ='" + vtipo + "'";

        db = banco.getReadableDatabase();


        if (vtipo == "Entregador") {
            dados = db.query("entregador", campos, condicao, null, null, null, null);
        } else {
            dados = db.query("cliente", campos, condicao, null, null, null, null);
        }


        if (dados != null) {
            dados.moveToFirst();

            ContentValues valores = new ContentValues();

            valores.put("estLogin", 1);

            String condicao2 = "usuario = '" + vusuario + "' and senha='" + vsenha + "'";
            db = banco.getWritableDatabase();

            //USUARIO LOGADO FICARA COM STATUS 1 E USUARIO NAO LOGADO FICARA COM STATUS 0

            int estadoLogin;

            if (vtipo == "Entregador") {
                estadoLogin = db.update("entregador", valores, condicao2, null);
            } else {
                estadoLogin = db.update("cliente", valores, condicao2, null);
            }

        }

        db.close();
        return dados;
    }


    public String Logout() {

        String msg = "";

        ContentValues valores = new ContentValues();

        valores.put("estLogin", 0);

        String condicao = "usuario = '" + vusuario + "' and senha='" + vsenha + "'";

        db = banco.getWritableDatabase();

        int resultado = 0;


        if (vtipo == "Entregador") {
            resultado = db.update("entregador", valores, condicao, null);
        } else {
            resultado = db.update("cliente", valores, condicao, null);
        }

        db.close();

        if (resultado <= 0) {
            msg = "Erro ao Fazer Logout!!";
        } else {
            msg = "Logout Realizado com Sucesso!!!";
        }
        return msg;
    }

    public String AlterarUsuario(String vnomecompleto, String vcidade, String vendereco,
                                 String vsenha, String vestado, String vcpf, String vcnpj,
                                 int vidade) {

        String msg = "";
        ContentValues valores = new ContentValues();

        valores.put("nomecompleto", vnomecompleto);
        valores.put("idade", vidade);
        valores.put("endereco", vendereco);
        valores.put("senha", vsenha);
        valores.put("cpf", vcpf);
        valores.put("cnpj", vcnpj);
        valores.put("cidade", vcidade);
        valores.put("estado", vestado);

        String condicao = "usuario = '" + vusuario + "' and senha='" + vsenha + "' and tipo='" + vtipo + "'";

        db = banco.getWritableDatabase();
        int resultado = 0;

        if (vtipo == "Entregador") {
            resultado = db.update("entregador", valores, condicao, null);
        } else {
            resultado = db.update("cliente", valores, condicao, null);
        }
        db.close();

        if (resultado <= 0) {
            msg = "Erro ao alterar";
        } else {
            msg = "Registro alterado com sucesso!!!";
        }

        return msg;
    }


    public Cursor ConsultarUsuario() {
        Cursor dados = null;

        String[] campos = {"nomecompleto", "idade", "endereco", "senha", "cpf", "cnpj", "cidade", "estado"};
        String condicao = "usuario = '" + vusuario + "' and senha='" + vsenha + "' and tipo='" + vtipo + "'";

        db = banco.getReadableDatabase();


        if (vtipo == "Entregador") {

            dados = db.query("entregador", campos, condicao, null, null, null, null);

        } else {
            dados = db.query("cliente", campos, condicao, null, null, null, null);
        }

        if (dados != null) {
            dados.moveToFirst();
        }

        db.close();
        return dados;
    }

}
