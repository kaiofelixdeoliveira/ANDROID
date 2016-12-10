package br.com.eniac.DeliveryExpress;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class PerfilClienteAdapter extends ArrayAdapter<PerfilClientePOJO> {

    private Context context;
    private List<PerfilClientePOJO> listaEntregas = null;

    public PerfilClienteAdapter(Context context, List<PerfilClientePOJO> listaEntregas) {
        super(context,0, listaEntregas);
        this.listaEntregas = listaEntregas;
        this.context = context;
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        PerfilClientePOJO cliente = listaEntregas.get(position);

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_lista, null);
        }

        TextView textViewCodigo = (TextView) view.findViewById(R.id.itemId);
        textViewCodigo.setText(String.valueOf(cliente.getId()));

        TextView textViewCidade = (TextView) view.findViewById(R.id.itemCidade);
        textViewCidade.setText(cliente.getCidade());

        TextView textViewEstado = (TextView)view.findViewById(R.id.itemEstado);
        textViewEstado.setText(String.valueOf(cliente.getEstado()));

        TextView textViewHorario = (TextView)view.findViewById(R.id.itemHorario);
        textViewHorario.setText(cliente.getHorario());

        TextView textViewStatus = (TextView)view.findViewById(R.id.itemStatus);
        textViewStatus.setText(cliente.getStatus());

        TextView textViewNumero = (TextView)view.findViewById(R.id.itemNumero);
        textViewNumero.setText(cliente.getNumero());

        TextView textViewEndereco = (TextView)view.findViewById(R.id.itemEndereco);
        textViewEndereco.setText(cliente.getEndereco());

        TextView textViewPrazoEntreg = (TextView)view.findViewById(R.id.itemPrazoEntreg);
        textViewPrazoEntreg.setText(cliente.getPrazoEntreg());

        TextView textViewPontoRef = (TextView)view.findViewById(R.id.itemPontRef);
        textViewPontoRef.setText(cliente.getPontoRef());

        TextView textViewCliente = (TextView) view.findViewById(R.id.itemCliente);
        textViewCliente.setText(cliente.getCliente());

        TextView textViewEntregador = (TextView) view.findViewById(R.id.itemEntregador);
        textViewEntregador.setText(cliente.getEntregador());



        return view;
    }



}
