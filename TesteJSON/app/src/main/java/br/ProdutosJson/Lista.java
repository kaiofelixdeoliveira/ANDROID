package br.ProdutosJson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class Lista extends Activity{


    private MetodosJSON service = new MetodosJSON();
    private AdapterProd adapterProd;
    private ListView txtlista;


    protected void onResume() {
        super.onResume();

        carregarDados();
    }

    private void carregarDados() {
        new CarregarMeusProdutosTask().execute();

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista);



        txtlista = (ListView) findViewById(R.id.lista);
        Button voltar = (Button) findViewById(R.id.btnvoltar);



        voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (v.getId() == R.id.btnvoltar) {
                    Intent voltar = new Intent(Lista.this, MainActivity.class);


                    startActivity(voltar);
                    finish();
                }

            }

        });

        carregarDados();

    }





    private class CarregarMeusProdutosTask extends AsyncTask<String, Void, List<ProdutoPOJO>> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(Lista.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(List<ProdutoPOJO> produtos) {

                adapterProd = new AdapterProd(getBaseContext(), produtos);


                txtlista.setAdapter(adapterProd);

                dialog.dismiss();

            }


        @Override
        protected List<ProdutoPOJO> doInBackground(String... params) {
            return service.getAll();
        }
    }


}
