package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.content.Intent;
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

public class Mercadoria extends Activity implements View.OnClickListener {

    EditText txttipo, txtpeso, txtpericulo, txtvolume;
    Button btEnviar,btVoltar;
    private Spinner spinerMerc;
    private Spinner spinerPericulosidade;

    private String txtPericulosidade;
    private String txtMercadoria;

    private List<String> tipoMerc =new ArrayList<String>();
    private List<String> tipoPericulosidade =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mercadoria);

        //*******************SPINNER DO TIPO DE MERCADORIA**********************

        spinerMerc = (Spinner) findViewById(R.id.editTipo);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.tipoMerc));

        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinerMerc.setAdapter(spinnerArrayAdapter);

        spinerMerc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                txtMercadoria = parent.getItemAtPosition(position).toString();
                //Toast.makeText(Cadastro.this, txtEstados, Toast.LENGTH_LONG).show();

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        //************************SPINNER DE PERICULOSIDADE************************

        spinerPericulosidade = (Spinner) findViewById(R.id.editPericulo);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.tipoPericulosidade));

        ArrayAdapter<String> spinnerArrayAdapter2 = arrayAdapter2;

        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinerPericulosidade.setAdapter(spinnerArrayAdapter2);

        spinerPericulosidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                txtPericulosidade = parent.getItemAtPosition(position).toString();
                //Toast.makeText(Cadastro.this, txtEstados, Toast.LENGTH_LONG).show();

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        txtpeso = (EditText) findViewById(R.id.editPeso);
        txtvolume = (EditText) findViewById(R.id.editVolume);
        btEnviar = (Button) findViewById(R.id.btnEnviar);

        btEnviar.setOnClickListener(this);
    }

    public void onClick(View v) {

     /*   if (v.getId() == R.id.btnVoltar){
            Intent Mercvoltar = new Intent(this,PerfilCliente.class);
            startActivity(Mercvoltar);
            finish();


        }*/

        // TODO Auto-generated method stub
        if (v.getId() == R.id.btnEnviar) {
            cadastrarMerc();
            Intent Mercvoltar = new Intent(this,PerfilCliente.class);
            startActivity(Mercvoltar);
            finish();

        }
    }
    public void cadastrarMerc() {

        if (txtvolume.length() != 0
                && txtpeso.length() != 0) {


            String vpericulo = txtPericulosidade;
            String vpeso = txtpeso.getText().toString();
            String vtipo = txtMercadoria;
            String vvolume = txtvolume.getText().toString();

            MercadoriaDAO bd = new MercadoriaDAO(getBaseContext());

            String msg;
            msg = bd.CadMerc(vpericulo, vpeso, vtipo, vvolume);

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();


        } else {

            Toast.makeText(this, "PREENCHA TODOS OS CAMPOS CORRETAMENTE", Toast.LENGTH_LONG).show();
        }



    }


    private void limpadados(){
        txtpericulo.setText("");
        txtpeso.setText("");
        txttipo.setText("");
        txtvolume.setText("");


    }


}
