package br.com.eniac.DeliveryExpress;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import static br.com.eniac.DeliveryExpress.TelaLogin.vusuario;

public class VeiculoDAO {

    private SQLiteDatabase db;
    private BancoDados banco;

    public VeiculoDAO(Context context) {
        banco = new BancoDados(context);
    }


    public String IncluirVeiculo(String vcor,String vpeso, String vmodelo,
                                 String vplaca, String vtipo) {

        ContentValues valores;
        Long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put("cor", vcor);
        valores.put("peso_total", vpeso);
        valores.put("modelo", vmodelo);
        valores.put("num_placa", vplaca);
        valores.put("tipo", vtipo);
        valores.put("entregador", vusuario);


            resultado = db.insert("veiculo", null, valores);

        db.close();

        if (resultado == -1) {
            return "Deu Ruim";
        } else {
            return "Deu Certo";
        }

    }

    public String ExcluirVeiculo(){
        return "";

    }

    public String AlterarVeiculo(){
        return "";

    }

    public String ConsultarVeiculo(){

        return "";
    }



}
