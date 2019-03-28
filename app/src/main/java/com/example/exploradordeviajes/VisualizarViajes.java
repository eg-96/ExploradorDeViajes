package com.example.exploradordeviajes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.exploradordeviajes.Modelos.Vuelos;

import java.io.Serializable;
import java.util.ArrayList;

public class VisualizarViajes extends AppCompatActivity implements Serializable {
    private Vuelos vuelo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_viajes);
        Bundle data = getIntent().getExtras();
        Vuelos vuelo = (Vuelos) data.getParcelable("vuelo");
        Toast.makeText(VisualizarViajes.this,vuelo.getNombre(),Toast.LENGTH_SHORT).show();
    }

}
