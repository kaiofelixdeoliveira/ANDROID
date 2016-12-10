package br.com.eniac.DeliveryExpress;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static br.com.eniac.DeliveryExpress.TelaLogin.vusuario;


public class PerfilEntregadorDAO {

    private SQLiteDatabase db;
    private BancoDados banco;

    public PerfilEntregadorDAO(Context context) {
        banco = new BancoDados(context);
    }

    /*public Cursor BuscaEntregasCliente(String buscaTexto) {
        Cursor dados;


        String[] campos = {"_id", "cidade", "estado", "horario", "status", "numero", "endereco", "prazo_entrega", "ponto_referencia", "cliente", "entregador"};
        String condicao = "_id ='"+buscaTexto+"'" +
                "or cidade='"+buscaTexto+"'"+
                "or estado='"+buscaTexto+"'"+
                "or horario='"+buscaTexto+"'"+
                "or status='"+buscaTexto+"'"+
                "or numero='"+buscaTexto+"'"+
                "or endereco='"+buscaTexto+"'"+
                "or prazo_entrega='"+buscaTexto+"'"+
                "or ponto_referencia='"+buscaTexto+"'"
                ;

        db = banco.getReadableDatabase();
        dados = db.query("entregas", campos, condicao, null, null, null, null, null);

        if (dados != null) {
            dados.moveToFirst();
        }
        db.close();
        return dados;
    }
*/
    public Cursor CarregaEntregasCliente(String buscaTexto) {
        Cursor dados;

        String condicao="";

        String condicao2="";

        String[] campos = {"_id", "cidade", "estado", "horario", "status", "numero", "endereco", "prazo_entrega", "ponto_referencia", "cliente", "entregador"};
        if (buscaTexto!="vazio") {

            condicao = "_id ='" + buscaTexto + "'"+
                    "or cidade='" + buscaTexto + "'" +
                    "or estado='" + buscaTexto + "'" +
                    "or horario='" + buscaTexto + "'" +
                    "or status='" + buscaTexto + "'" +
                    "or numero='" + buscaTexto + "'" +
                    "or endereco='" + buscaTexto + "'" +
                    "or prazo_entrega='" + buscaTexto + "'" +
                    "or ponto_referencia='" + buscaTexto+"'" +
                    "or cliente='" + buscaTexto + "'"
            ;
            condicao2=condicao+" and status='Novo'";

        } else {
            condicao2 = "status = 'Novo'";
        }
        db = banco.getReadableDatabase();
        dados = db.query("entregas", campos, condicao2, null, null, null, null, null);

        if (dados != null) {
            dados.moveToFirst();
        }
        db.close();
        return dados;
    }

    public Cursor ConsultEntregEntregas(int id) {
        Cursor dados;


        String[] campos = {"_id", "cidade", "estado", "horario", "status", "numero", "endereco", "prazo_entrega", "ponto_referencia", "entregador", "cliente"};
        String condicao = "_id =" + id + "";

        db = banco.getReadableDatabase();
        dados = db.query("entregas", campos, condicao, null, null, null, null, null);

        if (dados != null) {
            dados.moveToFirst();
        }
        db.close();
        return dados;
    }

    public Cursor CarregaEntregasEntregador() {
        Cursor dados;


        String[] campos = {"_id", "cidade", "estado", "horario", "status", "numero", "endereco", "prazo_entrega", "ponto_referencia", "cliente", "entregador"};
        String condicao = "entregador = '" + vusuario + "'";

        db = banco.getReadableDatabase();
        dados = db.query("entregas", campos, condicao, null, null, null, null, null);

        if (dados != null) {
            dados.moveToFirst();
        } else {
            dados = null;
        }
        db.close();
        return dados;
    }

    public String ConfirmaEntrega(int id, String statusRec) {

        String msg = "";

        ContentValues valores = new ContentValues();

        valores.put("entregador", vusuario);

        if (statusRec == "Aguardando Aprovação do Cliente") {

            valores.put("status", "Aguardando Aprovação do Cliente");
        } else if (statusRec == "Em Andamento") {

            valores.put("status", "Em Andamento");
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
            msg = "Aguardando Aprovação do Cliente";
        }

        return msg;
    }


    public String AtualizaReputacaoEntregador(int numEntregConfirmadas, int numEntregRealizadas, String entregador) {
        String msg = "";


        ContentValues valores = new ContentValues();

        valores.put("numEntregasRealizadas", numEntregRealizadas);
        valores.put("numEntregasConfirmadas", numEntregConfirmadas);

        String condicao = "usuario ='" + entregador + "'";

        db = banco.getWritableDatabase();
        int resultado = 0;

        resultado = db.update("entregador", valores, condicao, null);

        db.close();

        if (resultado <= 0) {
            msg = "Erro ao alterar";
        } else {
            msg = "Entrega Confirmada!!!";
        }

        return msg;

    }

    public Cursor ConsultaReputacaoEntregador(String nomeEntregador) {
        Cursor dados;

        String[] campos = {"numEntregasRealizadas", "numEntregasConfirmadas"};
        String condicao = "usuario = '" + nomeEntregador + "'";

        db = banco.getReadableDatabase();
        dados = db.query("entregador", campos, condicao, null, null, null, null, null);

        if (dados != null) {
            dados.moveToFirst();
        } else {
            dados = null;
        }
        db.close();
        return dados;
    }
}
