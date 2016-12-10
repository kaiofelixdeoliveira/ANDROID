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


public class PerfilClienteLista extends Activity implements AdapterView.OnItemClickListener {

    private ListView txtlista;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_lista);

        List<PerfilClientePOJO> listaClientes;

        listaClientes = CarregarListaClientes();

        PerfilClienteAdapter adaptador = new PerfilClienteAdapter(this, listaClientes);

        txtlista = (ListView) findViewById(R.id.lista);
        Button button = (Button) findViewById(R.id.btnLista);
        txtlista.setAdapter(adaptador);

        txtlista.setOnItemClickListener(this);

        //CRIAÇÃO DE BOTÃO ANONIMO
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (v.getId() == R.id.btnLista) {
                    Intent novocadastro = new Intent(PerfilClienteLista.this, PerfilCliente.class);
                    startActivity(novocadastro);
                    finish();

                }
            }
        });


    }
    //CARREGA OS CLIENTES
    public List<PerfilClientePOJO> CarregarListaClientes() {
        List<PerfilClientePOJO> lista = new LinkedList<PerfilClientePOJO>();
        try {
            PerfilClienteDAO bd = new PerfilClienteDAO(getBaseContext());


            String msg;

            Cursor retorno = bd.CarregaEntregas();


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


    public void onItemClick(AdapterView<?> a, View v, int position, long id) {

        TextView txtId = (TextView) v.findViewById(R.id.itemId);

        Intent i = new Intent(PerfilClienteLista.this, TelaClienteListaID.class);
        i.putExtra("ID", txtId.getText().toString());
        Log.d("BuscaLista", txtId.getText().toString());
        startActivity(i);
        finish();



    }


    protected void onResume() {
        super.onResume();

        CarregarListaClientes();
    }

   /*    protected void onItemClick( ListView l, View v, int position, long id){
        PerfilClientePOJO lista= new PerfilClientePOJO();
    }
        String item=(String) getPerfilClienteAda().getItem(position);
        Intent CadEntrega = new Intent(this, Entrega.class);
        startActivity(CadEntrega);
        finish();


    }
    */
}
