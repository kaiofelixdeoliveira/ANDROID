package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TelaClienteListaID extends Activity implements View.OnClickListener {

    private String auxiliarEntregador = "";
    private int idEntrega;
    private TextView txtVolume, txtPeso, txtCodigo, txtPericulo, txtTipo,
            txtCodigoEntreg, txtCliente, txtEntregador, txtCidade, txtEstado,
            txtHorario, txtStatus, txtNumero, txtEndereco, txtPrazo, txtPontoRef,
            txtReputacao, txtEntregConfirmadas, txtEntregRealizadas;

    private String mensagem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_lista2);

        //LISTA DE CLIENTES
        txtReputacao = (TextView) findViewById(R.id.itemReputacao);
        txtEntregRealizadas = (TextView) findViewById(R.id.itemEntregRealializadas);
        txtEntregConfirmadas = (TextView) findViewById(R.id.itemEntregConfirmadas);
        txtCodigoEntreg = (TextView) findViewById(R.id.itemId);
        txtCliente = (TextView) findViewById(R.id.itemCliente);
        txtEntregador = (TextView) findViewById(R.id.itemEntregador);
        txtEndereco = (TextView) findViewById(R.id.itemEndereco);
        txtCidade = (TextView) findViewById(R.id.itemCidade);
        txtEstado = (TextView) findViewById(R.id.itemEstado);
        txtHorario = (TextView) findViewById(R.id.itemHorario);
        txtStatus = (TextView) findViewById(R.id.itemStatus);
        txtNumero = (TextView) findViewById(R.id.itemNumero);
        txtPrazo = (TextView) findViewById(R.id.itemPrazoEntreg);
        txtPontoRef = (TextView) findViewById(R.id.itemPontRef);


        //LISTA DE MERCADORIA


        txtPericulo = (TextView) findViewById(R.id.itemPericulo);
        txtPeso = (TextView) findViewById(R.id.itemPeso);
        txtVolume = (TextView) findViewById(R.id.itemVolume);
        txtCodigo = (TextView) findViewById(R.id.itemCodigo);
        txtTipo = (TextView) findViewById(R.id.itemTipo);


        Button Enviar = (Button) findViewById(R.id.btnClienteEnviar);
        Button Voltar = (Button) findViewById(R.id.btnClienteVoltar);
        Button Entregue = (Button) findViewById(R.id.btnEntregue);


        Enviar.setOnClickListener(this);
        Voltar.setOnClickListener(this);
        Entregue.setOnClickListener(this);


        CarregarListaMercadoria();
        CarregarListaClientes();
        carregaReputacaoEntregador();


    }


    public void onClick(View v) {

        if (v.getId() == R.id.btnClienteVoltar) {
            Intent sair = new Intent(TelaClienteListaID.this, PerfilClienteLista.class);
            startActivity(sair);
            finish();
        }

        if (v.getId() == R.id.btnClienteEnviar) {
            if(txtStatus.getText().toString().equals("Aguardando Aprovação do Cliente")) {
                Bundle bundle = getIntent().getExtras();
                String statusEnv = "Entregador Aceito";
                idEntrega = Integer.parseInt(bundle.getString("ID"));

                PerfilClienteDAO bd = new PerfilClienteDAO(getBaseContext());
                mensagem = bd.AltEstEntreg(idEntrega, statusEnv);
                Intent enviar = new Intent(TelaClienteListaID.this, PerfilClienteLista.class);
                Toast.makeText(TelaClienteListaID.this, mensagem, Toast.LENGTH_LONG).show();
                startActivity(enviar);
                gerarNotificacao();
                finish();
            }else{
                Toast.makeText(TelaClienteListaID.this, "NÃO HÁ ENTREGADOR PARA SER ACEITO", Toast.LENGTH_LONG).show();
            }

        }

        if (v.getId() == R.id.btnEntregue) {
            if(txtStatus.getText().toString().equals("Em Andamento")) {
                Bundle bundle = getIntent().getExtras();
                String statusEnv = "Entregue";
                idEntrega = Integer.parseInt(bundle.getString("ID"));

                PerfilClienteDAO bd = new PerfilClienteDAO(getBaseContext());
                mensagem = bd.AltEstEntreg(idEntrega, statusEnv);
                Intent enviar = new Intent(TelaClienteListaID.this, PerfilClienteLista.class);
                Toast.makeText(TelaClienteListaID.this, mensagem, Toast.LENGTH_LONG).show();
                startActivity(enviar);
                gerarNotificacao();
                finish();
            }else{

                Toast.makeText(TelaClienteListaID.this, "A ENTREGA TEM DE ESTAR EM ANDAMENTO", Toast.LENGTH_LONG).show();
            }

        }
    }


    public void gerarNotificacao() {


        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pEntregador = PendingIntent.getActivity(this, 0, new Intent(getBaseContext(), PerfilEntregadorLista.class), 0);

//(this, activity.class)
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker("" + mensagem + "");
        builder.setContentTitle("STATUS");
        //builder.setContentText("Descrição");
        builder.setSmallIcon(R.drawable.icon);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
        builder.setContentIntent(pEntregador);


        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        String[] descs = new String[]{"O STATUS ESTÁ COMO" + mensagem + "","ACOMPANHE AS SUAS ENTREGAS NO ESPAÇO DE #VERIFICAR ENTREGAS#"};
        for (int i = 0; i < descs.length; i++) {
            style.addLine(descs[i]);
        }
        builder.setStyle(style);

        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.icon, n);

        try {
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(this, som);
            toque.play();
        } catch (Exception e) {
        }
    }

    //CARREGA OS CLIENTES
    public void CarregarListaClientes() {

        Bundle bundle = getIntent().getExtras();

        idEntrega = Integer.parseInt(bundle.getString("ID"));

        try {
            PerfilClienteDAO bd = new PerfilClienteDAO(getBaseContext());


            String msg;

            Cursor retorno = bd.VisuEstEntreg(idEntrega);

            if (retorno.moveToFirst()) {


                txtCodigoEntreg.setText(String.valueOf(retorno.getInt(0)));
                txtCidade.setText(retorno.getString(1));
                txtEstado.setText(retorno.getString(2));
                txtHorario.setText(retorno.getString(3));
                txtStatus.setText(retorno.getString(4));
                txtNumero.setText(retorno.getString(5));
                txtEndereco.setText(retorno.getString(6));
                txtPrazo.setText(retorno.getString(7));
                txtPontoRef.setText(retorno.getString(8));
                txtCliente.setText(retorno.getString(9));
                txtEntregador.setText(retorno.getString(10));
                auxiliarEntregador = retorno.getString(10);


            } else {

                Toast.makeText(this, "Erro ao consultar dados",
                        Toast.LENGTH_LONG).show();
            }
            retorno.close();


        } catch (Exception e) {
            Log.e("BANCO", "Listar=" + e.getMessage());
            mensagem("Erro ao consultar");
        }

    }

    //CARREGA MERCADORIA
    public void CarregarListaMercadoria() {

        Bundle bundle = getIntent().getExtras();

        idEntrega = Integer.parseInt(bundle.getString("ID"));

        try {
            MercadoriaDAO bd = new MercadoriaDAO(getBaseContext());


            String msg;

            Cursor retorno = bd.ConsultMerc(idEntrega);

            if (retorno.moveToFirst()) {

                txtCodigo.setText(String.valueOf(retorno.getInt(0)));
                txtVolume.setText(retorno.getString(1));
                txtTipo.setText(retorno.getString(2));
                txtPericulo.setText(retorno.getString(3));
                txtPeso.setText(retorno.getString(4));
            } else {

                Toast.makeText(this, "Erro ao consultar dados",
                        Toast.LENGTH_LONG).show();

            }
            retorno.close();

        } catch (Exception e) {
            Log.e("BANCO", "Listar=" + e.getMessage());
            mensagem("Erro ao consultar");
        }

    }

    /**************************
     * CARREGA REPUTAÇÃO DO ENTREGADOR
     ************************/
    public void carregaReputacaoEntregador() {

        int entregasConfirmadas = 0;
        int entregasRealizadas = 0;
        String reputacao = "";

        if (!txtEntregador.getText().toString().equals(null)) {
            Log.d("##Nome do Entregador##", txtEntregador.getText().toString());

            try {
                PerfilEntregadorDAO bd = new PerfilEntregadorDAO(getBaseContext());


                String msg;

                Cursor retorno = bd.ConsultaReputacaoEntregador(txtEntregador.getText().toString());

                if (retorno.moveToFirst()) {

                    entregasRealizadas = retorno.getInt(0);
                    entregasConfirmadas = retorno.getInt(1);

                } else {

                    Toast.makeText(this, "Não há entregas realizadas para consultar",
                            Toast.LENGTH_LONG).show();

                }
                retorno.close();

                /*

                if ((entregasRealizadas == 0 && entregasConfirmadas == 0)
                        || (entregasRealizadas == 0 && entregasConfirmadas != 0)
                        || (entregasRealizadas != 0 && entregasConfirmadas == 0)) {

                    reputacao = "Não há entregas Realizadas";

                } else if (entregasConfirmadas == entregasRealizadas) {

                    reputacao = "Ótimo Entregador";
                } else {

                    if ((entregasRealizadas / entregasConfirmadas) < 0.6) {

                        reputacao = "Entregador Ruim";

                    } else {

                        reputacao = "Bom Entregador";

                    }

                }

                txtReputacao.setText(reputacao);
                */
                txtEntregConfirmadas.setText(String.valueOf(entregasConfirmadas));
                txtEntregRealizadas.setText(String.valueOf(entregasRealizadas));

            } catch (Exception e) {
                Log.e("BANCO", "Listar=" + e.getMessage());
                mensagem("Erro ao consultar");
            }
        } else {
            Toast.makeText(this, "não há entregador cadastrado para carregar entregas confirmadas", Toast.LENGTH_LONG).show();

        }

    }

    public void mensagem(String msg) {
        Context contexto = getApplicationContext();
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(contexto, "Voce clicou aqui", duracao);
        toast.show();
    }


    protected void onResume() {
        super.onResume();

        CarregarListaMercadoria();
        CarregarListaClientes();

    }
}
