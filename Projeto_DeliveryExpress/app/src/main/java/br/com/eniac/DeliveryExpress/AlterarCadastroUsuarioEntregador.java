package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ponto frio on 21/11/2015.
 */
public class AlterarCadastroUsuarioEntregador extends Activity implements View.OnClickListener{

    private EditText txtnomecompleto, txtidade, txtendereco, txtsenha, txtcnpj, txtcpf, txtcidade, txtestado;
    private Button btnAlt, btnVoltar, btConsultar;

    private Spinner spinerEst;

    private List<String> estados =new ArrayList<String>();
    private String txtEstados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alteracadastroentregador);

        spinerEst = (Spinner) findViewById(R.id.spinerEstado);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.estados));

        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinerEst.setAdapter(spinnerArrayAdapter);

        spinerEst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                txtEstados = parent.getItemAtPosition(position).toString();
                //Toast.makeText(Cadastro.this, txtEstados, Toast.LENGTH_LONG).show();

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        txtnomecompleto = (EditText) findViewById(R.id.editNome);
        txtidade = (EditText) findViewById(R.id.editIdade);
        txtendereco = (EditText) findViewById(R.id.editEndereco);
        txtsenha = (EditText) findViewById(R.id.editSenha);
        txtcpf = (EditText) findViewById(R.id.editCPF);
        txtcnpj = (EditText) findViewById(R.id.editCNPJ);
        txtcidade = (EditText) findViewById(R.id.editCidade);


        btnVoltar = (Button) findViewById(R.id.btnCadVolt);
        btnAlt = (Button) findViewById(R.id.btnAlterar);
        btConsultar = (Button) findViewById(R.id.btnConsultar);


        btnAlt.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);
        btConsultar.setOnClickListener(this);


    }


    private void alterarUsuario() {

        if (txtnomecompleto.length() != 0
                && txtidade.length() != 0
                && txtendereco.length() != 0
                && txtsenha.length() != 0
                && txtcidade.length() != 0
                && txtcpf.length() != 0 || txtcnpj.length() != 0) {

            String vnomecompleto = txtnomecompleto.getText().toString();

            int vidade = Integer.parseInt(txtidade.getText().toString());
            String vendereco = txtendereco.getText().toString();
            String vsenha = txtsenha.getText().toString();
            String vcpf = txtcpf.getText().toString();
            String vcnpj = txtcnpj.getText().toString();

            String vcidade = txtcidade.getText().toString();
            String vestado = txtEstados;

            CadastroDAO bd = new CadastroDAO(getBaseContext());

            String msg;

            msg = bd.AlterarUsuario(vnomecompleto, vcidade, vendereco,vsenha, vestado, vcpf, vcnpj, vidade);

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            limpadados();

        } else {
            Toast.makeText(this, "PREENCHA TODOS OS CAMPOS CORRETAMENTE", Toast.LENGTH_LONG).show();
        }


    }


    public void consultaUsuario() {



        CadastroDAO bd = new CadastroDAO(getBaseContext());

        Cursor retorno = bd.ConsultarUsuario();

        if (retorno.moveToFirst()) {
            txtnomecompleto.setText(retorno.getString(0));

            txtidade.setText(String.valueOf(retorno.getInt(1)));
            txtendereco.setText(retorno.getString(2));
            txtsenha.setText(retorno.getString(3));
            txtcnpj.setText(retorno.getString(4));
            txtcpf.setText(retorno.getString(5));

            txtcidade.setText(retorno.getString(6));
            txtEstados=(retorno.getString(7));

        } else {

            Toast.makeText(this, "Usuario e senha incorretos",
                    Toast.LENGTH_LONG).show();
        }

    }


    public Boolean limpadados() {

        txtnomecompleto.setText("");
        txtidade.setText("");
        txtendereco.setText("");
        txtsenha.setText("");
        txtcidade.setText("");
        txtcpf.setText("");
        txtcnpj.setText("");
        return true;
    }

    public void onClick(View v) {

        // TODO Auto-generated method stub


        if (v.getId() == R.id.btnAlterar) {
            alterarUsuario();
        }


        if (v.getId() == R.id.btnCadVolt) {
            Intent Cadvoltar = new Intent(this, PerfilEntregador.class);
            startActivity(Cadvoltar);
            finish();
        }
        if (v.getId() == R.id.btnConsultar) {
            consultaUsuario();
        }


    }

}
