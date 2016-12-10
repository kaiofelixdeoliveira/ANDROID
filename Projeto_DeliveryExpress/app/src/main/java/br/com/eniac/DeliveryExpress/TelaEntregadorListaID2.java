package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


    public class TelaEntregadorListaID2 extends Activity implements View.OnClickListener {
        private int idEntrega;
        private TextView txtVolume, txtPeso, txtCodigo, txtPericulo, txtTipo, txtCodigoEntreg, txtCliente, txtEntregador, txtCidade, txtEstado,
                txtHorario, txtStatus, txtNumero, txtEndereco, txtPrazo, txtPontoRef;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.layout_telaentregentregador2);

            //LISTA DE CLIENTES

            txtCodigoEntreg = (TextView) findViewById(R.id.itemId);
            txtCliente = (TextView) findViewById(R.id.itemCliente);
            txtEntregador = (TextView) findViewById(R.id.itemEntregador);
            txtEndereco = (TextView) findViewById(R.id.itemEndereco);
            txtCidade = (TextView) findViewById(R.id.itemCidade);
            txtEstado = (TextView) findViewById(R.id.itemEstado);
            txtHorario = (TextView) findViewById(R.id.itemHorario);
            txtStatus = (TextView) findViewById(R.id.itemStatus);
            txtNumero = (TextView) findViewById(R.id.itemNumero);
            txtPrazo = (TextView) findViewById(R.id.itemPrazoEntreg);
            txtPontoRef = (TextView) findViewById(R.id.itemPontRef);


            //LISTA DE MERCADORIA


            txtPericulo = (TextView) findViewById(R.id.itemPericulo);
            txtPeso = (TextView) findViewById(R.id.itemPeso);
            txtVolume = (TextView) findViewById(R.id.itemVolume);
            txtCodigo = (TextView) findViewById(R.id.itemCodigo);
            txtTipo = (TextView) findViewById(R.id.itemTipo);


            Button Voltar = (Button) findViewById(R.id.btnVoltar);
            Button Entregue = (Button) findViewById(R.id.btnEntregue);
            Button Andamento= (Button) findViewById(R.id.btnAndamento);



            Voltar.setOnClickListener(this);
            Entregue.setOnClickListener(this);
            Andamento.setOnClickListener(this);

            CarregarListaMercadoria();
            CarregarListaClientes();


        }


        public void onClick(View v) {

            if (v.getId() == R.id.btnVoltar) {
                Intent sair = new Intent(TelaEntregadorListaID2.this, EntregasConfirmadas.class);
                startActivity(sair);
                finish();
            }


            if (v.getId() == R.id.btnAndamento) {


                if(txtStatus.getText().toString().equals("Entregador Aceito")) {

                    Bundle bundle = getIntent().getExtras();


                    idEntrega = Integer.parseInt(bundle.getString("ID"));
                    String statusEnvia = "Em Andamento";
                    PerfilEntregadorDAO bd = new PerfilEntregadorDAO(getBaseContext());
                    String retorno = bd.ConfirmaEntrega(idEntrega, statusEnvia);
                    Intent enviar = new Intent(TelaEntregadorListaID2.this, EntregasConfirmadas.class);
                    Toast.makeText(TelaEntregadorListaID2.this, "Parabèns Entrega em Andamento", Toast.LENGTH_LONG).show();
                    startActivity(enviar);
                    finish();
                }else{

                    Toast.makeText(TelaEntregadorListaID2.this, "O Cliente Deve Aceitar Primeiro ", Toast.LENGTH_LONG).show();


                }

            }

            if (v.getId() == R.id.btnEntregue) {

                if(txtStatus.getText().toString().equals("Em Andamento")) {


                    Bundle bundle = getIntent().getExtras();

                    idEntrega = Integer.parseInt(bundle.getString("ID"));
                    String statusEnvia = "Entregue";
                    PerfilEntregadorDAO bd = new PerfilEntregadorDAO(getBaseContext());
                    String retorno = bd.ConfirmaEntrega(idEntrega, statusEnvia);
                    Intent enviar = new Intent(TelaEntregadorListaID2.this, EntregasConfirmadas.class);
                    Toast.makeText(TelaEntregadorListaID2.this, "Entrega Concluida!!", Toast.LENGTH_LONG).show();
                    startActivity(enviar);
                    finish();
                }else{
                    Toast.makeText(TelaEntregadorListaID2.this, "A entrega deve estar com status de em andamento ", Toast.LENGTH_LONG).show();
                }

            }
        }




        public void CarregarListaClientes() {

            Bundle bundle = getIntent().getExtras();

            idEntrega = Integer.parseInt(bundle.getString("ID"));

            try {
                PerfilEntregadorDAO bd = new PerfilEntregadorDAO(getBaseContext());


                String msg;

                Cursor retorno = bd.ConsultEntregEntregas(idEntrega);

                if (retorno.moveToFirst()) {


                    txtCodigoEntreg.setText(String.valueOf(retorno.getInt(0)));
                    txtCidade.setText(retorno.getString(1));
                    txtEstado.setText(retorno.getString(2));
                    txtHorario.setText(retorno.getString(3));
                    txtStatus.setText(retorno.getString(4));
                    txtNumero.setText(retorno.getString(5));
                    txtEndereco.setText(retorno.getString(6));
                    txtPrazo.setText(retorno.getString(7));
                    txtPontoRef.setText(retorno.getString(8));
                    txtEntregador.setText(retorno.getString(9));
                    txtCliente.setText(retorno.getString(10));




                }else{

                    Toast.makeText(this, "Erro ao consultar dados",
                            Toast.LENGTH_LONG).show();
                }
                retorno.close();


            } catch (Exception e) {
                Log.e("BANCO", "Listar=" + e.getMessage());
                mensagem("Erro ao consultar");
            }

        }

        public void CarregarListaMercadoria() {

            Bundle bundle = getIntent().getExtras();

            idEntrega = Integer.parseInt(bundle.getString("ID"));

            try {
                MercadoriaDAO bd = new MercadoriaDAO(getBaseContext());


                String msg;

                Cursor retorno = bd.ConsultMerc(idEntrega);

                if (retorno.moveToFirst()) {

                    txtCodigo.setText(String.valueOf(retorno.getInt(0)));
                    txtVolume.setText(retorno.getString(1));
                    txtTipo.setText(retorno.getString(2));
                    txtPericulo.setText(retorno.getString(3));
                    txtPeso.setText(retorno.getString(4));
                }else{

                    Toast.makeText(this, "Erro ao consultar dados",
                            Toast.LENGTH_LONG).show();

                }
                retorno.close();

            } catch (Exception e) {
                Log.e("BANCO", "Listar=" + e.getMessage());
                mensagem("Erro ao consultar");
            }

        }


        public void mensagem(String msg) {
            Context contexto = getApplicationContext();
            int duracao = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(contexto, "Voce clicou aqui", duracao);
            toast.show();
        }


        protected void onResume() {
            super.onResume();

            CarregarListaClientes();
        }
    }
