package br.com.eniac.DeliveryExpress;

import android.database.sqlite.SQLiteDatabase;


        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;


public class BancoDados extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final int VERSAO = 15;


    public BancoDados(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE cliente ("
                + "id integer primary key autoincrement,"
                + "estLogin integer,"
                + "nomecompleto text,"
                + "sexo text,"
                + "idade integer,"
                + "endereco text,"
                + "usuario text,"
                + "senha text,"
                + "cpf text,"
                + "cnpj text,"
                + "tipo text,"
                + "cidade text,"
                + "estado text,"
                + "reputacao text,"
                + "num_pedidos_realizados integer,"
                + "num_pedidos_pagos integer)";

        String sql2 = "CREATE TABLE entregador ("
                + "id integer primary key autoincrement,"
                + "estLogin integer,"
                + "nomecompleto text,"
                + "sexo text,"
                + "idade integer,"
                + "endereco text,"
                + "usuario text,"
                + "senha text,"
                + "cpf text,"
                + "cnpj text,"
                + "tipo text,"
                + "cidade text,"
                + "estado text,"
                + "reputacao text,"
                + "numEntregasRealizadas integer,"
                + "numEntregasConfirmadas integer)";

        String sql3 = "CREATE TABLE pedidos_entrega ("
                + "cod_pedido integer primary key autoincrement,"
                + "num_total_entregas integer)";

        String sql4 = "CREATE TABLE veiculo ("
                + "cod_veiculo integer primary key autoincrement,"
                + "cor text,"
                + "peso_total text,"
                + "modelo text,"
                + "num_placa text,"
                + "tipo text,"
                + "entregador)";

        String sql5 = "CREATE TABLE entregas ("
                + "_id integer primary key autoincrement,"
                +" cliente text,"
                +" entregador text,"
                + "cidade text,"
                + "estado text,"
                + "horario text,"
                + "status text,"
                + "numero text,"
                + "endereco text,"
                + "prazo_entrega text,"
                + "ponto_referencia text)";

        String sql6 = "CREATE TABLE mercadoria ("
                + "codigo_mercadoria integer primary key autoincrement,"
                + "cliente text,"
                + "volume text,"
                + "tipo text,"
                + "periculosidade text,"
                + "peso text)";



        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS entregador");
        db.execSQL("DROP TABLE IF EXISTS cliente");
        db.execSQL("DROP TABLE IF EXISTS veiculo");
        db.execSQL("DROP TABLE IF EXISTS pedidos_entrega");
        db.execSQL("DROP TABLE IF EXISTS entregas");
        db.execSQL("DROP TABLE IF EXISTS mercadoria");

        onCreate(db);
    }
}
