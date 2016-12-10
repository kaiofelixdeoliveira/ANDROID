package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;


public class PerfilEntregadorLista extends Activity implements AdapterView.OnItemClickListener {

    private EditText txtpesquisa;
    private ListView txtlista;
    private String txtFiltro = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_lista2);


        txtpesquisa = (EditText) findViewById(R.id.editPesquisa);

        txtlista = (ListView) findViewById(R.id.listaEntreg);
        Button Voltar = (Button) findViewById(R.id.btnListaVoltar);
        Button buscar = (Button) findViewById(R.id.btnPesquisar);


        txtlista.setOnItemClickListener(this);

        List<PerfilClientePOJO> listaClientes;

        listaClientes = CarregarListaClientes();

        PerfilClienteAdapter adaptador = new PerfilClienteAdapter(this, listaClientes);


        //AUTOCOMPLETADOR

        txtlista.setAdapter(adaptador);

        Voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (v.getId() == R.id.btnListaVoltar) {
                    Intent sair = new Intent(PerfilEntregadorLista.this, PerfilEntregador.class);

                    startActivity(sair);
                    finish();
                }

            }

        });

        buscar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (v.getId() == R.id.btnPesquisar) {
                    if(txtFiltro.length()!=0) {

                        List<PerfilClientePOJO> listaClientes;

                        listaClientes = CarregarListaClientes();

                        PerfilClienteAdapter adaptador = new PerfilClienteAdapter(PerfilEntregadorLista.this, listaClientes);


                        //AUTOCOMPLETADOR

                        txtlista.setAdapter(adaptador);
                    }else{
                        Toast.makeText(getBaseContext(), "Nada Encontrado :[ ", Toast.LENGTH_LONG).show();

                    }


                }
            }

        });




        //CRIAÇÃO DE BOTÃO ANONIMO




        //FILTRO DE PESQUISA


    }


    //FILTRO
    // DE PESQUISA QUE NÃO DEU CERTO

        /*txtpesquisa.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {


                    adaptador.getFilter().filter(s.toString());


            }
        });*/


    /*public List<PerfilClientePOJO> buscaEntregasClientes() {

        String txtBusca=txtpesquisa.getText().toString();
        List<PerfilClientePOJO> lista = new LinkedList<PerfilClientePOJO>();
        try {
            PerfilEntregadorDAO busca = new PerfilEntregadorDAO(getBaseContext());


            String msg;

            Cursor retorno = busca.BuscaEntregasCliente(txtBusca);


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
    }*/

    //CARREGA OS CLIENTES
    public List<PerfilClientePOJO> CarregarListaClientes() {

        if(txtpesquisa.length()!= 0) {
            txtFiltro = txtpesquisa.getText().toString().trim();

        }else{
            txtFiltro="vazio";
        }
        Log.d("txtFiltro = ",txtFiltro);



        List<PerfilClientePOJO> lista = new LinkedList<PerfilClientePOJO>();
        try {
            PerfilEntregadorDAO bd = new PerfilEntregadorDAO(getBaseContext());



            String msg;

            Cursor retorno = bd.CarregaEntregasCliente(txtFiltro);

            String mensagem="";
            Log.d("Linhas de pesquisa", mensagem.valueOf(retorno));



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
            }else{
                Toast.makeText(this, "Nada Encontrado :[ ", Toast.LENGTH_LONG).show();
            }


            retorno.close();


        } catch (Exception e) {
            Log.e("BANCO", "Listar=" + e.getMessage());
            mensagem("Erro ao consultar");
        }


        return lista;
    }

    public void bindview(View convert,Context arg1,Cursor cursor){

        List<PerfilClientePOJO> listaClientes;

        listaClientes = CarregarListaClientes();

        PerfilClienteAdapter adaptador = new PerfilClienteAdapter(PerfilEntregadorLista.this, listaClientes);

        //AUTOCOMPLETADOR

        txtlista.setAdapter(adaptador);
        if(listaClientes.equals("Novo")) {
            convert.setBackgroundColor(Color.BLUE);
        }else {
            convert.setBackgroundColor(Color.RED);
        }
    }



    public void mensagem(String msg) {
        Context contexto = getApplicationContext();
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(contexto, "Voce clicou aqui", duracao);
        toast.show();
    }


    public void onItemClick(AdapterView<?> a, View v, int position, long id) {

        TextView txtId = (TextView) v.findViewById(R.id.itemId);

        Intent i = new Intent(PerfilEntregadorLista.this, TelaEntregadorListaID.class);
        i.putExtra("ID", txtId.getText().toString());
        Log.d("BuscaLista", txtId.getText().toString());
        startActivity(i);
        finish();


    }


    protected void onResume() {
        super.onResume();


    }

}
