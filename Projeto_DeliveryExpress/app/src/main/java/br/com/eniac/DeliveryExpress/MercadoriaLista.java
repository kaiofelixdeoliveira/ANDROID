package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;


public class MercadoriaLista{
/*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_telaentregcliente);

        List<PerfilClientePOJO> listaClientes;

        listaClientes = CarregarListaClientes();

        PerfilClienteAdapter adaptador = new PerfilClienteAdapter(this, listaClientes);

        txtlista = (ListView) findViewById(R.id.ListEntregID);
        Button Enviar = (Button) findViewById(R.id.btnClienteEnviar);
        Button Voltar = (Button) findViewById(R.id.btnClienteVoltar);

        txtlista.setAdapter(adaptador);
        Enviar.setOnClickListener(this);
        Voltar.setOnClickListener(this);

        //CRIAÇÃO DE BOTÃO ANONIMO




    }


    public void onClick(View v) {

        if (v.getId() == R.id.btnClienteVoltar) {
            Intent sair = new Intent(MercadoriaLista.this, PerfilClienteLista.class);
            startActivity(sair);
            finish();
        }

        if (v.getId() == R.id.btnClienteEnviar) {
            Bundle bundle = getIntent (). getExtras ();

            idEntrega = Integer.parseInt(bundle.getString("ID"));

            PerfilClienteDAO bd=new PerfilClienteDAO(getBaseContext());
            String retorno=bd.AltEstEntreg(idEntrega);
            Intent enviar = new Intent(MercadoriaLista.this, PerfilClienteLista.class);
            Toast.makeText(MercadoriaLista.this, "Parabèns Entrega Confirmada", Toast.LENGTH_LONG).show();
            startActivity(enviar);
            finish();

        }
    }




    //CARREGA OS CLIENTES
    public List<PerfilClientePOJO> CarregarListaClientes() {
        List<PerfilClientePOJO> lista = new LinkedList<PerfilClientePOJO>();

        Bundle bundle = getIntent (). getExtras ();

        idEntrega = Integer.parseInt(bundle.getString("ID"));

        try {
            PerfilClienteDAO bd = new PerfilClienteDAO(getBaseContext());


            String msg;

            Cursor retorno = bd.VisuEstEntreg(idEntrega);


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
                    item.setPontoRef(retorno.getString(7));
                    item.setPrazoEntreg(retorno.getString(8));
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
    }*/
}
