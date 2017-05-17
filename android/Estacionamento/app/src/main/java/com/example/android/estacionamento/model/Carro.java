package com.example.android.estacionamento.model;

/**
 * Created by eribas on 17/05/2017.
 */
import java.util.Date;

public class Carro {

    private String placa;
    private Date date;
    private boolean pago;

    public Carro(){
    }

    public Carro(String placa, Date date, boolean pago) {
        this.placa = placa;
        this.date = date;
        this.pago = pago;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{ 'placa':" + "'"+placa +"'" + ", 'date':" + "'"+date +"'"+ "}";
    }
}
