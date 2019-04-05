package com.example.exploradordeviajes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exploradordeviajes.Modelos.Ticket;
import com.example.exploradordeviajes.Modelos.Vuelos;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class VisualizarViajes extends AppCompatActivity implements Serializable {
    private Vuelos vuelo;

    private Ticket ticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_viajes);
        Bundle data = getIntent().getExtras();
        Vuelos vuelo = (Vuelos) data.getParcelable("vuelo");
        Toast.makeText(VisualizarViajes.this,vuelo.getNombre(),Toast.LENGTH_SHORT).show();

        ImageView imageCtr = findViewById(R.id.imagen);
        Picasso.get().load(vuelo.getImage()).into(imageCtr);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("vuelo",0);

        String salidaDe = pref.getString("salidade","");
        String fechaDe = pref.getString("fechasalida","");

        TextView salida = findViewById(R.id.txtSalida);
        salida.setText(salidaDe);

        TextView llegada = findViewById(R.id.txtLugarLlegada);
        llegada.setText(vuelo.getNombre());

        SharedPreferences pref2 = getApplicationContext().getSharedPreferences("UserAuth",0);
        String userEmail = pref2.getString("email","");

        TextView email = findViewById(R.id.txtEmail);
        email.setText(userEmail);

        TextView fecha = findViewById(R.id.txtFechaSalida);
        fecha.setText(fechaDe);

        ticket = new Ticket(salidaDe,vuelo.getNombre(),vuelo.getPrecio(),userEmail,1,fechaDe,"Económico",vuelo.getImage());
        saveTicket(ticket);
    }

    private void saveTicket(Ticket ticket){
        final Ticket tickett = ticket;
        Button btn = (Button) findViewById(R.id.btnCompra);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseFirestore.getInstance().collection("Compra").add(tickett).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(VisualizarViajes.this,"Compra realizada",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(VisualizarViajes.this, Compra.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }

}
