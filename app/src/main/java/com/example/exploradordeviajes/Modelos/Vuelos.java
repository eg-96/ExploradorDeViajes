package com.example.exploradordeviajes.Modelos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Vuelos {
    private String nombre;
    private String salida;
    private Integer precio;
    private String image;
    private Bitmap bitmapImage;

    public Vuelos(String nombre, String salida, Integer precio,String image) {
        this.nombre = nombre;
        this.salida = salida;
        this.precio = precio;
        this.image = image;
    }

    public Vuelos(){

    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public Number getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bitmap getBitmapImage() {
        return this.bitmapImage;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte[] decodedString = Base64.decode(encodedString, Base64.URL_SAFE );
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodedByte;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

}

