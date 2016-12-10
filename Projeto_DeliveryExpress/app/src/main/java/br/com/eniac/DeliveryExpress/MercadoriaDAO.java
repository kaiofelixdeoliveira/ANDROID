package br.com.eniac.DeliveryExpress;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static br.com.eniac.DeliveryExpress.TelaLogin.vusuario;

/**
 * Created by ponto frio on 18/11/2015.
 */
public class MercadoriaDAO {

    private SQLiteDatabase db;
    private BancoDados banco;



    public MercadoriaDAO(Context context) {
        banco = new BancoDados(context);
    }

    public String CadMerc(String vpericulo, String vpeso,String vtipo,String vvolume){
        ContentValues valores;
        Long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("cliente", vusuario);
        valores.put("periculosidade", vpericulo);
        valores.put("peso", vpeso);
        valores.put("volume", vvolume);
        valores.put("tipo", vtipo);

        resultado = db.insert("mercadoria", null, valores);

        db.close();

        if (resultado == -1) {
            return "Erro Ao Cadastrar Mercadoria";
        } else {
            return "Mercadoria Cadastrada";
        }
    }

    public Cursor ConsultMerc(int codigoMerc) {
        Cursor dados;


        String[] campos = {"codigo_mercadoria", "volume", "tipo", "periculosidade", "peso"};
        String condicao = "codigo_mercadoria ="+codigoMerc+"";

        db = banco.getReadableDatabase();
        dados = db.query("mercadoria", campos, condicao, null, null, null, null, null);

        if (dados != null) {
            dados.moveToFirst();
        }
        db.close();
        return dados;
    }


}
