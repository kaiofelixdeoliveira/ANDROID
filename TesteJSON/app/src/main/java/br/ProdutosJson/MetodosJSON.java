

package br.ProdutosJson;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MetodosJSON {

    public int resultado;
    private static final String URL = "https://api.mlab.com/api/1/databases/aplicativo_android/collections/cadastro";
    private static final String API_KEY = "?apiKey=3_-Ooah_DIe6SgsbTR8hrci8ivNt3_2g";

    public List<ProdutoPOJO> getAll() {


        List<ProdutoPOJO> produtos2 = new ArrayList();
        List<ProdutoPOJO> produtos = new ArrayList();
        HttpURLConnection urlConnection = null;


        try {
            java.net.URL url = new URL(URL + API_KEY);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            /*Gson gson = new Gson();
             produtos= gson.fromJson(conteudo, new TypeToken<List<ProdutoPOJO>>(){}.getType());
             */


            JSONArray pessoasJson = new JSONArray(conteudo);
            JSONObject pessoa;

            for (int i = 0; i < pessoasJson.length(); i++) {
                pessoa = new JSONObject(pessoasJson.getString(i));


                ProdutoPOJO objetoPessoa = new ProdutoPOJO();

               // if (pessoa.getString("_id").equals("56cb2aff1aac2303009fab28")) {
                    Log.i("PESSOA ENCONTRADA: ",
                            "ID=" + pessoa.getString("_id"));
                    objetoPessoa.set_id(pessoa.getString("_id"));
                    objetoPessoa.setNome(pessoa.getString("nome"));
                    objetoPessoa.setPreco(pessoa.getDouble("preco"));
                    objetoPessoa.setQuantidade(pessoa.getInt("quantidade"));
                    produtos.add(objetoPessoa);
                //}
            }


        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            urlConnection.disconnect();
        }
        return produtos;
    }


}


