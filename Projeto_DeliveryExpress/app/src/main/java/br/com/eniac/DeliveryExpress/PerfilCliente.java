package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import static br.com.eniac.DeliveryExpress.TelaLogin.vusuario;
import static br.com.eniac.DeliveryExpress.TelaLogin.vtipo;

public class PerfilCliente extends Activity implements View.OnClickListener {


    public Button btnCad, btnSair, btnCarregar,btnAlter,btnEncer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfilcliente);


        btnCad = (Button) findViewById(R.id.btnClientCad);
        btnSair = (Button) findViewById(R.id.btnClienteSair);
       btnCarregar = (Button) findViewById(R.id.carregar);
        btnAlter = (Button) findViewById(R.id.btnAlterarCad);
        btnEncer = (Button) findViewById(R.id.btnEncerCont);

        btnCad.setOnClickListener(this);
        btnSair.setOnClickListener(this);
        btnCarregar.setOnClickListener(this);
        btnAlter.setOnClickListener(this);
        btnEncer.setOnClickListener(this);


    }



    public void onClick(View v) {

        // TODO Auto-generated method stub
        if (v.getId() == R.id.btnClientCad) {
            Intent CadEntrega = new Intent(PerfilCliente.this, Entrega.class);
            startActivity(CadEntrega);
            finish();
        }

        if (v.getId() == R.id.btnClienteSair) {
            logout();
            Intent sair = new Intent(PerfilCliente.this, TelaLogin.class);
            startActivity(sair);
            finish();


        }

        if (v.getId() == R.id.carregar) {
            Intent PerfilLista = new Intent(this, PerfilClienteLista.class);
            startActivity(PerfilLista);
            finish();

        }

        if (v.getId() == R.id.btnAlterarCad) {
            Intent AlteraCad = new Intent(this, AlteraCadastroUsuario.class);
            startActivity(AlteraCad);
            finish();

        }

        if (v.getId() == R.id.btnEncerCont) {
            excluirUsuario();
            Intent EncerCad = new Intent(this, TelaLogin.class);
            startActivity(EncerCad);
            finish();

        }
    }

    public void excluirUsuario() {


        CadastroDAO bd = new CadastroDAO(getBaseContext());

        String msg;
        msg = bd.ExcluirUsuario();

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();


    }

    public void logout(){


        CadastroDAO db=new CadastroDAO(getBaseContext());
        String msg =db.Logout();

        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();


    }

/*



        private void CarregarLista() {

        PerfilClienteDAO crud = new PerfilClienteDAO(getBaseContext());

        Cursor cursor = crud.CarregaEntregas();

        String[] nomeCampos = new String[]{"_id", "endereco"};
        int[] idViews = new int[]{R.id.idLivro, R.id.nomeLivro};

        cursor.moveToFirst();

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),R.layout.layout_perfilcliente, cursor, nomeCampos, idViews);
            lista = (ListView) findViewById(R.id.ListEntreg);
        lista.setAdapter(adaptador);

    }
*/


}