package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cadastro extends Activity implements View.OnClickListener {

    private static final String URL = "https://api.mlab.com/api/1/databases/aplicativo_android/collections/cadastro";
    private static final String API_KEY = "?apiKey=3_-Ooah_DIe6SgsbTR8hrci8ivNt3_2g";

    private EditText txtnomecompleto, txtidade, txtendereco, txtusuario, txtsenha, txtcnpj, txtcpf, txtcidade, txtestado;
    private RadioButton txtfem, txtcli, txtentreg, txtmasc, txtPfisica, txtPjuridica;
    private Button btnCad, btnAlt, btnVoltar, btnConsult, btnExclu;
    private Spinner spinerEst;

    private List<String> estados =new ArrayList<String>();
    private String txtEstados;

    String vnomecompleto,  vcidade, vendereco,
    vestado,  vsenha,  vcpf, vcnpj,
    vsexo,  vtipo, vusuario;
    int vidade;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastro);

        //CRIAÇÃO DO SPINNER ESTADOS



        spinerEst = (Spinner) findViewById(R.id.spinerEstad);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.estados));

        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinerEst.setAdapter(spinnerArrayAdapter);

        spinerEst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                txtEstados=parent.getItemAtPosition(position).toString();
                //Toast.makeText(Cadastro.this, txtEstados, Toast.LENGTH_LONG).show();

            }

            public void onNothingSelected(AdapterView<?>parent){

            }

        });

        txtPfisica = (RadioButton) findViewById(R.id.rdbFisica);
        txtPjuridica = (RadioButton) findViewById(R.id.rdbJuridica);
        txtnomecompleto = (EditText) findViewById(R.id.editNome);
        txtmasc = (RadioButton) findViewById(R.id.editSexMasc);
        txtfem = (RadioButton) findViewById(R.id.editSexFem);
        txtentreg = (RadioButton) findViewById(R.id.editCadEntreg);
        txtcli = (RadioButton) findViewById(R.id.editCadCli);
        txtidade = (EditText) findViewById(R.id.editIdade);
        txtendereco = (EditText) findViewById(R.id.editEndereco);
        txtusuario = (EditText) findViewById(R.id.editUsuario);
        txtsenha = (EditText) findViewById(R.id.editSenha);
        txtcpf = (EditText) findViewById(R.id.editCPF);
        txtcnpj = (EditText) findViewById(R.id.editCNPJ);
        txtcidade = (EditText) findViewById(R.id.editCidade);
        txtestado = (EditText) findViewById(R.id.editEstado);

        btnVoltar = (Button) findViewById(R.id.btnCadVolt);
        btnCad = (Button) findViewById(R.id.btncadastrar);


        btnCad.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);
        txtPjuridica.setOnClickListener(this);
        txtPfisica.setOnClickListener(this);

        txtcnpj.setEnabled(false);
        txtcpf.setEnabled(false);



    }




    @Override
    public void onClick(View v) {

        // TODO Auto-generated method stub
        if (v.getId() == R.id.btncadastrar) {

            incluirUsuario();

            GravarDados();


        }


        if (v.getId() == R.id.btnCadVolt) {
            Intent Cadvoltar = new Intent(this, TelaLogin.class);
            startActivity(Cadvoltar);
            finish();
        }


        if (v.getId() == R.id.rdbJuridica) {

            txtcnpj.setEnabled(true);
            txtcpf.setEnabled(false);
            txtcpf.setHint("");
            txtcnpj.setHint("Digite seu CNPJ");
        }

        if (v.getId() == R.id.rdbFisica) {

            txtcnpj.setEnabled(false);
            txtcpf.setEnabled(true);
            txtcnpj.setHint("");
            txtcpf.setHint("Digite seu CPF");

        }



    }

/*
    public void post() {


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

                    CadastroPOJO cadastro = new CadastroPOJO();
                    cadastro.getNomecompleto();
                    cadastro.getCidade();
                    cadastro.getEndereco();
                    cadastro.getEstado();
                    cadastro.getSenha();
                    cadastro.getCpf();
                    cadastro.getCnpj();
                    cadastro.getSexo();
                    cadastro.getTipo();
                    cadastro.getUsuario();
                    cadastro.getIdade();


                    Gson gson = new Gson();
                    String json = gson.toJson(cadastro);

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


    }*/


    public void incluirUsuario() {

        if (txtnomecompleto.length() != 0
                && (txtfem.getText() != null || txtmasc != null)
                && txtidade.length() != 0
                && txtendereco.length() != 0
                && txtusuario.length() != 0
                && txtsenha.length() != 0
                && (txtentreg.getText() != null || txtcli != null)
                && txtcidade.length() != 0
                && (txtcpf.length() != 0 || txtcnpj.length() != 0)) {

            int vEstLogin = 0;
            vnomecompleto = txtnomecompleto.getText().toString();

            String msgsexo = "";

            if (txtfem.isChecked() == true) {
                msgsexo = "Feminino";
            }

            if (txtmasc.isChecked() == true) {
                msgsexo = "Masculino";

            }

             vsexo = msgsexo;

            vidade = Integer.parseInt(txtidade.getText().toString());
            vendereco = txtendereco.getText().toString();
             vusuario = txtusuario.getText().toString();
             vsenha = txtsenha.getText().toString();
             vcpf = txtcpf.getText().toString();
             vcnpj = txtcnpj.getText().toString();
            String msgtipo = "";

            if (txtentreg.isChecked() == true) {
                msgtipo = "Entregador";
            }

            if (txtcli.isChecked() == true) {
                msgtipo = "Cliente";

            }

            vtipo = msgtipo;

            vcidade = txtcidade.getText().toString();
            vestado = txtEstados;

            String msg;

            /*CadastroPOJO pojo=new CadastroPOJO();

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
*/


            /*msg=cadastro.IncluirUsuario(vnomecompleto,  vcidade, vendereco,
                     vestado,  vsenha,  vcpf, vcnpj,
                     vsexo,  vtipo, vusuario,  vidade);

            if (msg != "Este usuario ja esta sendo usado") {
                limpadados();
            }
*/
         limpadados();


        } else {

            Toast.makeText(this, "PREENCHA TODOS OS CAMPOS CORRETAMENTE", Toast.LENGTH_LONG).show();
        }

    }

    private void GravarDados(){

        new GravarMeusProdutosTask().execute();
    }


    public Boolean limpadados() {

        txtnomecompleto.setText("");
        txtfem.setChecked(false);
        txtmasc.setChecked(false);
        txtidade.setText("");
        txtendereco.setText("");
        txtusuario.setText("");
        txtsenha.setText("");
        txtentreg.setChecked(false);
        txtcli.setChecked(false);
        txtcidade.setText("");
        txtcpf.setText("");
        txtcnpj.setText("");
        return true;
    }

    private class GravarMeusProdutosTask extends AsyncTask<String, String, String> {
        private ProgressDialog dialog;

        String mensagem="";
        @Override
        //ACESSO A THREAD PRINCIPAL
        protected void onPreExecute() {

            dialog = new ProgressDialog(Cadastro.this);
            dialog.setMessage("LOADING...");
            dialog.show();
        }

        //*********** SEMPRE RETORNA ALGO*******
        @Override
        protected String doInBackground(String... params) {


            CadastroDAO cadastro=new CadastroDAO(getBaseContext());

            mensagem=cadastro.post(vnomecompleto,  vcidade, vendereco,
                    vestado,  vsenha,  vcpf, vcnpj,
                    vsexo,  vtipo, vusuario,  vidade);


                 return mensagem;
        }


        //*É CHAMADO QUANDO O doInBackground RETORNA ALGUMA COISA
        protected void onPostExecute(String retorno) {


            CadastroPOJO pojo=new CadastroPOJO();
            /*Log.d("getNomecompleto",pojo.getNomecompleto());
            Log.d("getCidade", pojo.getCidade());
            Log.d("getEndereco",pojo.getEndereco());*/
            Log.d("MENSAGEM---------",retorno);
            dialog.setMessage(retorno);

            dialog.dismiss();

        }


    }



}

