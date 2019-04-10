package com.example.exploradordeviajes.Modelos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

public class Vuelos implements Parcelable {
    private String nombre;
    private String salida;
    private Integer precio;
    private String image;


    public Vuelos(String nombre, String salida, Integer precio,String image) {
        this.nombre = nombre;
        this.salida = salida;
        this.precio = precio;
        this.image = image;
    }

    public Vuelos(){

    }

    protected Vuelos(Parcel in) {
        nombre = in.readString();
        salida = in.readString();
        if (in.readByte() == 0) {
            precio = null;
        } else {
            precio = in.readInt();
        }
        image = in.readString();
    }

    public static final Creator<Vuelos> CREATOR = new Creator<Vuelos>() {
        @Override
        public Vuelos createFromParcel(Parcel in) {
            return new Vuelos(in);
        }

        @Override
        public Vuelos[] newArray(int size) {
            return new Vuelos[size];
        }
    };

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

    public Integer getPrecio() {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(salida);
        if (precio == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(precio);
        }
        parcel.writeString(image);

    }
}

