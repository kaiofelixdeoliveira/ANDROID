package br.com.eniac.DeliveryExpress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


    public class PerfilClienteAdapterMercadoria extends ArrayAdapter<MercadoriaPOJO> {

        private Context context;
        private List<MercadoriaPOJO> listaMercadoria = null;

        public PerfilClienteAdapterMercadoria(Context context, List<MercadoriaPOJO> listaMercadoria) {
            super(context,0, listaMercadoria);
            this.listaMercadoria = listaMercadoria;
            this.context = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            MercadoriaPOJO mercadoria = listaMercadoria.get(position);

            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.item_lista2, null);
            }

            //Itens da Mercadoria
            TextView textViewCodigo = (TextView) view.findViewById(R.id.itemCodigo);
            textViewCodigo.setText(String.valueOf(mercadoria.getCodigo()));

            TextView textViewVolume = (TextView) view.findViewById(R.id.itemVolume);
            textViewVolume.setText(mercadoria.getVolume());

            TextView textViewTipo = (TextView)view.findViewById(R.id.itemTipo);
            textViewTipo.setText(String.valueOf(mercadoria.getTipo()));

            TextView textViewPericulosidade = (TextView)view.findViewById(R.id.itemPericulo);
            textViewPericulosidade.setText(mercadoria.getPericulosidade());

            TextView textViewPeso = (TextView)view.findViewById(R.id.itemPeso);
            textViewPeso.setText(mercadoria.getPeso());




            return view;
        }



    }

