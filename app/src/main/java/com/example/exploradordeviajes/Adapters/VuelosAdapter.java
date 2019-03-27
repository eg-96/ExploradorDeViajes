package com.example.exploradordeviajes.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exploradordeviajes.Modelos.Vuelos;
import com.example.exploradordeviajes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VuelosAdapter extends RecyclerView.Adapter<VuelosAdapter.ViewHolder> {
    private ArrayList<Vuelos> vuelos;
    private OnVuelosLister mOnVueloListener;
    private Context context;

    public VuelosAdapter( Context context, ArrayList listaVuelos, OnVuelosLister onVuelosLister){
        this.vuelos = listaVuelos;
        this.context = context;
        this.mOnVueloListener = onVuelosLister;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_places,viewGroup,false);
        return new ViewHolder(view, mOnVueloListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.place_name.setText(vuelos.get(i).getNombre());
        viewHolder.price_place.setText("Desde: $" + vuelos.get(i).getPrecio());
        Picasso.get().load(vuelos.get(i).getImage()).into(viewHolder.vueloImage);
    }

    @Override
    public int getItemCount() {
        if(vuelos != null){
            return vuelos.size();
        }else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView place_name;
        TextView price_place;
        ImageView vueloImage;
        OnVuelosLister onVuelosLister;

        public ViewHolder(@NonNull View itemView, OnVuelosLister onVuelosLister){
            super(itemView);
            place_name = itemView.findViewById(R.id.place_name);
            price_place = itemView.findViewById(R.id.price_place);
            vueloImage = itemView.findViewById(R.id.vueloImage);
            this.onVuelosLister = onVuelosLister;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onVuelosLister.onVueloClick(getAdapterPosition());
        }
    }

    public interface OnVuelosLister {
        void onVueloClick(int position);
    }
}
