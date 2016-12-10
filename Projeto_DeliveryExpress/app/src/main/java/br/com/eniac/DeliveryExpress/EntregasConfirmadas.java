package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;


public class EntregasConfirmadas extends Activity implements View.OnClickListener{


    private ListView txtlista;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_entregasconfirmadas);

        List<PerfilClientePOJO> listaClientes;

        listaClientes = CarregarListaClientes();

        PerfilClienteAdapter adaptador = new PerfilClienteAdapter(this, listaClientes);

        txtlista = (ListView) findViewById(R.id.ListEntreg);
        Button Voltar = (Button) findViewById(R.id.btnEntregVoltar);

        txtlista.setAdapter(adaptador);
        Voltar.setOnClickListener(this);

        //CRIAÇÃO DE BOTÃO ANONIMO



        txtlista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                TextView txtId = (TextView) v.findViewById(R.id.itemId);

                Intent i = new Intent(EntregasConfirmadas.this, TelaEntregadorListaID2.class);
                i.putExtra("ID", txtId.getText().toString());
                Log.d("BuscaLista", txtId.getText().toString());
                startActivity(i);
                finish();


            }
        });




    }

    public void onClick(View v) {

        if (v.getId() == R.id.btnEntregVoltar) {
            Intent sair = new Intent(EntregasConfirmadas.this, PerfilEntregador.class);
            startActivity(sair);
            finish();
        }

      /*  if (v.getId() == R.id.btnEntregEnviar) {


            PerfilEntregadorDAO bd=new PerfilEntregadorDAO(getBaseContext());
            String retorno=bd.ConfirmaEntrega(idEntrega);
            Intent enviar = new Intent(TelaEntregadorListaID.this, PerfilEntregadorLista.class);
            Toast.makeText(TelaEntregadorListaID.this, "Parabèns Entrega Confirmada", Toast.LENGTH_LONG).show();
            startActivity(enviar);
            finish();

        }*/
    }


    public List<PerfilClientePOJO> CarregarListaClientes() {
        List<PerfilClientePOJO> lista = new LinkedList<PerfilClientePOJO>();

        try {
            PerfilEntregadorDAO bd = new PerfilEntregadorDAO(getBaseContext());


            String msg;

            Cursor retorno = bd.CarregaEntregasEntregador();


            if (retorno.moveToFirst()) {
                do {

                    PerfilClientePOJO item = new PerfilClientePOJO();

                    item.setId(retorno.getInt(0));
                    item.setCidade(retorno.getString(1));
                    item.setEstado(retorno.getString(2));
                    item.setHorario(retorno.getString(3));
                    item.setStatus(retorno.getString(4));
                    item.setNumero(retorno.getString(5));
                    item.setEndereco(retorno.getString(6));
                    item.setPrazoEntreg(retorno.getString(7));
                    item.setPontoRef(retorno.getString(8));
                    item.setCliente(retorno.getString(9));
                    item.setEntregador(retorno.getString(10));

                    lista.add(item);

                } while (retorno.moveToNext());
            }
            retorno.close();

        } catch (Exception e) {
            Log.e("BANCO", "Listar=" + e.getMessage());
            mensagem("Erro ao consultar");
        }

        return lista;
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
