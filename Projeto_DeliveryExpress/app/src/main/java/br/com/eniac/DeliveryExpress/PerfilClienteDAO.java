package br.com.eniac.DeliveryExpress;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import static android.content.Intent.getIntent;
import static br.com.eniac.DeliveryExpress.TelaLogin.vusuario;

/**
 * Created by ponto frio on 09/11/2015.
 */
public class PerfilClienteDAO {

    private SQLiteDatabase db;
    private BancoDados banco;


    public PerfilClienteDAO(Context context) {
        banco = new BancoDados(context);
    }


    public String SolicPeddEntreg(String vendereco, String vcidade,
                                  String vnumero, String vestado,
                                  String vpontoRef, String vhorario,
                                  String vprazoEntreg) {
        ContentValues valores;
        Long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("cliente", vusuario);
        valores.put("endereco", vendereco);
        valores.put("cidade", vcidade);
        valores.put("numero", vnumero);
        valores.put("estado", vestado);
        valores.put("ponto_referencia", vpontoRef);
        valores.put("horario", vhorario);
        valores.put("status", "Novo");
        valores.put("prazo_entrega", vprazoEntreg);

        resultado = db.insert("entregas", null, valores);

        db.close();

        if (resultado == -1) {
            return "Erro ao cadastar";
        } else {
            return "Cadastre uma Mercadoria";
        }
    }

    public Cursor CarregaEntregas() {
        Cursor dados;


        String[] campos = {"_id", "cidade", "estado", "horario", "status", "numero", "endereco", "prazo_entrega", "ponto_referencia", "cliente", "entregador"};
        String condicao = "cliente = '" + vusuario + "'";

        db = banco.getReadableDatabase();
        dados = db.query("entregas", campos, condicao, null, null, null, null, null);

        if (dados != null) {
            dados.moveToFirst();
        }
        db.close();
        return dados;
    }

    public String AltEstEntreg(int id, String statusRec) {

        String msg = "";
        ContentValues valores = new ContentValues();

        if (statusRec == "Entregador Aceito") {
            valores.put("status", "Entregador Aceito");
        } else if (statusRec == "Entregue") {
            valores.put("status", "Entregue");
        }
        String condicao = "_id =" + id + "";

        db = banco.getWritableDatabase();
        int resultado = 0;

        resultado = db.update("entregas", valores, condicao, null);

        db.close();

        if (resultado <= 0) {
            msg = "Erro ao alterar";
        } else {
            msg = "Entregador Aceito";
        }

        return msg;
    }


    public Cursor VisuEstEntreg(int id) {
        Cursor dados;


        String[] campos = {"_id", "cidade", "estado", "horario", "status", "numero", "endereco", "prazo_entrega", "ponto_referencia", "cliente", "entregador"};
        String condicao = "_id =" + id + "";

        db = banco.getReadableDatabase();
        dados = db.query("entregas", campos, condicao, null, null, null, null, null);

        if (dados != null) {
            dados.moveToFirst();
        }
        db.close();
        return dados;
    }


    public String AtualizaReputacaoCliente(int num_pedidos_realizados, int num_pedidos_pagos) {
        String msg = "";

        ContentValues valores = new ContentValues();

        valores.put("num_pedidos_realizados", num_pedidos_realizados);
        valores.put("num_pedidos_pagos", num_pedidos_pagos);

        String condicao = "usuario ='" + vusuario + "'";

        db = banco.getWritableDatabase();
        int resultado = 0;

        resultado = db.update("cliente", valores, condicao, null);

        db.close();

        if (resultado <= 0) {
            msg = "Erro ao alterar";
        } else {
            msg = "Entrega Confirmada!!!";
        }

        return msg;

    }

    public Cursor ConsultaReputacaoCliente(String nomeCliente) {
        Cursor dados;

        String[] campos = {"num_pedidos_realizados", "num_pedidos_pagos"};
        String condicao = "usuario = '" + nomeCliente + "'";

        db = banco.getReadableDatabase();
        dados = db.query("cliente", campos, condicao, null, null, null, null, null);

        if (dados != null) {
            dados.moveToFirst();
        } else {
            dados = null;
        }
        db.close();
        return dados;
    }
}