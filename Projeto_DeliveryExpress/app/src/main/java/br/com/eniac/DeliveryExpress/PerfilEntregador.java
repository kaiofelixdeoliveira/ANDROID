package br.com.eniac.DeliveryExpress;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PerfilEntregador extends Activity implements View.OnClickListener {


    private Button btVer,btPesq,btSair,btnAlter,btnEncer,btCadVei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfilentregador);

        btVer = (Button) findViewById(R.id.btnVerEntreg);
        btPesq = (Button) findViewById(R.id.btnPesqEntreg);
        btSair = (Button) findViewById(R.id.btnEntregSair);
        btnAlter = (Button) findViewById(R.id.btnAlterarCad);
        btnEncer = (Button) findViewById(R.id.btnEncerCont);
        btCadVei = (Button) findViewById(R.id.btnCadVei);

        btVer.setOnClickListener(this);
        btPesq.setOnClickListener(this);
        btSair.setOnClickListener(this);
        btnAlter.setOnClickListener(this);
        btnEncer.setOnClickListener(this);
        btCadVei.setOnClickListener(this);

    }

    public void onClick(View v) {

        // TODO Auto-generated method stub
        if (v.getId() == R.id.btnPesqEntreg) {
            Intent pesqEntreg = new Intent(PerfilEntregador.this, PerfilEntregadorLista.class);
            startActivity(pesqEntreg);
            finish();
        }

        if (v.getId() == R.id.btnEntregSair) {
            logout();
            Intent Sair = new Intent(PerfilEntregador.this, TelaLogin.class);
            startActivity(Sair);
            finish();

        }

        if (v.getId() == R.id.btnVerEntreg) {
            Intent PerfilLista = new Intent(this, EntregasConfirmadas.class);
            startActivity(PerfilLista);
            finish();

        }
        if (v.getId() == R.id.btnAlterarCad) {
            Intent AlteraCad = new Intent(this, AlterarCadastroUsuarioEntregador.class);
            startActivity(AlteraCad);
            finish();

        }

        if (v.getId() == R.id.btnEncerCont) {
            excluirUsuario();
            Intent EncerCad = new Intent(this, TelaLogin.class);
            startActivity(EncerCad);
            finish();

        }

        if (v.getId() == R.id.btnCadVei) {
            Intent EncerCad = new Intent(this, Veiculo.class);
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
}
