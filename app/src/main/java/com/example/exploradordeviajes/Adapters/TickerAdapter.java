package com.example.exploradordeviajes.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exploradordeviajes.Modelos.Ticket;
import com.example.exploradordeviajes.R;

import java.util.ArrayList;

public class TickerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Ticket> tickets;
    private LayoutInflater layoutInflater;

    public TickerAdapter(Context context, ArrayList<Ticket> tickets) {
        this.context = context;
        this.tickets = tickets;
    }

    @Override
    public int getCount() {
        return tickets.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = layoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_compra, container, false);

        TextView nombrePais = view.findViewById(R.id.nombrePais);
        TextView txtCorreo = view.findViewById(R.id.txtCorreo);
        TextView txtFecha = view.findViewById(R.id.txtFecha);
        TextView txtPrecio = view.findViewById(R.id.txtPrecio);
        TextView txtBoleto = view.findViewById(R.id.txtBoleto);
        TextView txtSalida = view.findViewById(R.id.txtSalida);

        nombrePais.setText(tickets.get(position).getLlegada());
        txtCorreo.setText(tickets.get(position).getEmail());
        txtFecha.setText(tickets.get(position).getFechaViaje());
        txtPrecio.setText(tickets.get(position).getPrecio().toString());
        txtBoleto.setText(tickets.get(position).getTipoBoleto());
        txtSalida.setText(tickets.get(position).getSalida());

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
