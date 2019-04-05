package com.example.exploradordeviajes.Modelos;

public class Ticket {

    private String salida;
    private String llegada;
    private Integer precio;
    private String email;
    private Integer cantTickets;
    private String FechaViaje;
    private String tipoBoleto;
    private String image;

    public Ticket(String salida, String llegada, Integer precio, String email, Integer cantTickets, String fechaViaje, String tipoBoleto, String image) {
        this.salida = salida;
        this.llegada = llegada;
        this.precio = precio;
        this.email = email;
        this.cantTickets = cantTickets;
        FechaViaje = fechaViaje;
        this.tipoBoleto = tipoBoleto;
        this.image = image;
    }

    public Ticket(String salida, String llegada, Integer precio, String email, Integer cantTickets, String fechaViaje, String tipoBoleto) {
        this.salida = salida;
        this.llegada = llegada;
        this.precio = precio;
        this.email = email;
        this.cantTickets = cantTickets;
        FechaViaje = fechaViaje;
        this.tipoBoleto = tipoBoleto;
    }

    public Ticket(){

    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getLlegada() {
        return llegada;
    }

    public void setLlegada(String llegada) {
        this.llegada = llegada;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCantTickets() {
        return cantTickets;
    }

    public void setCantTickets(Integer cantTickets) {
        this.cantTickets = cantTickets;
    }

    public String getFechaViaje() {
        return FechaViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        FechaViaje = fechaViaje;
    }

    public String getTipoBoleto() {
        return tipoBoleto;
    }

    public void setTipoBoleto(String tipoBoleto) {
        this.tipoBoleto = tipoBoleto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
