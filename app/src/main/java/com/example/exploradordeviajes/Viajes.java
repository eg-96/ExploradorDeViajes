package com.example.exploradordeviajes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.exploradordeviajes.Adapters.VuelosAdapter;
import com.example.exploradordeviajes.Modelos.Vuelos;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Viajes extends AppCompatActivity implements VuelosAdapter.OnVuelosLister {
    private ArrayList<Vuelos> vuelos;
    private VuelosAdapter mVuelosAdapter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vuelos = new ArrayList<>();
        setContentView(R.layout.activity_viajes);

        getViajes();
//        initRecyclerView();
    }

    private void getViajes(){
        SharedPreferences prefs = getSharedPreferences("vuelo", Context.MODE_PRIVATE);
        String salida = prefs.getString("salidade","");

        FirebaseFirestore.getInstance().collection("Vuelos").whereEqualTo("salida",salida).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (DocumentSnapshot snapshot: queryDocumentSnapshots){
                    vuelos.add(snapshot.toObject(Vuelos.class));
                }
                for (int i = 0; i < vuelos.size() ; i++) {
                    System.out.println("===================================");

                    System.out.println(vuelos.get(i).getNombre());
                    System.out.println(vuelos.get(i).getSalida());
                    System.out.println(vuelos.get(i).getPrecio());
                    System.out.println(vuelos.get(i).getImage());
                    System.out.println("===================================");
                }
                initRecyclerView();
            }
        });
    }

    private void initRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerViajes);
        mVuelosAdapter = new VuelosAdapter(this,vuelos,this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));
        mRecyclerView.setAdapter(mVuelosAdapter);
    }

    @Override
    public void onVueloClick(int position) {
        vuelos.get(position);
        Intent intent = new Intent(this,  VisualizarViajes.class);
//        intent.putExtra("card_place", vuelos.get(position));
        startActivity(intent);

    }

}
