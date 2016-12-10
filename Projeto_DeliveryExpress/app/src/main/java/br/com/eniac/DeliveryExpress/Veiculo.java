package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Veiculo extends Activity implements View.OnClickListener {

    private EditText txtCOD, txtcor, txtpeso, txtmodelo, txtplaca, txttipo, txtentregador;

    private Button btEnviar,btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_veiculo);

        txtplaca = (EditText) findViewById(R.id.editPlaca);
        txtcor = (EditText) findViewById(R.id.editCor);
        txtmodelo = (EditText) findViewById(R.id.editModelo);
        txtpeso = (EditText) findViewById(R.id.editPeso);
        txttipo = (EditText) findViewById(R.id.editTipo);

        btVoltar = (Button) findViewById(R.id.btnVoltar);
        btEnviar = (Button) findViewById(R.id.btnEnviar);

        btVoltar.setOnClickListener(this);
        btEnviar.setOnClickListener(this);

    }

    public void incluirveiculo() {

        if (txtplaca.length() != 0
                && txtcor.length() != 0
                && txtpeso.length() != 0
                && txtmodelo.length() != 0
                && txttipo.length() != 0) {

            int vEstLogin = 0;


            String vplaca = txtplaca.getText().toString();
            String vcor = txtcor.getText().toString();
            String vpeso = txtpeso.getText().toString();
            String vmodelo = txtmodelo.getText().toString();
            String vtipo = txttipo.getText().toString();

            VeiculoDAO bd = new VeiculoDAO(getBaseContext());

            String msg;
            msg = bd.IncluirVeiculo(vplaca, vcor, vpeso, vmodelo, vtipo);

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            limpadados();


        } else {

            Toast.makeText(this, "PREENCHA TODOS OS CAMPOS CORRETAMENTE", Toast.LENGTH_LONG).show();
        }

    }

    public Boolean limpadados() {

        txtpeso.setText("");
        txtplaca.setText("");
        txtmodelo.setText("");
        txttipo.setText("");
        txtcor.setText("");
        return true;
    }

    public void onClick(View v) {

        // TODO Auto-generated method stub
        if (v.getId() == R.id.btnVoltar) {


            Intent Cadvoltar = new Intent(this,PerfilEntregador.class);
            startActivity(Cadvoltar);
            finish();
        }


        if (v.getId() == R.id.btnEnviar) {
            incluirveiculo();
        }


    }
}
