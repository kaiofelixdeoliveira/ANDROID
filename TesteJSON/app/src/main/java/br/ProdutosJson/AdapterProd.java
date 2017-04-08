package br.ProdutosJson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ponto frio on 19/02/2016.
 */
public class AdapterProd extends ArrayAdapter<ProdutoPOJO> {


    private Context context;
    private List<ProdutoPOJO> listaEntregas = null;

    public AdapterProd(Context context, List<ProdutoPOJO> listaEntregas) {
        super(context,0, listaEntregas);
        this.listaEntregas = listaEntregas;
        this.context = context;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ProdutoPOJO cliente = listaEntregas.get(position);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_lista, null);
        }

        TextView textViewCodigo = (TextView) view.findViewById(R.id.itemId);
        textViewCodigo.setText(cliente.get_id());

        TextView textViewCidade = (TextView) view.findViewById(R.id.itemNome);
        textViewCidade.setText(cliente.getNome());

        TextView textViewEstado = (TextView) view.findViewById(R.id.itemPreco);
        textViewEstado.setText(String.valueOf(cliente.getPreco()));

        TextView textViewHorario = (TextView) view.findViewById(R.id.itemQuantidade);
        textViewHorario.setText(String.valueOf(cliente.getQuantidade()));


        return view;
    }
}
