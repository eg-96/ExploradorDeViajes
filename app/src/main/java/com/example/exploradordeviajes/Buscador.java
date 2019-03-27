package com.example.exploradordeviajes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Buscador extends AppCompatActivity {
    private FusedLocationProviderClient client;
    private Location gps, network_loc, final_loc;
    private double lat;
    private double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);
        requestPermission();
//        client = LocationServices.getFusedLocationProviderClient(this);

        TextView button = findViewById(R.id.btnUbicacionActual);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                requestPermission();
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(Buscador.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(Buscador.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(Buscador.this,Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(Buscador.this,"No permission granted",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Buscador.this,"permission granted",Toast.LENGTH_LONG).show();

                }

                try{
                    gps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                }catch (Exception e){
                    e.printStackTrace();
                }
                if(gps != null){
                    final_loc = gps;
                    lat = final_loc.getLatitude();
                    longitude = final_loc.getLongitude();
                }else if (network_loc != null){
                    final_loc = network_loc;
                    lat = final_loc.getLatitude();
                    longitude = final_loc.getLongitude();
                }else{
                    lat = 0.0;
                    longitude = 0.0;
                }
                getActualLocaltion(lat,longitude);



//
            }
        });
        privateVisualizarViajes();
    }

    private void privateVisualizarViajes(){
        Button btn  = findViewById(R.id.search_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viajes = new Intent(Buscador.this, Viajes.class);
                startActivity(viajes);
            }
        });
    }


    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
    }

    public void getActualLocaltion(double lat, double lon) {
        String cityName ="";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addreses;
        try{
            addreses = geocoder.getFromLocation(lat,lon, 10);
            if (addreses != null && addreses.size() > 0){
                String address = addreses.get(0).getAddressLine(0);
                String city = addreses.get(0).getLocality();
                String state = addreses.get(0).getAdminArea();
                String country = addreses.get(0).getCountryName();
                String knownName = addreses.get(0).getFeatureName();

                System.out.println(address);
                System.out.println(city);
                System.out.println(state);
                System.out.println(country);
                System.out.println(knownName);

                SharedPreferences pref = getApplicationContext().getSharedPreferences("vuelo",0);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("salidade",state);
                editor.apply();

                EditText text = (EditText) findViewById(R.id.txtSalida);
                text.setText(state);
            }

        } catch (IOException e){
            e.printStackTrace();
        }


    }
}
