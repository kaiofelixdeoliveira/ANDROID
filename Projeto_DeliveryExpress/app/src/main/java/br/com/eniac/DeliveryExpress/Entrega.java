package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Entrega extends Activity implements View.OnClickListener {



    private EditText txtendereco, txtcidade, txtnumero, txtestado, txtpontoRef;
    private Button btnCad, btnVoltar;
    private Spinner spinerEst;

    private TimePicker txthorario;
    private DatePicker txtprazoEntreg;

    private List<String> estados =new ArrayList<String>();
    private String txtEstados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_entrega);


        spinerEst = (Spinner) findViewById(R.id.spinerEntregEstado);

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

        txtendereco = (EditText) findViewById(R.id.editEntregEndereco);
        txtcidade = (EditText) findViewById(R.id.editEntregCidade);
        txtnumero = (EditText) findViewById(R.id.editEntregNumero);
        txtpontoRef = (EditText) findViewById(R.id.editEntregPontRef);
        txthorario = (TimePicker) findViewById(R.id.editEntregHorario);
        txtprazoEntreg = (DatePicker) findViewById(R.id.editEntregPrazo);

        btnCad = (Button) findViewById(R.id.btnEntregCadastrar);
        btnVoltar = (Button) findViewById(R.id.btnEntregVoltar);

        btnCad.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);

    }

    public void onClick(View v) {

        // TODO Auto-generated method stub
        if (v.getId() == R.id.btnEntregCadastrar) {
            cadastrarPedido();

        }

        if (v.getId() == R.id.btnEntregVoltar) {
            Intent PerfilClienteVoltar = new Intent(this, PerfilCliente.class);
            startActivity(PerfilClienteVoltar);
            finish();
        }
    }
    public void cadastrarPedido() {

        if (txtendereco.length() != 0
                && txtcidade.length() != 0
                && txtnumero.length() != 0
                && txtpontoRef.length() != 0
                && txthorario != null
                && txtprazoEntreg != null) {

            int vEstLogin = 0;
            String vendereco = txtendereco.getText().toString();

            String vcidade = txtcidade.getText().toString();
            String vnumero = txtnumero.getText().toString();
            String vestado = txtEstados;
            String vpontoRef = txtpontoRef.getText().toString();
            String vhorario = String.valueOf(txthorario.getCurrentHour())+
                    ":"+String.valueOf(txthorario.getCurrentMinute())+" hrs";

            String vprazoEntreg = "Até "+ String.valueOf(txtprazoEntreg.getDayOfMonth())+
                                 "/"+String.valueOf(txtprazoEntreg.getMonth())+
                                 "/"+String.valueOf(txtprazoEntreg.getYear());


            PerfilClienteDAO bd = new PerfilClienteDAO(getBaseContext());

            String msg;
            msg = bd.SolicPeddEntreg(vendereco, vcidade, vnumero, vestado, vpontoRef, vhorario, vprazoEntreg);

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();


            limpadados();

            Intent CadEntrega = new Intent(Entrega.this, Mercadoria.class);
            startActivity(CadEntrega);
            finish();

        } else {

            Toast.makeText(this, "PREENCHA TODOS OS CAMPOS CORRETAMENTE", Toast.LENGTH_LONG).show();
        }


    }


    private void limpadados(){
        txtendereco.setText("");
        txtcidade.setText("");
        txtnumero.setText("");
        txtpontoRef.setText("");

    }



}
