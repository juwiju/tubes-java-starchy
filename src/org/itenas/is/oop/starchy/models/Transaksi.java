/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.starchy.models;

import java.util.Date;
/**
 *
 * @Juwita
 */
public class Transaksi {
    private int transaksi_id;
    private User user;
    private Date transaksi_date;
    private int transaksi_total;
    private Transaksi_Metode transaksi_metode;

    public Transaksi() {
    }

    public Transaksi(int transaksi_id, User user, Date transaksi_date, int transaksi_total, Transaksi_Metode transaksi_metode) {
        this.transaksi_id = transaksi_id;
        this.user = user;
        this.transaksi_date = transaksi_date;
        this.transaksi_total = transaksi_total;
        this.transaksi_metode = transaksi_metode;
    }

    public int getTransaksi_id() {
        return transaksi_id;
    }

    public void setTransaksi_id(int transaksi_id) {
        this.transaksi_id = transaksi_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTransaksi_date() {
        return transaksi_date;
    }

    public void setTransaksi_date(Date transaksi_date) {
        this.transaksi_date = transaksi_date;
    }

    public int getTransaksi_total() {
        return transaksi_total;
    }

    public void setTransaksi_total(int transaksi_total) {
        this.transaksi_total = transaksi_total;
    }

    public Transaksi_Metode getTransaksi_metode() {
        return transaksi_metode;
    }

    public void setTransaksi_metode(Transaksi_Metode transaksi_metode) {
        this.transaksi_metode = transaksi_metode;
    }
    
    
}
