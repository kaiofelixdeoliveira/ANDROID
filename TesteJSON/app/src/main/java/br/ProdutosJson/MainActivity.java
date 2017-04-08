package br.ProdutosJson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String URL = "https://api.mlab.com/api/1/databases/aplicativo_android/collections/cadastro";
    private static final String API_KEY = "?apiKey=3_-Ooah_DIe6SgsbTR8hrci8ivNt3_2g";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity);

        Button postar = (Button) findViewById(R.id.send);
        Button deletar = (Button) findViewById(R.id.del);
        Button atualizar = (Button) findViewById(R.id.put);
        Button lista = (Button) findViewById(R.id.lista);

        postar.setOnClickListener(this);
        deletar.setOnClickListener(this);
        atualizar.setOnClickListener(this);
        lista.setOnClickListener(this);

    }


    public void onClick(View v) {

        if (v.getId() == R.id.send) {


            post();

        }


        if (v.getId() == R.id.del) {


            delete();

        }

        if (v.getId() == R.id.put) {


            atualizar();

        }
        if (v.getId() == R.id.lista) {


            Intent listar = new Intent(MainActivity.this,Lista.class);

            startActivity(listar);
            finish();
        }



    }
    /*protected void onPostExecute(List<ProdutoPOJO> produtos) {
         if (produtos != null) {
             adapter = new ProdutoArrayAdapter(getBaseContext(), 0, produtos);
             ((ListView) findViewById(R.id.produtos)).setAdapter(adapter);

    }

        public class ProdutoArrayAdapter extends ArrayAdapter<ProdutoPOJO> {
             private Context context;
             private List<ProdutoPOJO> produtos;

                     public ProdutoArrayAdapter(Context context, int resource, List<ProdutoPOJO> objects) {
                 super(context, resource, objects);
                 this.context = context;
                 this.produtos = objects;
                 }
*/

    public void post() {


        Thread envio = new Thread(new Runnable() {

            public void run() {
                HttpURLConnection urlConnection = null;

                try {

                    String mensagem;
                    urlConnection = null;
                    URL url = new URL(URL + API_KEY);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");

                    //urlConnection.setRequestProperty("Accept", "application/json");

                    OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                    Writer w = new BufferedWriter(new OutputStreamWriter(out));


                    ProdutoPOJO produtos = new ProdutoPOJO();
                    /**GERA UMA CHAVE EM HEXADECIMAL ALEATORIA PARA ENVIAR COMO ID**/
                    Random rand = new Random();
                    String chaveID = Long.toHexString(rand.nextLong()) + Long.toHexString(rand.nextLong());
                    produtos.set_id(chaveID);
                    produtos.setNome("OLIVEIRA");
                    produtos.setPreco(0.38);
                    produtos.setQuantidade(49);

                    Gson gson = new Gson();
                    String json = gson.toJson(produtos);

                    w.write(json);
                    w.close();

                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    Scanner s = new Scanner(in);
                    String conteudo = s.nextLine();
                    in.close();
                } catch (Exception e) {

                    throw new RuntimeException(e);
                } finally {
                    urlConnection.disconnect();
                }


            }

        });

        envio.start();
    }

    public void delete() {

        Thread envio = new Thread(new Runnable() {

            public void run() {

                HttpURLConnection urlConnection = null;

                try {
                    String id="56cb2aff1aac2303009fab28";
                    URL url = new URL(URL + "/" + id + API_KEY);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("DELETE");

                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    urlConnection.disconnect();
                }

            }

        });

        envio.start();
    }


    public void atualizar() {


        Thread envio = new Thread(new Runnable() {

            public void run() {
                HttpURLConnection urlConnection = null;

                try {

                    String mensagem;
                    urlConnection = null;
                    URL url = new URL(URL +"/56cb2aff1aac2303009fab28" +API_KEY);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("PUT");
                    urlConnection.setRequestProperty("Content-Type", "application/json");

                    urlConnection.setRequestProperty("Accept", "application/json");

                    OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                    Writer w = new BufferedWriter(new OutputStreamWriter(out));

                    ProdutoPOJO produtos = new ProdutoPOJO();
                    produtos.setNome("OLIVEIRA");
                    produtos.setPreco(0.37);
                    produtos.setQuantidade(9);

                    Gson gson = new Gson();
                    String json = gson.toJson(produtos);

                    w.write(json);
                    w.close();


                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    Scanner s = new Scanner(in);
                    String conteudo = s.nextLine();
                    in.close();

                } catch (Exception e) {

                    throw new RuntimeException(e);
                } finally {
                    urlConnection.disconnect();
                }


            }

        });

        envio.start();
    }
}
