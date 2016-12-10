package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class TelaLogin extends Activity implements View.OnClickListener {


    public EditText txtusuario, txtsenha;
    private RadioButton txtentregador, txtcliente;
    private Button txtbtnLogin,txtbtnCad, txtbtnSair;
    public static String vusuario =null ;
    public static String vtipo =null ;
    public static String vsenha =null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_telalogin);


        txtusuario = (EditText) findViewById(R.id.editUsuario);
        txtsenha = (EditText) findViewById(R.id.editSenha);
        txtentregador = (RadioButton) findViewById(R.id.rdbEntreg);
        txtcliente = (RadioButton) findViewById(R.id.rdbCli);

        txtbtnLogin = (Button) findViewById(R.id.btnEnt);
        txtbtnCad = (Button) findViewById(R.id.btnCad);
        txtbtnSair = (Button) findViewById(R.id.btnLoginSair);

        txtbtnCad.setOnClickListener(this);
        txtbtnLogin.setOnClickListener(this);
        txtbtnSair.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnEnt) {
            login();
        }
        if (v.getId() == R.id.btnCad) {
            Intent novocadastro = new Intent(this,Cadastro.class);
            startActivity(novocadastro);
            finish();
        }
        if (v.getId() == R.id.btnLoginSair) {
            finish();
        }

    }



    public void login() {
        if(txtusuario.length() != 0
                && txtsenha.length() != 0
                && (txtentregador.getText() != null || txtcliente != null)) {


            vusuario = txtusuario.getText().toString();
            vsenha = txtsenha.getText().toString();
            vtipo = "";

            if (txtentregador.isChecked() == true) {

                vtipo = "Entregador";
            }
            if(txtcliente.isChecked()==true) {

                vtipo = "Cliente";
            }
            CadastroDAO bd = new CadastroDAO(getBaseContext());

            Cursor retorno = bd.Login(vusuario, vsenha, vtipo);

            if (retorno.moveToFirst()) {
                if(retorno.getInt(1)==0) {

                    Toast.makeText(this, "Seja bem vindo  " + retorno.getString(0),
                            Toast.LENGTH_LONG).show();

                    //INTENT PARA IR PARA A TELA PRINCIPAL
                    //SE O USUARIO FOR ENTREGADOR
                if(vtipo=="Entregador"){

                    Intent perfilentregador = new Intent(this,PerfilEntregador.class);
                    startActivity(perfilentregador);
                    finish();
                    }else{

                    //SE O USUARIO FOR CLIENTE
                    Intent perfilcliente = new Intent(this,PerfilCliente.class);

                    perfilcliente.putExtra("CLIENTE", retorno.getString((0)) );
                    startActivity(perfilcliente);
                    finish();
                }

                }else{

                    Toast.makeText(this, "VOCÊ JA ESTA LOGADO  ",
                            Toast.LENGTH_LONG).show();

                    //INTENT PARA IR PARA A TELA PRINCIPAL

                    if(vtipo=="Entregador"){

                        Intent perfilentregador = new Intent(this,PerfilEntregador.class);
                        startActivity(perfilentregador);
                        finish();
                    }else{

                        //SE O USUARIO FOR CLIENTE
                        Intent perfilcliente = new Intent(this,PerfilCliente.class);
                        startActivity(perfilcliente);
                        finish();
                    }

                }

            } else {

                Toast.makeText(this, "Usuario e senha incorretos",
                        Toast.LENGTH_LONG).show();
            }

        }else{

            Toast.makeText(this, "PREENCHA TODOS OS CAMPOS",
                    Toast.LENGTH_LONG).show();
        }

    }


}
